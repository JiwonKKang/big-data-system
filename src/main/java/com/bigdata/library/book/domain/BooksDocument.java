package com.bigdata.library.book.domain;

import com.bigdata.library.book.service.Book;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@Document(collection = "books")
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class BooksDocument {

    @Id
    private String id;
    private String title;
    private List<AuthorDocument> authors;
    @Field("author_name")
    private String authorName;
    @Field("author_id")
    private String authorId;
    @Field("work_id")
    private String workId;
    private String isbn;
    private String isbn13;
    private String asin;
    private String language;
    @Field("average_rating")
    private Double averageRating;
    @Field("rating_dist")
    private String ratingDistribution;
    @Field("ratings_count")
    private Integer ratingsCount;
    @Field("text_reviews_count")
    private Integer textReviewsCount;
    @Field("publication_date")
    private String publicationDate;
    @Field("original_publication_date")
    private String originalPublicationDate;
    private String format;
    @Field("edition_information")
    private String editionInformation;
    @Field("image_url")
    private String imageUrl;
    private String publisher;
    @Field("num_pages")
    private Integer numPages;
    @Field("series_id")
    private String seriesId;
    @Field("series_name")
    private String seriesName;
    @Field("series_position")
    private String seriesPosition;
    private List<ShelfDocument> shelves;
    private String description;


    @Getter
    @AllArgsConstructor
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class AuthorDocument {
        private String id;
        private String name;
        private String role;

        public Book.Author toDomain() {
            return new Book.Author(id, name, role);
        }
    }

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class ShelfDocument {
        private String name;
        private Integer count;

        public Book.Shelf toDomain() {
            return new Book.Shelf(name, count);
        }
    }


    public Book toDomain() {
        return new Book(
                id,
                title,
                authors.stream()
                        .map(AuthorDocument::toDomain)
                        .toList(),
                authorName,
                authorId,
                workId,
                isbn,
                isbn13,
                asin,
                language,
                averageRating,
                ratingDistribution,
                ratingsCount,
                textReviewsCount,
                publicationDate,
                originalPublicationDate,
                format,
                editionInformation,
                imageUrl,
                publisher,
                numPages,
                seriesId,
                seriesName,
                seriesPosition,
                shelves.stream()
                        .map(ShelfDocument::toDomain)
                        .toList(),
                description
        );
    }

}
