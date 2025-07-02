package com.learn.library.data;

import com.learn.library.model.BorrowingRecord;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class BorrowingRecordJpaRepositoryTest {
    @Autowired
    private BorrowingRecordJpaRepository borrowingRecordJpaRepository;

    @Autowired
    private DataTestUtil dataTestUtil;

    @BeforeEach
    void setup() {
        dataTestUtil.setKnownGoodState();
    }

    @Test
    void shouldFindAll() {
        List<BorrowingRecord> result = borrowingRecordJpaRepository.findAll();
        assertNotNull(result);
        assertFalse(result.isEmpty());
    }

    @Test
    void shouldFindAllBorrowedBooks() {
        List<BorrowingRecord> actual = borrowingRecordJpaRepository.findByReturnedOnIsNull();
        assertNotNull(actual);
        assertEquals(1, actual.size());
    }

    @Test
    void shouldSaveActiveRecord() {
        BorrowingRecord activeBorrowingRecord = new BorrowingRecord(DataTestUtil.user1, DataTestUtil.book1, LocalDate.now());
        BorrowingRecord savedBorrowingRecord = borrowingRecordJpaRepository.save(activeBorrowingRecord);

        assertEquals(4, savedBorrowingRecord.getBorrowingRecordId());
        assertEquals(activeBorrowingRecord.getUser(), savedBorrowingRecord.getUser());
        assertEquals(activeBorrowingRecord.getBook(), savedBorrowingRecord.getBook());
        assertEquals(activeBorrowingRecord.getCheckedOutOn(), savedBorrowingRecord.getCheckedOutOn());
        assertNull(savedBorrowingRecord.getReturnedOn());

        List<BorrowingRecord> all = borrowingRecordJpaRepository.findAll();
        assertEquals(all.get(3), savedBorrowingRecord);
    }

}