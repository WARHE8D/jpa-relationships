package com.warhe8d.service;

import com.warhe8d.models.Author;
import com.warhe8d.repo.AuthorRepo;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthorServiceImpl implements AuthorService {
    private final AuthorRepo authorRepo;
    public AuthorServiceImpl(AuthorRepo authorRepo) {
        this.authorRepo = authorRepo;
    }

    @Override
    public Optional<Author> createAuthor(Author author) {
        return Optional.of(authorRepo.save(author));
    }

    @Override
    public Optional<Author> updateAuthor(long id, Author author) {
        Author saveduthor = authorRepo.findById(id).orElse(null);
        if (saveduthor != null) {
            saveduthor.setBiography(author.getBiography());
            saveduthor.setName(author.getName());
            if(author.getBooks() != null) {
                saveduthor.setBooks(author.getBooks());
            }
            return Optional.of(authorRepo.save(saveduthor));
        }
        return Optional.empty();
    }

    @Override
    public int deleteAuthor(long id) {
        if(authorRepo.existsById(id)){
            authorRepo.deleteById(id);
            return 1;
        }
        return 0;
    }

    @Override
    public Optional<Author> getAuthor(long id) {
        return authorRepo.findById(id);
    }
}
