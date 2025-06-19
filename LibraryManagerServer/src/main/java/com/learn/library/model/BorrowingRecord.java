package com.learn.library.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "borrowing_record")
public class BorrowingRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int borrowingRecordId;

//    @ManyToOne(fetch = FetchType.LAZY)
    @ManyToOne(fetch = FetchType.EAGER)     // Tests don't pass if we're lazy fetching. I should do more research on this.
    @JoinColumn(name = "user_id")
    private User user;

//    @ManyToOne(fetch = FetchType.LAZY)
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "book_id")
    private Book book;

    @Column(name = "checked_out_on")
    private LocalDate checkedOutOn;

    @Column(name = "returned_on")
    private LocalDate returnedOn;

    public BorrowingRecord() {}

    public BorrowingRecord(Member user, Book book, LocalDate checkedOutOn, LocalDate returnedOn) {
        this.user = user;
        this.book = book;
        this.checkedOutOn = checkedOutOn;
        this.returnedOn = returnedOn;
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

    public LocalDate getReturnedOn() {
        return returnedOn;
    }

    public void setUser(Member user) {
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
}