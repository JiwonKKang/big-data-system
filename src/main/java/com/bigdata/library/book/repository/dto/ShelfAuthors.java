package com.bigdata.library.book.repository.dto;

import java.util.List;

public record ShelfAuthors(
        String _id,
        List<Author> authors
) {
    public static record Author(
            String name,
            int averageRating,
            int totalBooks
    ) {
    }
}
