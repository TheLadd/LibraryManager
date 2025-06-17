package com.learn.library.domain;

import com.learn.library.data.BookRepository;
import com.learn.library.model.Book;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> findAll() {
        return bookRepository.findAll();
    }
}
