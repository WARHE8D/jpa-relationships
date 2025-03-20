package com.warhe8d.service;

import com.warhe8d.models.Book;
import com.warhe8d.repo.BookRepo;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    private final BookRepo bookRepo;
    public BookServiceImpl(BookRepo bookRepo) {
        this.bookRepo = bookRepo;
    }

    @Override
    public Optional<Book> createBook(Book book) {
        return Optional.of(bookRepo.save(book));
    }

    @Override
    public Optional<Book> updateBook(long id, Book book) {
        Book b = bookRepo.findById(id).orElse(null);
        if(b != null) {
            b.setTitle(book.getTitle());
            b.setAuthors(book.getAuthors());
            b.setIsbn(book.getIsbn());
            b.setPublicationYear(book.getPublicationYear());
            if(book.getAuthors() != null && !book.getAuthors().isEmpty()) {
                b.setBorrowRecords(book.getBorrowRecords());//book has cascade no need to manually save br
            }
            return Optional.of(bookRepo.save(b));
        }
        return Optional.empty();
    }

    @Override
    public int deleteBook(long id) {
        if(bookRepo.existsById(id)) {
            bookRepo.deleteById(id);
            return 1;
        }
        return 0;
    }

    @Override
    public Optional<Book> getBook(long id) {
        if(bookRepo.existsById(id)) {
            return bookRepo.findById(id);
        }
        return Optional.empty();
    }
}
