package com.learn.library.data;

import com.learn.library.model.Member;
import com.learn.library.model.Staff;
import com.learn.library.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class UserJpaRepositoryTest {
    @Autowired
    private UserJpaRepository userJpaRepository;

    // Test entity mapping and any custom queries. Can test to learn JpaRepository behaviors, too.

    @Test
    void shouldFindMemberByUserId() {
        Member actual = (Member) userJpaRepository.findByUserId(1);
        assertNotNull(actual);
        assertEquals(1, actual.getUserId());
        assertEquals("Owen", actual.getFirstName());
        assertEquals("Ribera", actual.getLastName());
        assertEquals(LocalDate.of(2000, 9, 20), actual.getJoinedOn());
    }

    @Test
    void shouldFindStaffByUserId() {
        Staff actual = (Staff) userJpaRepository.findByUserId(4);
        assertNotNull(actual);
        assertEquals(4, actual.getUserId());
        assertEquals("Jackson", actual.getFirstName());
        assertEquals("Ribera", actual.getLastName());
        assertEquals(LocalDate.of(1997, 4, 20), actual.getHiredOn());
    }

    @Test
    void shouldNotFindByUnknownId() {
        User actual = userJpaRepository.findByUserId(99);
        assertNull(actual);
    }
}