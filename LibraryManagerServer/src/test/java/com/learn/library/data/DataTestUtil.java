package com.learn.library.data;

import com.learn.library.domain.BorrowingRecordService;
import com.learn.library.model.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.StoredProcedureQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
public class DataTestUtil {
    public static final User user1 = new Member(1, "Owen", "Ribera", LocalDate.of(2000, 9, 20));
    public static final Book book1 = new Book(1, "At The Mountains Of Madness", "H.P. Lovecraft", "Cosmic Horror");
    public static final BorrowingRecord record1 = new BorrowingRecord(user1, book1, LocalDate.now());
    public static final BorrowingRecord record2 = new BorrowingRecord(user1, book1, LocalDate.of(2025, 1, 20), LocalDate.of(2025, 2, 21));

    public static final User newUser = new Member("Charlie", "CuteGirl", LocalDate.now());
    public static final User invalidUser = new Member("Scott", "", LocalDate.now());
    public static final Book newBook = new Book("Testing With JUnit", "Owen Ribera", "Programming");
    public static final Book invalidBook = new Book("", "Owen Ribera", "Programming");
    public static final BorrowingRecord recordWithInvalidUser = new BorrowingRecord(invalidUser, book1, LocalDate.now());

    public static final List<Book> books = List.of(book1);
    public static final List<User> users = List.of(user1);
    public static final List<BorrowingRecord> records = List.of(record1, record2);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public DataTestUtil(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void setKnownGoodState() {
        jdbcTemplate.update("call set_known_good_state()");
    }

}
