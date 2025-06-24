package com.learn.library.data;

import com.learn.library.model.Book;
import com.learn.library.model.Member;
import com.learn.library.model.Staff;
import com.learn.library.model.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.StoredProcedureQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DataTestUtil {
    public static User user1 = new Member(1, "Owen", "Ribera", LocalDate.of(2000, 9, 20));
    public static Book book1 = new Book(1, "At The Mountains Of Madness", "H.P. Lovecraft", "Cosmic Horror");

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public DataTestUtil(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void setKnownGoodState() {
        jdbcTemplate.update("call set_known_good_state()");
    }

}
