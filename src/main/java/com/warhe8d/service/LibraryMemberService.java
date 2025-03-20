package com.warhe8d.service;

import com.warhe8d.models.LibraryMember;

import java.util.Optional;

public interface LibraryMemberService {
    Optional<LibraryMember> registerMember(LibraryMember libraryMember);
    Optional<LibraryMember> getMemberById(long id);
    Optional<LibraryMember> updateMember(long id, LibraryMember libraryMember);
    int deleteMember(long id);

}
