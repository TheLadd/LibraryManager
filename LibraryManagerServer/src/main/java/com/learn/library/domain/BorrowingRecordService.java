package com.learn.library.domain;

import com.learn.library.data.BorrowingRecordRepository;
import com.learn.library.domain.ErrorMessages.BorrowingRecordErrorMessages;
import com.learn.library.model.BorrowingRecord;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;

import jakarta.validation.Validator;

import java.util.Set;

public class BorrowingRecordService {
    private BorrowingRecordRepository borrowingRecordRepository;
    private Validator validator;

    public BorrowingRecordService(BorrowingRecordRepository borrowingRecordRepository) {
        this.borrowingRecordRepository = borrowingRecordRepository;
        this.validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    public Result<BorrowingRecord> add(BorrowingRecord record) {
        Set<ConstraintViolation<BorrowingRecord>> violations = validator.validate(record);

        Result<BorrowingRecord> result;
        if (!violations.isEmpty()) {
            result = new Result<>(violations);
        }
        else {
            BorrowingRecord savedRecord = borrowingRecordRepository.save(record);
            if (savedRecord != null) {
                result = new Result<>(savedRecord);
            }
            else {
                result = new Result<>();
                result.setType(ResultType.DATABASE_FAILURE);
            }
        }

        return result;
    }

    public Result<BorrowingRecord> update(BorrowingRecord record) {
        // Do update specific validation
        if (borrowingRecordRepository.findByBorrowingRecordId(record.getBorrowingRecordId()) == null) {
            Result<BorrowingRecord> result = new Result<>();
            result.addMessage(BorrowingRecordErrorMessages.UNKNOWN_ID(record.getBorrowingRecordId()));
            return result;
        }

        // If it exists, our .add() logic will handle it (since it leverages JpaRepository.save())
        return add(record);
    }
}
