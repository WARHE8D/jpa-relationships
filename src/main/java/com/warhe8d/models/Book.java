package com.warhe8d.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.warhe8d.exception.ErrorResponse;
import com.warhe8d.exception.LibraryExceptions;
import com.warhe8d.exception.MainException;
import jakarta.persistence.*;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Set;

@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String title;

    @Column(nullable = false, unique = true)
    private String isbn;//book number

    @Column(nullable = false)
    private Integer publicationYear;

    @ManyToMany
    @JoinTable(
            name = "book_author",
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "author_id")
    )
    private Set<Author> authors;

    @JsonIgnore
    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL)
    private List<BorrowRecord> borrowRecords;


    //handy annotation
    @PreRemove
    private void checkBorrowRecordsBeforeDelete() {
        if (borrowRecords != null && borrowRecords.stream().anyMatch(br -> br.getReturnDate() == null)) {
            throw new LibraryExceptions("Cannot delete book as it is currently borrowed.");
        }
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public Integer getPublicationYear() {
        return publicationYear;
    }

    public void setPublicationYear(Integer publicationYear) {
        this.publicationYear = publicationYear;
    }

    public Set<Author> getAuthors() {
        return authors;
    }

    public void setAuthors(Set<Author> authors) {
        this.authors = authors;
    }

    public List<BorrowRecord> getBorrowRecords() {
        return borrowRecords;
    }

    public void setBorrowRecords(List<BorrowRecord> borrowRecords) {
        this.borrowRecords = borrowRecords;
    }
}
