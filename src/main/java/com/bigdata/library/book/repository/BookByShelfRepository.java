package com.bigdata.library.book.repository;

import com.bigdata.library.book.domain.BooksByShelf;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface BookByShelfRepository extends MongoRepository<BooksByShelf, BooksByShelf.ShelfBookId>{

    @Aggregation(pipeline = {
            "{ $group: { _id: '$_id.shelf', topBook: { $first: '$_id.book' }, topBookReadCount: { $first: '$readCount' }, averageRating: { $first: '$averageRating' } } }",
            "{ $sort: { topBookReadCount: -1 } }",
            "{ $project: { _id: 0, shelfName: '$_id', topBookTitle: '$topBook', topBookReadCount: '$topBookReadCount', averageRating: '$averageRating' } }"
    })
    List<ShelfBookSummary> getShelfBookSummaries();


}
