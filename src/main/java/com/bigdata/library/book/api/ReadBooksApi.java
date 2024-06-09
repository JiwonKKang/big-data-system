package com.bigdata.library.book.api;

import com.bigdata.library.book.repository.dto.*;
import com.bigdata.library.book.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/books")
@RequiredArgsConstructor
public class ReadBooksApi {

    private final BookService bookService;

    @GetMapping("/publisherAverageRatingForSeries")
    public List<PublisherRating> findPublisherAverageRatingForSeries(String seriesName) {
        return bookService.findPublisherAverageRatingForSeries(seriesName);
    }

    @GetMapping("/top3BooksPerYear")
    public List<TopBooksPerYear> findTop3BooksPerYear() {
        return bookService.findTop3BooksPerYear();
    }

    @GetMapping("/booksByTitleAuthorLanguage")
    public List<BookTitle> findBooksByTitleAuthorLanguage(@RequestParam String title, @RequestParam String author, @RequestParam String language) {
        return bookService.findBooksByTitleAuthorLanguage(title, author, language);
    }

    @GetMapping("/topsByShelf")
    public List<BookImageUrl> findTopByShelf(String shelfName) {
        return bookService.findTopChildrenBooks(shelfName);
    }

    @GetMapping("/topReadBooksPerShelf")
    public List<BooksPerShelf> findTopReadBooksPerShelf() {
        return bookService.findTopReadBooksPerShelf();
    }

    @GetMapping("/bookCountPerYear")
    public List<BookCount> findBookCountPerYear() {
        return bookService.findBookCountPerYear();
    }

    @GetMapping("/authorRatingDistributionForBook")
    public List<AuthorRating> findAuthorRatingDistributionForBook() {
        return bookService.findAuthorRatingDistributionForBook();
    }

    @GetMapping("/ratingDistributionForBook")
    public List<BookRating> findRatingDistributionForBook(String bookId) {
        return bookService.findRatingDistributionForBook(bookId);
    }

    @GetMapping("/averageRatingForAuthor")
    public List<ShelfRating> findAverageRatingForAuthor(String authorName) {
        return bookService.findAverageRatingForAuthor(authorName);
    }

    @GetMapping("/top3AuthorsPerShelf")
    public List<ShelfAuthors> findTop3AuthorsPerShelf() {
        return bookService.findTop3AuthorsPerShelf();
    }

}
