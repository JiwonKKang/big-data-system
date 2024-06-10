package com.bigdata.library.book.repository.dto;

public record ShelfRating(
        String _id,
        Double average_rating,
        Integer totalBooks
) {
}
