package com.learn.library.domain;

import com.learn.library.data.BookJpaRepository;
import com.learn.library.model.Book;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.function.Predicate;

@Service
public class BookService {
    BookJpaRepository bookJpaRepository;

    public BookService(BookJpaRepository bookJpaRepository) {
        this.bookJpaRepository = bookJpaRepository;
    }

    public List<Book> findAll() {
        return bookJpaRepository.findAll();
    }

    // Could just as easily implement findByAuthor, etc. but this is to practice strategy pattern/functional programming
    public List<Book> findByPredicate(Predicate<Book> predicate) {
        return bookJpaRepository.findAll().stream().filter(predicate).toList();
    }
}
