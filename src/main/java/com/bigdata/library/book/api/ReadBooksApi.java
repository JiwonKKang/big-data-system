package com.bigdata.library.book.api;

import com.bigdata.library.book.repository.ShelfBookSummary;
import com.bigdata.library.book.repository.dto.*;
import com.bigdata.library.book.service.BookService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/books")
@RequiredArgsConstructor
public class ReadBooksApi {

    private final BookService bookService;

    @GetMapping("/topsByShelf")
    @Operation(summary = "서가별 Top 책 조회 - query1")
    public List<BookImageUrl> findTopByShelf(String shelfName) {
        return bookService.findTopByShelf(shelfName);
    }

    @GetMapping("/booksByTitleAuthorLanguage")
    @Operation(summary = "제목, 저자, 언어로 책 조회 - query2")
    public List<BookTitle> findBooksByTitleAuthorLanguage(@RequestParam String title, @RequestParam String author, @RequestParam String language) {
        return bookService.findBooksByTitleAuthorLanguage(title, author, language);
    }

    @GetMapping("/authorRatingDistributionForBook")
    @Operation(summary = "특정 책의 작가별 평점 분포 조회 - query3")
    public List<AuthorRating> findAuthorRatingDistributionForBook(String bookId) {
        return bookService.findAuthorRatingDistributionForBook(bookId);
    }

    @GetMapping("/ratingDistributionForBook")
    @Operation(summary = "특정 책의 평점 분포 조회 - query4")
    public List<BookRating> findRatingDistributionForBook(String bookId) {
        return bookService.findRatingDistributionForBook(bookId);
    }

    @GetMapping("/averageRatingForAuthor")
    @Operation(summary = "특정작가 서가 평점 TOP5 조회 - query5")
    public List<ShelfRating> findAverageRatingForAuthor(String authorName) {
        return bookService.findAverageRatingForAuthor(authorName);
    }

    @GetMapping("/publisherAverageRatingForSeries")
    @Operation(summary = "시리즈이름으로 출판사 평균 평점 조회 - query6")
    public List<PublisherRating> findPublisherAverageRatingForSeries(String seriesName) {
        return bookService.findPublisherAverageRatingForSeries(seriesName);
    }

    @GetMapping("/top3AuthorsPerShelf")
    @Operation(summary = "서가별 Top3 작가 조회 - query7")
    public List<ShelfAuthors> findTop3AuthorsPerShelf() {
        return bookService.findTop3AuthorsPerShelf();
    }

    @GetMapping("/topReadBooksPerShelf")
    @Operation(summary = "서가별 Top 읽은 책 조회 - query8")
    public List<ShelfBookSummary> findTopReadBooksPerShelf() {
        return bookService.getShelfBookSummaries();
    }

    //stackoverflow
    @GetMapping("/top3BooksPerYear")
    @Operation(summary = "연도별 Top3 책 조회 - query9")
    public List<TopBooksPerYear> findTop3BooksPerYear() {
        return bookService.findTop3BooksPerYear();
    }

    //stackoverflow
    @GetMapping("/bookCountPerYear")
    @Operation(summary = "연도별 책 발간 수 조회 - query10 ")
    public List<BookCountPerYear> findBookCountPerYear() {
        return bookService.findBookCountPerYear();
    }


}
