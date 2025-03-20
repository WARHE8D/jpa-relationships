package com.warhe8d.controller;

import com.warhe8d.models.Book;
import com.warhe8d.service.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/lib/book")
public class BookController {
    private final BookService bookService;
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping("/create")
    public ResponseEntity<?> createBook(@RequestBody Book book) {
            Optional<Book> b = bookService.createBook(book);
            if(b.isPresent()) {
                return new ResponseEntity<>(b.get(), HttpStatus.CREATED);
            }
            return new ResponseEntity<>("Failed to create book",HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getBook(@PathVariable long id) {
        Optional<Book> b = bookService.getBook(id);
        if(b.isPresent()) {
            return new ResponseEntity<>(b.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>("Book Not Found",HttpStatus.NOT_FOUND);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateBook(@PathVariable long id, @RequestBody Book book) {
        Optional<Book> b = bookService.updateBook(id, book);
        if(b.isPresent()) {
            return new ResponseEntity<>(b.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>("Failed to update book",HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteBook(@PathVariable long id) {
        int delete = bookService.deleteBook(id);
        if(delete == 1) {
            return new ResponseEntity<>("Deleted",HttpStatus.OK);
        }
        return new ResponseEntity<>("Failed to Delete Book",HttpStatus.BAD_REQUEST);
    }

}
