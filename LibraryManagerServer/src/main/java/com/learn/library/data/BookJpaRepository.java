package com.learn.library.data;

import com.learn.library.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookJpaRepository extends BookRepository, JpaRepository<Book, Integer> {
    List<Book> findAll();
    Book findByBookId(int bookId);
    Book save(Book book);
    void deleteById(int bookId);
}
