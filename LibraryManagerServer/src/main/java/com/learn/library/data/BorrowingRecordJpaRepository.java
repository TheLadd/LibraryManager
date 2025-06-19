package com.learn.library.data;

import com.learn.library.model.BorrowingRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BorrowingRecordJpaRepository extends BorrowingRecordRepository, JpaRepository<BorrowingRecord, Integer> {
    List<BorrowingRecord> findAll();
}
