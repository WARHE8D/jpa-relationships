package com.warhe8d.controller;

import com.warhe8d.models.LibraryMember;
import com.warhe8d.service.LibraryMemberService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/lib/member")
public class LibraryMemberController {

    private LibraryMemberService libraryMemberService;

    public LibraryMemberController(LibraryMemberService libraryMemberService) {
        this.libraryMemberService = libraryMemberService;
    }

    @PostMapping("/register")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<?> register(@RequestBody LibraryMember libraryMember) {
        Optional<LibraryMember> member = libraryMemberService.registerMember(libraryMember);
        if(member.isPresent()) {
            return new ResponseEntity<>(member,HttpStatus.CREATED);
        }
        return new ResponseEntity<>("Could not create member",HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','LIBRARIAN')")
    public ResponseEntity<?> getMember(@PathVariable long id) {
        Optional<LibraryMember> lm = libraryMemberService.getMemberById(id);
        if(lm.isPresent()) {
            return new ResponseEntity<>(lm,HttpStatus.OK);
        }
        return new ResponseEntity<>("Could not find member",HttpStatus.NOT_FOUND);
    }

    @PutMapping("/update/{id}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<?> update(@PathVariable long id, @RequestBody LibraryMember libraryMember) {
        Optional<LibraryMember> member = libraryMemberService.updateMember(id,libraryMember);
        if(member.isPresent()) {
            return new ResponseEntity<>(member,HttpStatus.OK);
        }
        return new ResponseEntity<>("Failed to update member ID: "+id,HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<?> delete(@PathVariable long id) {
        int deleteMember = libraryMemberService.deleteMember(id);
        if(deleteMember == 1) {
            return new ResponseEntity<>("Member deleted",HttpStatus.OK);
        }
        return new ResponseEntity<>("Failed to delete member ID: "+id,HttpStatus.BAD_REQUEST);
    }
}
