package com.learn.library.domain;

import com.learn.library.data.BorrowingRecordRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import static org.junit.jupiter.api.Assertions.*;

class BorrowingRecordServiceTest {
    @MockBean
    BorrowingRecordRepository borrowingRecordRepository;

    @Autowired
    BorrowingRecordService borrowingRecordService;

    @Test
    void shouldFindAll() {
        Mockito.when(borrowingRecordRepository.findAll()).thenReturn();
    }

    @Test
    void shouldAddRecord() {}

    @Test
    void shouldNotAddNull() {}

    @Test
    void shouldNotAddNullUser() {}

    @Test
    void shouldNotAddUnknownUser() {}

    @Test
    void shouldNotAddInvalidUser() {}

    @Test
    void shouldNotAddNullBook() {}

    @Test
    void shouldNotAddUnknownBook() {}

    @Test
    void shouldNotAddInvalidBook() {}

    @Test
    void shouldNotAddNullCheckedOutOn() {}

    @Test
    void shouldNotAddFutureCheckedOutOn() {}

    @Test
    void shouldNotAddFutureReturnedOn() {}
}