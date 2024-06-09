package com.bigdata.library.book.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "grouped_books")
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class GroupedBookDocument {

    @Id
    private BookId id;

    @Field("averageRating")
    private int averageRating;

    @Field("totalBooks")
    private int totalBooks;

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class BookId {
        private String shelf;
        private String author;
    }
}
