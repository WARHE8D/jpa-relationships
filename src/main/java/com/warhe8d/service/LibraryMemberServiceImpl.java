package com.warhe8d.service;

import com.warhe8d.models.LibraryMember;
import com.warhe8d.repo.LibraryMemberRepo;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LibraryMemberServiceImpl implements LibraryMemberService {
    private LibraryMemberRepo libraryMemberRepo;

    public LibraryMemberServiceImpl(LibraryMemberRepo libraryMemberRepo) {
        this.libraryMemberRepo = libraryMemberRepo;
    }

    @Override
    public Optional<LibraryMember> registerMember(LibraryMember libraryMember) {
        //add borrowd books and membership
        return Optional.of(libraryMemberRepo.save(libraryMember));
    }


    @Override
    public Optional<LibraryMember> getMemberById(long id) {
        return libraryMemberRepo.findById(id);
    }

    @Override
    public Optional<LibraryMember> updateMember(long id, LibraryMember libraryMember) {
        //add borrowd books and membership
        Optional<LibraryMember> lm = libraryMemberRepo.findById(id);
        if(lm.isPresent()) {
            LibraryMember updatedMember = lm.get();
            updatedMember.setName(libraryMember.getName());
            updatedMember.setEmail(libraryMember.getEmail());
            return Optional.of(libraryMemberRepo.save(updatedMember));
        }
        return Optional.empty();
    }

    @Override
    public int deleteMember(long id) {
        Optional<LibraryMember> lm = libraryMemberRepo.findById(id);
        if(lm.isPresent()) {
//            checkBorrowRecordsBeforeDelete in LibraryMember automatically checks borrows records before delete.
            libraryMemberRepo.delete(lm.get());
            return 1;
        }
        return 0;
    }

}
