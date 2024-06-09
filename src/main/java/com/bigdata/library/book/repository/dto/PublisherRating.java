package com.bigdata.library.book.repository.dto;

public record PublisherRating(
        String id,
        Double average_rating,
        Integer totalBooks
) {
}
