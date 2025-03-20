package com.warhe8d.models;

import jakarta.persistence.*;
import jakarta.persistence.Id;

import java.util.Date;

@Entity
public class BorrowRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date borrowDate;

    @Temporal(TemporalType.DATE)
    private Date returnDate;

    @ManyToOne
    @JoinColumn(name = "library_member_id")
    private LibraryMember libraryMember;

    @ManyToOne
    @JoinColumn(name = "book_id")
    private Book book;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getBorrowDate() {
        return borrowDate;
    }

    public void setBorrowDate(Date borrowDate) {
        this.borrowDate = borrowDate;
    }

    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    public LibraryMember getLibraryMember() {
        return libraryMember;
    }

    public void setLibraryMember(LibraryMember libraryMember) {
        this.libraryMember = libraryMember;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }
}