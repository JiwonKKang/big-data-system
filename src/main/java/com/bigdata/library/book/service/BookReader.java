package com.bigdata.library.book.service;

import com.bigdata.library.book.repository.BookMongoRepository;
import com.bigdata.library.book.repository.GroupBookMongoRepository;
import com.bigdata.library.book.repository.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class BookReader {

    private final BookMongoRepository bookRepository;
    private final GroupBookMongoRepository groupBookRepository;

    public List<BookImageUrl> findTopByShelf(String shelfName) {
        PageRequest pageRequest = PageRequest.of(0, 10);
        return bookRepository.findTopByShelf(shelfName, pageRequest);
    }

    public List<BookTitle> findBooksByTitleAuthorLanguage(String title, String author, String language) {
        return bookRepository.findBooksByTitleAuthorLanguage(title, author, language);
    }

    public List<BooksPerShelf> findTopReadBooksPerShelf() {
        return bookRepository.getTopReadBooksPerShelf();
    }

    public List<TopBooksPerYear> findTop3BooksPerYear() {
        return bookRepository.getTop3BooksPerYear();
    }

    public List<BookCount> findBookCountPerYear() {
        return bookRepository.getBookCountPerYear();
    }

    public List<PublisherRating> findPublisherAverageRatingForSeries(String seriesName) {
        return bookRepository.getPublisherAverageRatingForSeries(seriesName);
    }

    public List<ShelfAuthors> findTop3AuthorsPerShelf() {
        return groupBookRepository.getTop3AuthorsPerShelf();
    }

    public List<AuthorRating> findAuthorRatingDistributionForBook() {
        return bookRepository.getAuthorRatingDistributionForBook();
    }

    public List<BookRating> findRatingDistributionForBook(String bookId) {
        return bookRepository.getRatingDistributionForBook(bookId);
    }

    public List<ShelfRating> findAverageRatingForAuthor(String authorName) {
        return bookRepository.getAverageRatingForAuthor(authorName);
    }

}
