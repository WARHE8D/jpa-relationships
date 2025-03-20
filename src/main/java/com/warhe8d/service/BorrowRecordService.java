package com.warhe8d.service;

import com.warhe8d.models.BorrowRecord;

import java.util.Optional;

public interface BorrowRecordService {
    Optional<BorrowRecord> createBorrowRecord(BorrowRecord br);
    Optional<BorrowRecord> updateBorrowRecord(long id, BorrowRecord br);
    int deleteBorrowRecord(long id);
    Optional<BorrowRecord> getBorrowRecord(long id);
}
