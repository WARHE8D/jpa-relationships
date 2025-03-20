package com.warhe8d.repo;

import com.warhe8d.models.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepo extends JpaRepository<Author,Long> {
}
