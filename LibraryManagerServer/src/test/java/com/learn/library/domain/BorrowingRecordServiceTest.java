package com.learn.library.domain;

import com.learn.library.data.BorrowingRecordRepository;
import com.learn.library.data.DataTestUtil;
import com.learn.library.domain.ErrorMessages.BorrowingRecordErrorMessages;
import com.learn.library.model.BorrowingRecord;
import com.learn.library.model.User;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class BorrowingRecordServiceTest {
    @MockBean(name = "borrowingRecordJpaRepository")
    BorrowingRecordRepository borrowingRecordRepository;

    @Autowired
    BorrowingRecordService borrowingRecordService;

    @Test
    void shouldFindAll() {
        Mockito.when(borrowingRecordRepository.findAll()).thenReturn(DataTestUtil.records);

        List<BorrowingRecord> actual = borrowingRecordService.findAll();

        assertNotNull(actual);
        assertEquals(DataTestUtil.records.size(), actual.size());
    }

    @Test
    void shouldAddActiveRecord() {
        BorrowingRecord activeRecord = newActiveRecord();
        activeRecord.setBorrowingRecordId(3);
        Mockito.when(borrowingRecordRepository.save(Mockito.any())).thenReturn(activeRecord);

        Result<BorrowingRecord> actual = borrowingRecordService.add(newActiveRecord());

        assertNotNull(actual);
        assertTrue(actual.isSuccess());
        assertEquals(activeRecord, actual.getPayload());
    }

    @Test
    void shouldAddClosedRecord() {
        BorrowingRecord closedRecord = newClosedRecord();
        closedRecord.setBorrowingRecordId(3);
        Mockito.when(borrowingRecordRepository.save(Mockito.any())).thenReturn(closedRecord);

        Result<BorrowingRecord> actual = borrowingRecordService.add(newClosedRecord());

        assertNotNull(actual);
        assertTrue(actual.isSuccess());
        assertEquals(closedRecord, actual.getPayload());

    }

    @Test
    void shouldNotAddNull() {
        Result<BorrowingRecord> actual = borrowingRecordService.add(null);

        assertNotNull(actual);
        assertFalse(actual.isSuccess());
        assertEquals(1, actual.getMessages().size());
        assertTrue(actual.getMessages().contains(BorrowingRecordErrorMessages.NULL));
    }

    @Test
    void shouldNotAddNullUser() {
        BorrowingRecord hasNullUser = newActiveRecord();
        hasNullUser.setUser(null);

        Result<BorrowingRecord> actual = borrowingRecordService.add(hasNullUser);

        assertNotNull(actual);
        assertFalse(actual.isSuccess());
        assertEquals(1, actual.getMessages().size());
        assertTrue(actual.getMessages().contains(BorrowingRecordErrorMessages.NULL_USER));
    }

    @Test
    void shouldNotAddUnknownUser() {
        BorrowingRecord recordWithUnknownUser = newActiveRecord();
        recordWithUnknownUser.setUser(DataTestUtil.newUser);

        Result<BorrowingRecord> actual = borrowingRecordService.add(recordWithUnknownUser);

        assertNotNull(actual);
        assertFalse(actual.isSuccess());
        assertEquals(1, actual.getMessages().size());
        assertTrue(actual.getMessages().contains(BorrowingRecordErrorMessages.DATABASE_ERROR));
    }

    @Test
    void shouldNotAddInvalidUser() {
        BorrowingRecord recordWithInvalidUser = newActiveRecord();
        recordWithInvalidUser.setUser(DataTestUtil.invalidUser);

        Result<BorrowingRecord> actual = borrowingRecordService.add(recordWithInvalidUser);

        assertNotNull(actual);
        assertFalse(actual.isSuccess());
        assertEquals(1, actual.getMessages().size());
        assertTrue(actual.getMessages().contains(BorrowingRecordErrorMessages.DATABASE_ERROR));
    }

    @Test
    void shouldNotAddNullBook() {
        BorrowingRecord recordWithNullBook = newActiveRecord();
        recordWithNullBook.setBook(null);

        Result<BorrowingRecord> actual = borrowingRecordService.add(recordWithNullBook);

        assertNotNull(actual);
        assertFalse(actual.isSuccess());
        assertEquals(1, actual.getMessages().size());
        assertTrue(actual.getMessages().contains(BorrowingRecordErrorMessages.NULL_BOOK));
    }

    @Test
    void shouldNotAddUnknownBook() {
        BorrowingRecord recordWithUnknownBook = newActiveRecord();
        recordWithUnknownBook.setBook(DataTestUtil.newBook);

        Result<BorrowingRecord> actual = borrowingRecordService.add(recordWithUnknownBook);

        assertNotNull(actual);
        assertFalse(actual.isSuccess());
        assertEquals(1, actual.getMessages().size());
        assertTrue(actual.getMessages().contains(BorrowingRecordErrorMessages.DATABASE_ERROR));
    }

    @Test
    void shouldNotAddInvalidBook() {
        BorrowingRecord recordWithUnknownBook = newActiveRecord();
        recordWithUnknownBook.setBook(DataTestUtil.invalidBook);

        Result<BorrowingRecord> actual = borrowingRecordService.add(recordWithUnknownBook);

        assertNotNull(actual);
        assertFalse(actual.isSuccess());
        assertEquals(1, actual.getMessages().size());
        assertTrue(actual.getMessages().contains(BorrowingRecordErrorMessages.DATABASE_ERROR));
    }

    @Test
    void shouldNotAddNullCheckedOutOn() {
        BorrowingRecord recordWithNullCheckedOutOn = newActiveRecord();
        recordWithNullCheckedOutOn.setCheckedOutOn(null);

        Result<BorrowingRecord> actual = borrowingRecordService.add(recordWithNullCheckedOutOn);

        assertNotNull(actual);
        assertFalse(actual.isSuccess());
        assertEquals(1, actual.getMessages().size());
        assertTrue(actual.getMessages().contains(BorrowingRecordErrorMessages.NULL_CHECKOUT_OUT_ON));
    }

    @Test
    void shouldNotAddFutureCheckedOutOn() {
        BorrowingRecord recordWithNullCheckedOutOn = newActiveRecord();
        recordWithNullCheckedOutOn.setCheckedOutOn(LocalDate.now().plusDays(1));

        Result<BorrowingRecord> actual = borrowingRecordService.add(recordWithNullCheckedOutOn);

        assertNotNull(actual);
        assertFalse(actual.isSuccess());
        assertEquals(1, actual.getMessages().size());
        assertTrue(actual.getMessages().contains(BorrowingRecordErrorMessages.FUTURE_CHECKED_OUT_ON));
    }

    @Test
    void shouldNotAddFutureReturnedOn() {
        BorrowingRecord recordWithNullReturnedOn = newActiveRecord();
        recordWithNullReturnedOn.setReturnedOn(LocalDate.now().plusDays(1));

        Result<BorrowingRecord> actual = borrowingRecordService.add(recordWithNullReturnedOn);

        assertNotNull(actual);
        assertFalse(actual.isSuccess());
        assertEquals(1, actual.getMessages().size());
        assertTrue(actual.getMessages().contains(BorrowingRecordErrorMessages.FUTURE_RETURNED_ON));
    }

    private BorrowingRecord newActiveRecord() {
        return new BorrowingRecord(DataTestUtil.user1, DataTestUtil.book1, LocalDate.of(2024, 9, 20));
    }

    private BorrowingRecord newClosedRecord() {
        return new BorrowingRecord(DataTestUtil.user1, DataTestUtil.book1, LocalDate.of(2024, 11, 20), LocalDate.of(2024, 12, 5));
    }
}