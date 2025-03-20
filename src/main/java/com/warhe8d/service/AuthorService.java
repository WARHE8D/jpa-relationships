package com.warhe8d.service;

import com.warhe8d.models.Author;

import java.util.Optional;

public interface AuthorService {
    Optional<Author> createAuthor(Author author);
    Optional<Author> updateAuthor(long id, Author author);
    int deleteAuthor(long id);
    Optional<Author> getAuthor(long id);
}
