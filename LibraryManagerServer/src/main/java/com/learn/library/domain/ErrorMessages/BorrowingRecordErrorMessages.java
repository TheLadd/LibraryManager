package com.learn.library.domain.ErrorMessages;

public class BorrowingRecordErrorMessages {
    public static final String NULL = "Borrowing Record cannot be null";
    public static final String NULL_USER = "Borrowing Record User must not be null";
    public static final String NULL_BOOK = "Borrowing Record Book must not be null";
    public static final String NULL_CHECKOUT_OUT_ON = "Borrowing Record checkedOutOn must not be null";
    public static final String FUTURE_CHECKED_OUT_ON = "Borrowing Record checkedOutOn must not be in the future";
    public static final String FUTURE_RETURNED_ON = "Borrowing Record returnedOn must not be in the future";

    public static String UNKNOWN_ID(int id) { return "Unable to find Borrowing Record with ID: " + id; }

    public static final String DATABASE_ERROR = "Server-side database issue; ensure that this Borrowing Record's User and Book exist";
}
