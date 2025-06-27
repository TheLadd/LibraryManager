package com.learn.library.domain;

import com.learn.library.data.BookJpaRepository;
import com.learn.library.data.BookRepository;
import com.learn.library.domain.ErrorMessages.BookErrorMessage;
import com.learn.library.model.Book;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import org.springframework.stereotype.Service;

import jakarta.validation.Validator;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;

@Service
public class BookService {
    BookRepository bookRepository;
    Validator validator;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
        this.validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    // Could just as easily implement findByAuthor, etc. but this is to practice strategy pattern/functional programming
    public List<Book> findByPredicate(Predicate<Book> predicate) {
        return bookRepository.findAll().stream().filter(predicate).toList();
    }

    public Result<Book> add(Book book) {
        Result<Book> result = new Result<>();
        if (book == null) {
            result.addMessage(BookErrorMessage.NULL);
            return result;
        }

        Set<ConstraintViolation<Book>> violations = validator.validate(book);

        if (!violations.isEmpty()) {
           result.addMessages(violations.stream().map(ConstraintViolation::getMessage).toList());
        }
        else {
            Book savedBook = bookRepository.save(book);
            if (savedBook != null) {
                result.setPayload(savedBook);
            }
            else {
                result.setType(ResultType.DATABASE_FAILURE);
            }
        }

        return result;
    }

}
