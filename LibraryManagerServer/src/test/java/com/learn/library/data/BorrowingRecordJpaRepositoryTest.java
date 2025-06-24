package com.learn.library.data;

import com.learn.library.model.BorrowingRecord;
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

    @Test
    void shouldFindAll() {
        List<BorrowingRecord> result = borrowingRecordJpaRepository.findAll();
        assertNotNull(result);
        assertFalse(result.isEmpty());
    }

    @Test
    void shouldSaveActiveRecord() {
        BorrowingRecord activeBorrowingRecord = new BorrowingRecord(DataTestUtil.user1, DataTestUtil.book1, LocalDate.now());
        BorrowingRecord savedBorrowingRecord = borrowingRecordJpaRepository.save(activeBorrowingRecord);

        assertEquals(3, savedBorrowingRecord.getBorrowingRecordId());
        assertEquals(activeBorrowingRecord.getUser(), savedBorrowingRecord.getUser());
        assertEquals(activeBorrowingRecord.getBook(), savedBorrowingRecord.getBook());
        assertEquals(activeBorrowingRecord.getCheckedOutOn(), savedBorrowingRecord.getCheckedOutOn());
        assertNull(savedBorrowingRecord.getReturnedOn());

        List<BorrowingRecord> all = borrowingRecordJpaRepository.findAll();
//        assertTrue(all.contains(savedBorrowingRecord));
        assertEquals(all.get(2), savedBorrowingRecord);
//        assertNull(all);
    }
}