package com.bigdata.library.book.repository;

public record ShelfBookSummary(
        String shelfName,
        String topBookTitle,
        Integer topBookReadCount,
        Double averageRating
) {
}
