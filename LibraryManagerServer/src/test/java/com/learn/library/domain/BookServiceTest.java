package com.learn.library.domain;

import com.learn.library.data.BookRepository;
import com.learn.library.data.DataTestUtil;
import com.learn.library.domain.ErrorMessages.BookErrorMessage;
import com.learn.library.model.Book;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class BookServiceTest {
    @MockBean(name = "bookJpaRepository")   // Important to do when using a JPA generated class/bean
    BookRepository bookRepository;

    @Autowired
    BookService bookService;

    @Test
    void shouldFindAll() {
        Mockito.when(bookRepository.findAll()).thenReturn(DataTestUtil.books);

        List<Book> all = bookService.findAll();
        assertEquals(DataTestUtil.books, all);
    }

    @Test
    void shouldSaveBook() {
        Book newBookWithId = generateNewBook();
        newBookWithId.setBookId(4);
        Mockito.when(bookRepository.save(Mockito.any())).thenReturn(newBookWithId);

        Result<Book> actual = bookService.add(generateNewBook());

        assertTrue(actual.isSuccess());
        assertEquals(newBookWithId, actual.getPayload());
    }

    @Test
    void shouldNotSaveNullTitle() {
        Book nullTitle = generateNewBook();
        nullTitle.setTitle(null);

        Result<Book> actual = bookService.add(nullTitle);

        assertFalse(actual.isSuccess());
        assertEquals(1, actual.getMessages().size());
        assertTrue(actual.getMessages().contains(BookErrorMessage.TITLE_EMPTY));
    }

    @Test
    void shouldNotSaveEmptyTitle() {
        Book emptyTitle = generateNewBook();
        emptyTitle.setTitle("");

        Result<Book> actual = bookService.add(emptyTitle);

        assertFalse(actual.isSuccess());
        assertEquals(1, actual.getMessages().size());
        assertTrue(actual.getMessages().contains(BookErrorMessage.TITLE_EMPTY));
    }

    @Test
    void shouldNotSaveNullAuthor() {
        Book nullAuthor = generateNewBook();
        nullAuthor.setAuthor("");

        Result<Book> actual = bookService.add(nullAuthor);

        assertFalse(actual.isSuccess());
        assertEquals(1, actual.getMessages().size());
        assertTrue(actual.getMessages().contains(BookErrorMessage.AUTHOR_EMPTY));
    }

    @Test
    void shouldNotSaveMissingAuthor() {
        Book blankAuthor = generateNewBook();
        blankAuthor.setAuthor("");

        Result<Book> actual = bookService.add(blankAuthor);

        assertFalse(actual.isSuccess());
        assertEquals(1, actual.getMessages().size());
        assertTrue(actual.getMessages().contains(BookErrorMessage.AUTHOR_EMPTY));
    }

    @Test
    void shouldNotSaveNullGenre() {
        Book nullGenre = generateNewBook();
        nullGenre.setGenre(null);

        Result<Book> actual = bookService.add(nullGenre);

        assertFalse(actual.isSuccess());
        assertEquals(1, actual.getMessages().size());
        assertTrue(actual.getMessages().contains(BookErrorMessage.GENRE_EMPTY));
    }

    @Test
    void shouldNotSaveMissingGenre() {
        Book blankGenre = generateNewBook();
        blankGenre.setGenre("");

        Result<Book> actual = bookService.add(blankGenre);

        assertFalse(actual.isSuccess());
        assertEquals(1, actual.getMessages().size());
        assertTrue(actual.getMessages().contains(BookErrorMessage.GENRE_EMPTY));
    }

    @Test
    void shouldFindOneByPredicate() {
        // .findByPredicate()'s implementation uses findAll() and then filters. I know unit tests are supposed to be
        //  implementation agnostic but I'm mocking soooooooo
        Mockito.when(bookRepository.findAll()).thenReturn(DataTestUtil.books);

        List<Book> actual = bookService.findByPredicate(book -> book.getAuthor().equals("H.P. Lovecraft"));

        assertEquals(1, actual.size());
        assertEquals(DataTestUtil.book1, actual.get(0));
    }

    @Test
    void shouldFindNoneByPredicate() {
        // .findByPredicate()'s implementation uses findAll() and then filters. I know unit tests are supposed to be
        //  implementation agnostic but I'm mocking soooooooo
        Mockito.when(bookRepository.findAll()).thenReturn(DataTestUtil.books);

        List<Book> actual = bookService.findByPredicate(book -> book.getAuthor().equals("Doodoo Stinkerton"));

        assertTrue(actual.isEmpty());
    }

    private Book generateNewBook() {
        return new Book("The Road To Being Okay", "Owen Ribera", "Self-Help");
    }
}