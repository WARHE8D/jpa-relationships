package com.warhe8d.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.util.Date;

@Entity
public class MembershipCard {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String cardNumber;

    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date issueDate;

    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date expiryDate;

    @OneToOne
    @JoinColumn(name = "libraryMemberId")
    private LibraryMember libraryMember;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public Date getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(Date issueDate) {
        this.issueDate = issueDate;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }

    public LibraryMember getLibraryMember() {
        return libraryMember;
    }

    public void setLibraryMember(LibraryMember libraryMember) {
        this.libraryMember = libraryMember;
    }
}
