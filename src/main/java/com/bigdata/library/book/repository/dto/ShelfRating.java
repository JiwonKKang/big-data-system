package com.bigdata.library.book.repository.dto;

public record ShelfRating(
        String shelfName,
        Double averageRating,
        Integer totalBooks
) {
}
