package com.learn.library.data;

import com.learn.library.model.Member;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class MemberRepositoryTest {
    @Autowired
    private MemberRepository memberRepository;

    // Test entity mapping and any custom queries. Can test to learn JpaRepository behaviors, too.

    @Test
    void shouldFindByMemberId() {
        Member actual = memberRepository.findByMemberId(1);
        assertNotNull(actual);
        assertEquals(1, actual.getMemberId());
        assertEquals("Owen", actual.getFirstName());
        assertEquals("Ribera", actual.getLastName());
    }

    @Test
    void shouldNotFindByUnknownId() {
        Member actual = memberRepository.findByMemberId(99);
        assertNull(actual);
    }
}