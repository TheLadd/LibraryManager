package com.learn.library.data;

import com.learn.library.model.BorrowingRecord;

import java.time.LocalDate;
import java.util.List;

public interface BorrowingRecordRepository {
    List<BorrowingRecord> findAll();
    List<BorrowingRecord> findByReturnedOnIsNull();
    BorrowingRecord findByBorrowingRecordId(int borrowingRecordId);
    BorrowingRecord save(BorrowingRecord record);
}
