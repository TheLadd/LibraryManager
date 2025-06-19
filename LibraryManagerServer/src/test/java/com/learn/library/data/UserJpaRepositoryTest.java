package com.learn.library.data;

import com.learn.library.model.Member;
import com.learn.library.model.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class UserJpaRepositoryTest {
    @Autowired
    private UserJpaRepository userJpaRepository;

    // Test entity mapping and any custom queries. Can test to learn JpaRepository behaviors, too.

    @Test
    void shouldFindByUserId() {
        User actual = userJpaRepository.findByUserId(1);
        assertNotNull(actual);
        assertEquals(1, actual.getUserId());
        assertEquals("Owen", actual.getFirstName());
        assertEquals("Ribera", actual.getLastName());
    }

    @Test
    void shouldNotFindByUnknownId() {
        User actual = userJpaRepository.findByUserId(99);
        assertNull(actual);
    }
}