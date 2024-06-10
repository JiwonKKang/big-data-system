package com.bigdata.library.book.repository.dto;

public record AuthorRating(
        String author_id,
        Integer total_5_star,
        Integer total_4_star,
        Integer total_3_star,
        Integer total_2_star,
        Integer total_1_star
) {
}
