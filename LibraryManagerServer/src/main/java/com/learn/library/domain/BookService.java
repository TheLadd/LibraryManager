package com.learn.library.domain;

import com.learn.library.data.BookJpaRepository;
import com.learn.library.model.Book;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    BookJpaRepository bookJpaRepository;

    public BookService(BookJpaRepository bookJpaRepository) {
        this.bookJpaRepository = bookJpaRepository;
    }

    public List<Book> findAll() {
        return bookJpaRepository.findAll();
    }
}
