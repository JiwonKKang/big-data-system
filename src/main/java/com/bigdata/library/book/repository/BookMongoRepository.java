package com.bigdata.library.book.repository;

import com.bigdata.library.book.domain.BooksDocument;
import com.bigdata.library.book.repository.dto.BookInfo;
import com.bigdata.library.book.repository.dto.BookTitle;
import com.bigdata.library.book.repository.dto.*;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface BookMongoRepository extends MongoRepository<BooksDocument, String> {

    @Aggregation(pipeline = {
            "{ $match: { 'shelves.name': ?0, 'text_reviews_count': { $gt: 3000 } } }",
            "{ $sort: { 'ratings_count': -1 } }",
            "{ $group: { _id: '$author_id', firstBook: { $first: '$$ROOT' } } }",
            "{ $replaceRoot: { newRoot: '$firstBook' } }",
            "{ $project: { _id: 0, bookId: '$id', title: 1, imageUrl: 1 } }",
            "{ $limit: 10 }"
    })
    List<BookInfo> findTopByShelf(String shelfName);

    @Query(value = "{ 'title': { $regex: ?0, $options: 'i' }, 'authors.name': { $regex: ?1, $options: 'i' }, 'language': { $regex: ?2, $options: 'i' } }",
            fields = "{ 'title': 1, '_id': 0 }")
    List<BookTitle> findBooksByTitleAuthorLanguage(String title, String author, String language);

    @Aggregation(pipeline = {
            "{ $match: { id: ?0 } }",
            "{ $addFields: { rating_dist_array: { $split: ['$rating_dist', '|'] } } }",
            "{ $addFields: { rating_counts: { $arrayToObject: { $map: { input: '$rating_dist_array', as: 'item', in: { k: { $arrayElemAt: [ { $split: ['$$item', ':'] }, 0 ] }, v: { $toInt: { $arrayElemAt: [ { $split: ['$$item', ':'] }, 1 ] } } } } } } } } }",
            "{ $group: { _id: '$author_id', total_5_star: { $sum: '$rating_counts.5' }, total_4_star: { $sum: '$rating_counts.4' }, total_3_star: { $sum: '$rating_counts.3' }, total_2_star: { $sum: '$rating_counts.2' }, total_1_star: { $sum: '$rating_counts.1' } } }",
            "{ $project: { _id: 0, author_id: '$_id', total_5_star: 1, total_4_star: 1, total_3_star: 1, total_2_star: 1, total_1_star: 1 } }"
    })
    List<AuthorRating> getAuthorRatingDistributionForBook(String bookId);

    @Aggregation(pipeline = {
            "{ $match: { id: ?0 } }",
            "{ $project: { _id: 0, title: 1, ratings: { $arrayToObject: { $map: { input: { $split: ['$rating_dist', '|'] }, as: 'item', in: { k: { $arrayElemAt: [ { $split: ['$$item', ':'] }, 0 ] }, v: { $toInt: { $arrayElemAt: [ { $split: ['$$item', ':'] }, 1 ] } } } } } } }}"
    })
    List<BookRating> getRatingDistributionForBook(String bookId);

    @Aggregation(pipeline = {
            "{ $match: { 'authors.name': ?0 } }",
            "{ $unwind: '$shelves' }",
            "{ $group: { _id: '$shelves.name', averageRating: { $avg: '$average_rating' }, totalBooks: { $sum: 1 } } }",
            "{ $sort: { averageRating: -1 } }"
    })
    List<ShelfRating> getAverageRatingForAuthor(String authorName);

    @Aggregation(pipeline = {
            "{ $match: { series_name: ?0 } }",
            "{ $group: { _id: '$publisher', averageRating: { $avg: '$average_rating' }, totalBooks: { $sum: 1 } } }",
            "{ $sort: { averageRating: -1 } }"
    })
    List<PublisherRating> getPublisherAverageRatingForSeries(String seriesName);

    @Aggregation(pipeline = {
            "{ $project: { title: 1, average_rating: 1, shelves: { $slice: ['$shelves', 5] } } }",
            "{ $unwind: '$shelves' }",
            "{ $group: { _id: { shelf: '$shelves.name', book: '$title' }, readCount: { $sum: '$shelves.count' }, averageRating: { $first: '$average_rating' } } }",
            "{ $sort: { readCount: -1 } }",
            "{ $group: { _id: '$_id.shelf', topBook: { $first: '$_id.book' }, topBookReadCount: { $first: '$readCount' }, averageRating: { $first: '$averageRating' } } }",
            "{ $sort: { topBookReadCount: -1 } }",
            "{ $project: { _id: 0, shelfName: '$_id', topBookTitle: '$topBook', topBookReadCount: '$topBookReadCount', averageRating: '$averageRating' } }",
            "{ $limit: 20 }"
    })
    List<BooksPerShelf> getTopReadBooksPerShelf();



    @Aggregation(pipeline = {
            "{ $match: { ratings_count: { $gte: 100000 }, original_publication_date: { $regex: '^(?:\\\\d{4}|\\\\d{4}-\\\\d{2}-\\\\d{2})$' } } }",
            "{ $group: { _id: { year: { $toInt: { $substr: ['$original_publication_date', 0, 4] } }, book: '$title' }, averageRating: { $avg: '$average_rating' } } }",
            "{ $match: { '_id.year': { $lte: 2024 } } }",
            "{ $sort: { '_id.year': 1, averageRating: -1 } }",
            "{ $group: { _id: '$_id.year', topBooks: { $push: { title: '$_id.book', averageRating: '$averageRating' } } } }",
            "{ $project: { _id: 0, year: '$_id', topBooks: { $slice: ['$topBooks', 3] } } }",
            "{ $sort: { year: -1 } }",
    })
    List<TopBooksPerYear> getTop3BooksPerYear();



    @Aggregation(pipeline = {
            "{ $project: { original_publication_date: 1 } }",
            "{ $match: { original_publication_date: { $regex: '^(?:\\\\d{4}|\\\\d{4}-\\\\d{2}-\\\\d{2})$' } } }",
            "{ $group: { _id: { $toInt: { $substr: [ '$original_publication_date', 0, 4 ] } }, count: { $sum: 1 } } }",
            "{ $match: { _id: { $lt: 2023 } } }",
            "{ $sort: { _id: -1 } }"
    })
    List<BookCountPerYear> getBookCountPerYear();

}
