package com.warhe8d.repo;

import com.warhe8d.models.LibraryMember;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LibraryMemberRepo extends JpaRepository<LibraryMember,Long> {
}
