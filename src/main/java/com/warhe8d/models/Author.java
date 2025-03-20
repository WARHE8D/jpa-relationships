package com.warhe8d.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.warhe8d.exception.LibraryExceptions;
import jakarta.persistence.*;

import java.util.Set;

@Entity
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column
    private String biography;

    @JsonIgnore
    @ManyToMany(mappedBy = "authors")
    private Set<Book> books;

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

    public String getBiography() {
        return biography;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }

    public Set<Book> getBooks() {
        return books;
    }

    public void setBooks(Set<Book> books) {
        this.books = books;
    }

    @PreRemove
    private void checkBooksBeforeDelete() {
        if(books != null && !books.isEmpty()) {
            throw new LibraryExceptions("Author cannot be deleted because some books are linked");
        }
    }
}
