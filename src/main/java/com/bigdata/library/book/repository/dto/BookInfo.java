package com.bigdata.library.book.repository.dto;

public record BookInfo(
        String bookId,
        String author_id,
        String title,
        String image_url
) {
}
