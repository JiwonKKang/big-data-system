package com.bigdata.library.book.repository.dto;

public record ShelfRating(
        String _id,
        Double averageRating,
        Integer totalBooks
) {
}
