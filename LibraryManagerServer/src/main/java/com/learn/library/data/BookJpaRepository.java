package com.learn.library.data;

import com.learn.library.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookJpaRepository extends BookRepository, JpaRepository<Book, Integer> {
    List<Book> findAll();

    @Override   // This feels shaky but it works. Necessary to eliminate ambiguity between BookRepository.save() and JpaRepository.save()
    Book save(Book book);

    Book findByBookId(int bookId);

    void deleteById(int bookId);
}
