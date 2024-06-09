package com.bigdata.library.book.repository.dto;

import java.util.List;

public record TopBooksPerYear(
        Integer year,
        List<BookRecord> topBooks
) {
}
