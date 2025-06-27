package com.learn.library.domain.ErrorMessages;

public class BorrowingRecordErrorMessages {
    public static String UNKNOWN_ID(int id) { return "Unable to find Borrowing Record with ID: " + id; }
    public static final String NON_POSITIVE_ID = "Borrowing Record ID of record to be updated must be positive";
}
