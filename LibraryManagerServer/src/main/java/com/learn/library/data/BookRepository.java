package com.learn.library.data;

import com.learn.library.model.Book;

import java.util.List;

public interface BookRepository {
    List<Book> findAll();
    Book findByBookId(int bookId);
    Book save(Book book);
    void deleteById(int bookId);
}
