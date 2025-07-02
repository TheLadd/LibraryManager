package com.learn.library.domain;

import com.learn.library.domain.ErrorMessages.LibraryManagerErrorMessage;
import com.learn.library.model.Book;
import com.learn.library.model.BorrowingRecord;
import com.learn.library.model.User;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;


/*

    Was going to make this class a singleton before remembering that all beans in Spring are
    Singleton's by default.

 */

@Component
public class LibraryManager {
    private BookService bookService;
    private UserService userService;
    private BorrowingRecordService borrowingRecordService;

    public LibraryManager(BookService bookService, UserService userService, BorrowingRecordService borrowingRecordService) {
        this.bookService = bookService;
        this.userService = userService;
        this.borrowingRecordService = borrowingRecordService;
    }

    @Async
    public CompletableFuture<Result<Book>> addBook(Book book) { return CompletableFuture.completedFuture(bookService.add(book)); }

    @Async
    public CompletableFuture<List<Book>> findAllBooks() { return CompletableFuture.completedFuture(bookService.findAll()); }

    @Async
    public CompletableFuture<List<Book>> findBookByTitle(String title) { return CompletableFuture.completedFuture(bookService.findByPredicate(book -> book.getTitle().contains(title))); }

    @Async
    public CompletableFuture<List<Book>> findBookByAuthor(String author) { return CompletableFuture.completedFuture(bookService.findByPredicate(book -> book.getAuthor().contains(author))); }

    @Async
    public CompletableFuture<List<Book>> findBookByGenre(String genre) { return CompletableFuture.completedFuture(bookService.findByPredicate(book -> book.getGenre().contains(genre))); }


    @Async
    public CompletableFuture<Result<User>> addUser(User user) { return CompletableFuture.completedFuture(userService.add(user)); }

    @Async
    public CompletableFuture<List<User>> findAllUsers() { return CompletableFuture.completedFuture(userService.findAll()); }


    @Async
    public CompletableFuture<Result<BorrowingRecord>> borrowBook(User user, Book book) {
        BorrowingRecord record = new BorrowingRecord(user, book, LocalDate.now());
        return CompletableFuture.completedFuture(borrowingRecordService.add(record));
    }

    @Async
    public CompletableFuture<Result<BorrowingRecord>> returnBook(Book book) {
        // This assumes that we only have one copy of each book. Going to run with this for now...
        Optional<BorrowingRecord> record = borrowingRecordService.findActiveRecords().stream()
                .filter(br -> br.getBook().getBookId() == book.getBookId())
                .findFirst();

        if (record.isEmpty()) {
            Result<BorrowingRecord> result = new Result<>();
            result.addMessage(LibraryManagerErrorMessage.BOOK_NOT_CHECKED_OUT);
            return CompletableFuture.completedFuture(result);
        }

        record.get().setReturnedOn(LocalDate.now());
        return CompletableFuture.completedFuture(borrowingRecordService.update(record.get()));
    }
}
