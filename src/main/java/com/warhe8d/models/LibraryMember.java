package com.warhe8d.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

@Entity
public class LibraryMember {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    @Temporal(TemporalType.DATE)
    private Date membershipDate;

    @JsonIgnore
    @OneToOne(mappedBy = "libraryMember", cascade = CascadeType.ALL)
    private MembershipCard membershipCard;

    @JsonIgnore
    @OneToMany(mappedBy = "libraryMember", cascade = CascadeType.ALL)
    private List<BorrowRecord> borrowedBooks;

    @PreRemove
    private void checkBorrowRecordsBeforeDelete() {
        if (borrowedBooks != null && borrowedBooks.stream().anyMatch(br -> br.getReturnDate() == null)) {
            throw new IllegalStateException("Cannot delete member as they have unreturned books.");
        }
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getMembershipDate() {
        return membershipDate;
    }

    public void setMembershipDate(Date membershipDate) {
        this.membershipDate = membershipDate;
    }

    public MembershipCard getMembershipCard() {
        return membershipCard;
    }

    public void setMembershipCard(MembershipCard membershipCard) {
        this.membershipCard = membershipCard;
    }

    public List<BorrowRecord> getBorrowedBooks() {
        return borrowedBooks;
    }

    public void setBorrowedBooks(List<BorrowRecord> borrowedBooks) {
        this.borrowedBooks = borrowedBooks;
    }
}
