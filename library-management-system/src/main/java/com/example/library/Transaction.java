package com.example.library;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity

public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long bookId;
    private Long userId;
    private LocalDate borrowDate;
    private LocalDate returnDate;

    public Long getId () {
        return id;
    }
    public void setId (Long id) {
        this.id = id;
    }
    public Long getBookId () {
        return bookId;
    }
    public void setBookId (Long bookId) {
        this.bookId = bookId;
    }
    public Long getUserId () {
        return userId;
    }
    public void setUserId (Long userId) {
        this.userId = userId;
    }
    public LocalDate getBorrowDate () {
        return borrowDate;
    }
    public void setBorrowDate (LocalDate borrowDate) {
        this.borrowDate = borrowDate;
    }
    public LocalDate getReturnDate () {
        return returnDate;
    }
    public void setReturnDate (LocalDate returnDate) {
        this.returnDate = returnDate;
    }
}
