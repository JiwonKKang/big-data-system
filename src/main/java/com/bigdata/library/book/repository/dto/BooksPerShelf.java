package com.bigdata.library.book.repository.dto;

public record BooksPerShelf(
        String shelfName,
        String topBookTitle,
        Integer topBookReadCount,
        Double averageRating
) {
}
