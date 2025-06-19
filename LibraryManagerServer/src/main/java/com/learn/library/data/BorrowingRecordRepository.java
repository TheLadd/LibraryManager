package com.learn.library.data;

import com.learn.library.model.BorrowingRecord;

import java.util.List;

public interface BorrowingRecordRepository {
    List<BorrowingRecord> findAll();
}
