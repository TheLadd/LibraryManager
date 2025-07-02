package com.learn.library.domain;

import com.learn.library.model.Book;
import com.learn.library.model.BorrowingRecord;
import com.learn.library.model.User;
import org.springframework.stereotype.Component;

import java.util.List;


/*

    Was going to make this class a singleton before remembering that all beans in Spring are
    Singleton's by default.

 */

@Component
public class LibraryManager {
    private BookService bookService;
    private UserService userService;
    private BorrowingRecordService borrowingRecordService;

    private LibraryManager(BookService bookService, UserService userService, BorrowingRecordService borrowingRecordService) {
        this.bookService = bookService;
        this.userService = userService;
        this.borrowingRecordService = borrowingRecordService;
    }

    public Book addBook(Book book) { return null; }

    public List<Book> findAllBooks() { return null; }

    public List<Book> findBookByTitle(String title) { return null; }

    public List<Book> findBookByAuthor(String author) { return null; }

    public List<Book> findBookByGenre(String genre) { return null; }


    public User addUser(User user) { return null; }

    public List<User> findAllUsers() { return null; }


    public BorrowingRecord borrowBook(User user, Book book) { return null; }

    public BorrowingRecord returnBook(Book book) { return null; }

    public BorrowingRecord returnBook(BorrowingRecord record) { return null; }
}
