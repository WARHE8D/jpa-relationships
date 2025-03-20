package com.warhe8d.service;

import com.warhe8d.models.Book;

import java.util.Optional;

public interface BookService {
    Optional<Book> createBook(Book book);
    Optional<Book> updateBook(long id, Book book);
    int deleteBook(long id);
    Optional<Book> getBook(long id);
}
