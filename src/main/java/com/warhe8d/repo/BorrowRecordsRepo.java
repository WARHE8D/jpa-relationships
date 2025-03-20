package com.warhe8d.repo;

import com.warhe8d.models.BorrowRecord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BorrowRecordsRepo extends JpaRepository<BorrowRecord,Long> {
}
