package com.lib.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lib.entity.BookRecord;

@Repository
public interface BookRecordRepository extends JpaRepository<BookRecord, Long> {

}
