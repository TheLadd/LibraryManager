package com.learn.library.data;

import com.learn.library.model.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class MemberJpaRepositoryTest {
    @Autowired
    private MemberJpaRepository memberJpaRepository;

    // Test entity mapping and any custom queries. Can test to learn JpaRepository behaviors, too.

    @Test
    void shouldFindByMemberId() {
        Member actual = memberJpaRepository.findByMemberId(1);
        assertNotNull(actual);
        assertEquals(1, actual.getUserId());
        assertEquals("Owen", actual.getFirstName());
        assertEquals("Ribera", actual.getLastName());
    }

    @Test
    void shouldNotFindByUnknownId() {
        Member actual = memberJpaRepository.findByMemberId(99);
        assertNull(actual);
    }
}