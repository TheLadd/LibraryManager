package com.learn.library.domain;

import com.learn.library.data.BorrowingRecordRepository;
import com.learn.library.domain.ErrorMessages.BorrowingRecordErrorMessages;
import com.learn.library.model.BorrowingRecord;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;

import jakarta.validation.Validator;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Service
public class BorrowingRecordService {
    private BorrowingRecordRepository borrowingRecordRepository;
    private Validator validator;

    public BorrowingRecordService(BorrowingRecordRepository borrowingRecordRepository) {
        this.borrowingRecordRepository = borrowingRecordRepository;
        this.validator = Validation.buildDefaultValidatorFactory().getValidator();
    }

    public List<BorrowingRecord> findAll() {
        return borrowingRecordRepository.findAll();
    }

    public List<BorrowingRecord> findActiveRecords() {
        return borrowingRecordRepository.findByReturnedOnIsNull();
    }

    public Result<BorrowingRecord> add(BorrowingRecord record) {
        Result<BorrowingRecord> result = new Result<>();
        if (record == null) {
            result.addMessage(BorrowingRecordErrorMessages.NULL);
            return result;
        }

        Set<ConstraintViolation<BorrowingRecord>> violations = validator.validate(record);
        if (!violations.isEmpty()) {
            result.addMessages(violations.stream().map(ConstraintViolation::getMessage).toList());
        }
        else {
            BorrowingRecord savedRecord = borrowingRecordRepository.save(record);
            if (savedRecord != null) {
                result.setPayload(savedRecord);
            }
            else {
                result.addMessage(BorrowingRecordErrorMessages.DATABASE_ERROR, ResultType.DATABASE_FAILURE);
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
