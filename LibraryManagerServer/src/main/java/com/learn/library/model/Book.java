package com.learn.library.model;

import com.learn.library.domain.ErrorMessages.BookErrorMessage;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.Objects;

@Entity
@Table(name = "book")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int bookId;

    @Column
//    @NotNull(message = BookErrorMessage.TITLE_NULL)
    @NotEmpty(message = BookErrorMessage.TITLE_EMPTY)
    private String title;

    @Column
//    @NotNull(message = BookErrorMessage.AUTHOR_NULL)
    @NotEmpty(message = BookErrorMessage.AUTHOR_EMPTY)
    private String author;

    @Column
//    @NotNull(message = BookErrorMessage.GENRE_NULL)
    @NotEmpty(message = BookErrorMessage.GENRE_EMPTY)
    private String genre;


    public Book() {}

    public Book(String title, String author, String genre) {
        this.title = title;
        this.author = author;
        this.genre = genre;
    }

    public Book(int bookId, String title, String author, String genre) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.genre = genre;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    @Override
    public String toString() {
        return "Book{" +
                "bookId=" + bookId +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", genre='" + genre + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Book book)) return false;
        return bookId == book.bookId && Objects.equals(title, book.title) && Objects.equals(author, book.author) && Objects.equals(genre, book.genre);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bookId, title, author, genre);
    }
}
