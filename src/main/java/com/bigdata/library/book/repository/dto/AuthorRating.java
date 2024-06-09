package com.bigdata.library.book.repository.dto;

public record AuthorRating(
        String authorId,
        Integer total5Star,
        Integer total4Star,
        Integer total3Star,
        Integer total2Star,
        Integer total1Star
) {
}
