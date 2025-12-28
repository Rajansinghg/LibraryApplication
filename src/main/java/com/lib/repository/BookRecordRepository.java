package com.lib.repository;



import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lib.entity.Book;
import com.lib.entity.BookRecord;
import com.lib.entity.User;
import com.lib.enums.TransactionStatus;

@Repository
public interface BookRecordRepository extends JpaRepository<BookRecord, Long> {

	boolean existsByUserAndBookAndStatus(User user, Book book, TransactionStatus status);

	Optional<BookRecord> findByUserAndBookAndStatus(User user, Book book, TransactionStatus issued);

}
