package com.bigdata.library.book.api;

import com.bigdata.library.book.service.Book;

import java.util.List;

public record BookResponse(
        String id,
        String title,
        List<Book.Author> authors,
        String authorName,
        String authorId,
        String workId,
        String isbn,
        String isbn13,
        String asin,
        String language,
        Double averageRating,
        String ratingDistribution,
        Integer ratingsCount,
        Integer textReviewsCount,
        String publicationDate,
        String originalPublicationDate,
        String format,
        String editionInformation,
        String imageUrl,
        String publisher,
        Integer numPages,
        String seriesId,
        String seriesName,
        String seriesPosition,
        List<Book.Shelf> shelves,
        String description
) {
    public static BookResponse from(Book book) {
        return new BookResponse(
                book.id(),
                book.title(),
                book.authors(),
                book.authorName(),
                book.authorId(),
                book.workId(),
                book.isbn(),
                book.isbn13(),
                book.asin(),
                book.language(),
                book.averageRating(),
                book.ratingDistribution(),
                book.ratingsCount(),
                book.textReviewsCount(),
                book.publicationDate(),
                book.originalPublicationDate(),
                book.format(),
                book.editionInformation(),
                book.imageUrl(),
                book.publisher(),
                book.numPages(),
                book.seriesId(),
                book.seriesName(),
                book.seriesPosition(),
                book.shelves(),
                book.description()
        );
    }
}
