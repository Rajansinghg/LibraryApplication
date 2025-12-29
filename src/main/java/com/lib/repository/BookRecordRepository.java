package com.lib.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.lib.dto.CategoryUsageReport;
import com.lib.entity.Book;
import com.lib.entity.BookRecord;
import com.lib.entity.User;
import com.lib.enums.TransactionStatus;

@Repository
public interface BookRecordRepository extends JpaRepository<BookRecord, Long> {

	boolean existsByUserAndBookAndStatus(User user, Book book, TransactionStatus status);

	Optional<BookRecord> findByUserAndBookAndStatus(User user, Book book, TransactionStatus issued);

	// 🔥 REPORTING QUERY (ADD THIS)
	@Query(value = """
			    SELECT
			        b.category AS category,
			        COUNT(DISTINCT br.user_id) AS usersCount,
			        (COUNT(DISTINCT br.user_id) * 100.0 /
			            (SELECT COUNT(*) FROM users)
			        ) AS percentage
			    FROM book_records br
			    JOIN books b ON br.book_id = b.id
			    WHERE br.status IN ('ISSUED', 'RETURNED')
			    GROUP BY b.category
			""", nativeQuery = true)
	List<CategoryUsageReport> getCategoryUsageReport();

	List<BookRecord> findByStatus(TransactionStatus status);

	List<BookRecord> findByUserAndStatusAndIssueDateAfter(User user, TransactionStatus status, LocalDate issueDate);

	List<BookRecord> findByIssueDateAfterAndStatusIn(LocalDate issueDate, List<TransactionStatus> statuses);

}
