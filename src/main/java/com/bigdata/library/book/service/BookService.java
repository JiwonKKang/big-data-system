package com.bigdata.library.book.service;

import com.bigdata.library.book.repository.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookReader bookReader;

    public List<PublisherRating> findPublisherAverageRatingForSeries(String seriesName) {
        return bookReader.findPublisherAverageRatingForSeries(seriesName);
    }

    public List<TopBooksPerYear> findTop3BooksPerYear() {
        return bookReader.findTop3BooksPerYear();
    }

    public List<BookTitle> findBooksByTitleAuthorLanguage(String title, String author, String language) {
        return bookReader.findBooksByTitleAuthorLanguage(title, author, language);
    }

    public List<BookImageUrl> findTopChildrenBooks(String shelfName) {
        return bookReader.findTopChildrenBooks(shelfName);
    }

    public List<BooksPerShelf> findTopReadBooksPerShelf() {
        return bookReader.findTopReadBooksPerShelf();
    }

    public List<BookCount> findBookCountPerYear() {
        return bookReader.findBookCountPerYear();
    }

    public List<AuthorRating> findAuthorRatingDistributionForBook() {
        return bookReader.findAuthorRatingDistributionForBook();
    }

    public List<BookRating> findRatingDistributionForBook(String bookId) {
        return bookReader.findRatingDistributionForBook(bookId);
    }

    public List<ShelfRating> findAverageRatingForAuthor(String authorName) {
        return bookReader.findAverageRatingForAuthor(authorName);
    }

    public List<ShelfAuthors> findTop3AuthorsPerShelf() {
        return bookReader.findTop3AuthorsPerShelf();
    }


}
