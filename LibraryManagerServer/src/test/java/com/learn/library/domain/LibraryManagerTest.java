package com.learn.library.domain;

import com.learn.library.data.DataTestUtil;
import com.learn.library.model.Book;
import com.learn.library.model.BorrowingRecord;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.verify;

@SpringBootTest
class LibraryManagerTest {
    @MockBean
    private UserService userService;

    @MockBean
    private BookService bookService;

    @MockBean
    private BorrowingRecordService borrowingRecordService;

    @Autowired
    private LibraryManager libraryManager;


    @Test
    void shouldAddBook() throws ExecutionException, InterruptedException {
        when(bookService.add(any())).thenReturn(new Result<>(DataTestUtil.book1));

        Book newBook = DataTestUtil.book1;
        newBook.setBookId(0);

        CompletableFuture<Result<Book>> actual = libraryManager.addBook(newBook);

        assertNotNull(actual);
        assertTrue(actual.get().isSuccess());
        assertEquals(DataTestUtil.book1, actual.get().getPayload());
    }

    @Test
    void shouldCheckOutBook() throws ExecutionException, InterruptedException {
        BorrowingRecord testRecord = new BorrowingRecord(DataTestUtil.user1, DataTestUtil.book1, LocalDate.now());
        testRecord.setBorrowingRecordId(1);
        when(borrowingRecordService.add(any())).thenReturn(new Result<>(testRecord));

        CompletableFuture<Result<BorrowingRecord>> actual = libraryManager.borrowBook(DataTestUtil.user1, DataTestUtil.book1);

        assertNotNull(actual);
        actual.get();   // Ensure method had completed before we test its results
        verify(borrowingRecordService).add(any());
        assertEquals(testRecord, actual.get().getPayload());
    }

    @Test
    void shouldReturnBook() throws ExecutionException, InterruptedException {
        BorrowingRecord closedRecord = getRecord();
        closedRecord.setReturnedOn(LocalDate.now());
        when(borrowingRecordService.findActiveRecords()).thenReturn(List.of(DataTestUtil.record1));
        when(borrowingRecordService.update(any())).thenReturn(new Result<>(closedRecord));

        CompletableFuture<Result<BorrowingRecord>> actual = libraryManager.returnBook(DataTestUtil.book1);

        assertNotNull(actual);
        actual.get();   // Ensure method had completed before we test its results
        verify(borrowingRecordService).update(any());

        BorrowingRecord actualRecord = actual.get().getPayload();
        assertNotNull(actualRecord.getReturnedOn());
        assertEquals(actualRecord.getReturnedOn(), actualRecord.getCheckedOutOn());
//        assertTrue(actualRecord.getReturnedOn().isAfter(actualRecord.getCheckedOutOn()));
    }

    private BorrowingRecord getRecord() {
        return new BorrowingRecord(DataTestUtil.user1, DataTestUtil.book1, DataTestUtil.record1.getCheckedOutOn());
    }
}