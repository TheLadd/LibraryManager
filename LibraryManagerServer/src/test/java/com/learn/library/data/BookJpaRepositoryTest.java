package com.learn.library.data;

import com.learn.library.model.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class BookJpaRepositoryTest {
    @Autowired
    private BookJpaRepository bookJpaRepository;

    // Test entity mapping and any custom queries. Can test to learn JpaRepository behaviors, too.

    @Test
    void shouldFindByBookId() {
        Book actual = bookJpaRepository.findByBookId(1);
        assertNotNull(actual);
        assertEquals(1, actual.getBookId());
        assertEquals("At The Mountains Of Madness", actual.getTitle());
        assertEquals("H.P. Lovecraft", actual.getAuthor());
        assertEquals("Cosmic Horror", actual.getGenre());
    }

    @Test
    void shouldNotFindByBookId() {
        Book actual = bookJpaRepository.findByBookId(12);
        assertNull(actual);
    }

//    @Test
//    void shouldFindBooksByLovecraft() {
//        List<Book> actual = bookJpaRepository.findByAuthor("H.P. Lovecraft");
//        assertNotNull(actual);
//        assertFalse(actual.isEmpty());
//    }

    @Test
    void shouldSaveNewBook() {
        Book bookBeforeAdd = new Book("Murtagh", "Christopher Paolini", "Fantasy");
        Book bookAfterAdd = bookJpaRepository.save(bookBeforeAdd);
        assertEquals(bookBeforeAdd.getTitle(), bookAfterAdd.getTitle());
        assertEquals(bookBeforeAdd.getAuthor(), bookAfterAdd.getAuthor());
        assertEquals(bookBeforeAdd.getGenre(), bookAfterAdd.getGenre());
        assertNotEquals(0, bookAfterAdd.getBookId());

        List<Book> all = bookJpaRepository.findAll();
        all.contains(bookAfterAdd);
    }

    @Test
    void shouldDeleteBook() {}

    @Test
    void shouldNotDeleteUnkown() {}
}