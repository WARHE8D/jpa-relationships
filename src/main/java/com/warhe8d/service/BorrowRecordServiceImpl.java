package com.warhe8d.service;

import com.warhe8d.models.Book;
import com.warhe8d.models.BorrowRecord;
import com.warhe8d.models.LibraryMember;
import com.warhe8d.repo.BorrowRecordsRepo;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BorrowRecordServiceImpl implements BorrowRecordService {

    private final BorrowRecordsRepo brRepo;
    private final BookService bookService;
    private final LibraryMemberService memberService;

    public BorrowRecordServiceImpl(BorrowRecordsRepo brRepo, BookService bookService, LibraryMemberService memberService) {
        this.brRepo = brRepo;
        this.bookService = bookService;
        this.memberService = memberService;
    }


    @Override
    public Optional<BorrowRecord> createBorrowRecord(BorrowRecord br) {
        if(br.getBook()!=null && br.getBook().getId()!=null){
            Optional<Book> b = bookService.getBook(br.getBook().getId());
            b.ifPresent(br::setBook);
        }
        if(br.getLibraryMember()!=null && br.getLibraryMember().getId()!=null){
            Optional<LibraryMember> lm = memberService.getMemberById(br.getLibraryMember().getId());
            lm.ifPresent(br::setLibraryMember);
        }
        return Optional.of(brRepo.save(br));
    }

    @Override
    public Optional<BorrowRecord> updateBorrowRecord(long id, BorrowRecord br) {
        BorrowRecord borrowRecord = brRepo.findById(id).orElse(null);
        if (borrowRecord != null) {
            borrowRecord.setBorrowDate(br.getBorrowDate());
            borrowRecord.setReturnDate(br.getReturnDate());
            if(br.getLibraryMember() != null && br.getLibraryMember().getId() != 0) {
                borrowRecord.setLibraryMember(br.getLibraryMember());
            }
            if(br.getBook() != null && br.getBook().getId() != 0) {
                borrowRecord.setBook(br.getBook());
            }
            return Optional.of(brRepo.save(borrowRecord));
        }
        return Optional.empty();
    }

    @Override
    public int deleteBorrowRecord(long id) {
        if(brRepo.existsById(id)) {
            brRepo.deleteById(id);
            return 1;
        }
        return 0;
    }

    @Override
    public Optional<BorrowRecord> getBorrowRecord(long id) {
        if(brRepo.existsById(id)) {
            return brRepo.findById(id);
        }
        return Optional.empty();
    }
}
