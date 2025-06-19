package com.learn.library.data;

import com.learn.library.model.BorrowingRecord;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class BorrowingRecordJpaRepositoryTest {
    @Autowired
    private BorrowingRecordJpaRepository borrowingRecordJpaRepository;

    @Test
    void shouldFindAll() {
        List<BorrowingRecord> result = borrowingRecordJpaRepository.findAll();
        assertNotNull(result);
        assertTrue(!result.isEmpty());
    }
}