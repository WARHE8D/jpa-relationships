package com.warhe8d.controller;

import com.warhe8d.models.Author;
import com.warhe8d.service.AuthorService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/lib/author")
@PreAuthorize("hasAnyRole('ADMIN','LIBRARIAN')")
public class AuthorController {

    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @PostMapping("/create")
    public ResponseEntity<?> createAuthor(@RequestBody Author author) {
        Optional<Author> auth = authorService.createAuthor(author);
        if(auth.isPresent()) {
            return new ResponseEntity<>(auth.get(), HttpStatus.CREATED);
        }
        return new ResponseEntity<>("Failed to create author",HttpStatus.BAD_REQUEST);
    }


    @GetMapping("/{id}")
    public ResponseEntity<?> getAuthorById(@PathVariable long id) {
        Optional<Author> auth = authorService.getAuthor(id);
        if(auth.isPresent()) {
            return new ResponseEntity<>(auth.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>("Author not found",HttpStatus.NOT_FOUND);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateAuthor(@PathVariable long id, @RequestBody Author author) {
        Optional<Author> auth = authorService.updateAuthor(id, author);
        if(auth.isPresent()) {
            return new ResponseEntity<>(auth.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>("Failed to update author",HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteAuthor(@PathVariable long id) {
        int delete = authorService.deleteAuthor(id);
        if(delete == 1) {
            return new ResponseEntity<>("Author deleted successfully",HttpStatus.OK);
        }
        return new ResponseEntity<>("Failed to delete author",HttpStatus.BAD_REQUEST);
    }
}
