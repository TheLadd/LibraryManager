package com.learn.library.data;

import com.learn.library.model.Book;

import java.util.List;

public interface BookRepository {
    List<Book> findAll();
    List<Book> findByAuthor(String author);
    Book findByBookId(int bookId);
}
