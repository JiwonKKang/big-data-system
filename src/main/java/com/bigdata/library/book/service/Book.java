package com.bigdata.library.book.service;

import java.util.List;

public record Book(
    String id,
    String title,
    List<Author> authors,
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
    List<Shelf> shelves,
    String description
) {
    public record Author(String id, String name, String role) {}
    public record Shelf(String name, Integer count) {}
}
