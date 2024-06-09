package com.bigdata.library.book.repository.dto;

import java.util.Map;

public record BookRating(
        String title,
        Map<String, Integer> ratings
) {
}
