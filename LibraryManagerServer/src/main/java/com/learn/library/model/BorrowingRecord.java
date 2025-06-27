package com.learn.library.model;

import com.learn.library.domain.ErrorMessages.BorrowingRecordErrorMessages;
import com.learn.library.domain.ErrorMessages.UserErrorMessage;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;

import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "borrowing_record")
public class BorrowingRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int borrowingRecordId;

//    @ManyToOne(fetch = FetchType.LAZY)
    @ManyToOne(fetch = FetchType.EAGER)     // Tests don't pass if we're lazy fetching. I should do more research on this.
    @JoinColumn(name = "user_id")
    @NotNull(message = BorrowingRecordErrorMessages.NULL_USER)
    private User user;

//    @ManyToOne(fetch = FetchType.LAZY)
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "book_id")
    @NotNull(message = BorrowingRecordErrorMessages.NULL_BOOK)
    private Book book;

    @Column(name = "checked_out_on")
    @NotNull(message = BorrowingRecordErrorMessages.NULL_CHECKOUT_OUT_ON)
    @PastOrPresent(message = BorrowingRecordErrorMessages.FUTURE_CHECKED_OUT_ON)
    private LocalDate checkedOutOn;

    @Column(name = "returned_on")
    @PastOrPresent(message = BorrowingRecordErrorMessages.FUTURE_RETURNED_ON)
    private LocalDate returnedOn;

    public BorrowingRecord() {}

    // Complete borrowing record
    public BorrowingRecord(User user, Book book, LocalDate checkedOutOn, LocalDate returnedOn) {
        this.user = user;
        this.book = book;
        this.checkedOutOn = checkedOutOn;
        this.returnedOn = returnedOn;
    }

    // Active borrowing record
    public BorrowingRecord(User user, Book book, LocalDate checkedOutOn) {
        this.user = user;
        this.book = book;
        this.checkedOutOn = checkedOutOn;
    }

    public int getBorrowingRecordId() {
        return borrowingRecordId;
    }


    public User getUser() {
        return user;
    }

    public Book getBook() {
        return book;
    }

    public LocalDate getCheckedOutOn() {
        return checkedOutOn;
    }

    public LocalDate getReturnedOn() { return returnedOn; }

    public void setBorrowingRecordId(int borrowingRecordId) { this.borrowingRecordId = borrowingRecordId; }

    public void setUser(User user) {
        this.user = user;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public void setCheckedOutOn(LocalDate checkedOutOn) {
        this.checkedOutOn = checkedOutOn;
    }

    public void setReturnedOn(LocalDate returnedOn) {
        this.returnedOn = returnedOn;
    }

    @Override
    public String toString() {
        return "BorrowingRecord{" +
                "borrowingRecordId=" + borrowingRecordId +
                ", user=" + user +
                ", book=" + book +
                ", checkedOutOn=" + checkedOutOn +
                ", returnedOn=" + returnedOn +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof BorrowingRecord that)) return false;
        return borrowingRecordId == that.borrowingRecordId && Objects.equals(user, that.user) && Objects.equals(book, that.book) && Objects.equals(checkedOutOn, that.checkedOutOn) && Objects.equals(returnedOn, that.returnedOn);
    }

    @Override
    public int hashCode() {
        return Objects.hash(borrowingRecordId, user, book, checkedOutOn, returnedOn);
    }
}