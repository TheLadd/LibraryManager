package com.learn.library.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "borrowing_record")
public class BorrowingRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int borrowingRecordId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id")
    private Book book;

    @Column(name = "checked_out_on")
    private LocalDate checkedOutOn;

    @Column(name = "returned_on")
    private LocalDate returnedOn;

    public BorrowingRecord() {}

    public BorrowingRecord(Member member, Book book, LocalDate checkedOutOn, LocalDate returnedOn) {
        this.member = member;
        this.book = book;
        this.checkedOutOn = checkedOutOn;
        this.returnedOn = returnedOn;
    }

    public int getBorrowingRecordId() {
        return borrowingRecordId;
    }

    public Member getMember() {
        return member;
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

    public void setMember(Member member) {
        this.member = member;
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
}