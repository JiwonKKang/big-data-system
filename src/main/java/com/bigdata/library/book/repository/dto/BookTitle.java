package com.bigdata.library.book.repository.dto;

import org.springframework.data.mongodb.core.mapping.Field;

public record BookTitle(
        String title,
        String imageUrl,
        @Field("authors.name")
        String authorName
) {
}
