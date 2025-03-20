package com.warhe8d.controller;

import com.warhe8d.models.BorrowRecord;
import com.warhe8d.service.BorrowRecordService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/lib/bb")
@PreAuthorize("hasAnyRole('ADMIN','LIBRARIAN','MEMBER')")
public class BorrowedRecordsController {

    private final BorrowRecordService brService;

    public BorrowedRecordsController(BorrowRecordService brService) {
        this.brService = brService;
    }

    @PostMapping("/create")
    public ResponseEntity<?> createBorrowRecord(@RequestBody BorrowRecord br) {
        Optional<BorrowRecord> borrowRecord = brService.createBorrowRecord(br);
        if(borrowRecord.isPresent()) {
            return new ResponseEntity<>(borrowRecord, HttpStatus.CREATED);
        }
        return new ResponseEntity<>("Failed to create record", HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getBorrowRecord(@PathVariable long id) {
        Optional<BorrowRecord> borrowRecord = brService.getBorrowRecord(id);
        if(borrowRecord.isPresent()) {
            return new ResponseEntity<>(borrowRecord, HttpStatus.OK);
        }
        return new ResponseEntity<>("Record not found", HttpStatus.NOT_FOUND);
    }


    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateBorrowRecord(@PathVariable long id, @RequestBody BorrowRecord br) {
        Optional<BorrowRecord> borrowRecord = brService.updateBorrowRecord(id, br);
        if(borrowRecord.isPresent()) {
            return new ResponseEntity<>(borrowRecord, HttpStatus.OK);
        }
        return new ResponseEntity<>("Failed to update record", HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteBorrowRecord(@PathVariable long id) {
        int delete = brService.deleteBorrowRecord(id);
        if(delete == 1) {
            return new ResponseEntity<>("Record deleted successfully", HttpStatus.OK);
        }
        return new ResponseEntity<>("Failed to delete record", HttpStatus.BAD_REQUEST);
    }
}
