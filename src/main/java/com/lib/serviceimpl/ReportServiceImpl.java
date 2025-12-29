package com.lib.serviceimpl;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.lib.dto.BookTransactionReport;
import com.lib.dto.CategoryUsageReport;
import com.lib.dto.IssuedBookResponse;
import com.lib.entity.BookRecord;
import com.lib.entity.User;
import com.lib.enums.TransactionStatus;
import com.lib.exception.InvalidOperationException;
import com.lib.repository.BookRecordRepository;
import com.lib.repository.UserRepository;
import com.lib.service.ReportService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReportServiceImpl implements ReportService {

	private final BookRecordRepository bookRecordRepository;
	private final UserRepository userRepository;

	@Override
	public List<CategoryUsageReport> getCategoryUsage() {
		return bookRecordRepository.getCategoryUsageReport();
	}

	public List<IssuedBookResponse> getAllIssuedBooks() {

		return bookRecordRepository.findByStatus(TransactionStatus.ISSUED).stream().map(this::mapToDto).toList();
	}

	@Override
	public List<IssuedBookResponse> getIssuedBooksForUserInLastDays(Long userId, int days) {

		User user = userRepository.findById(userId).orElseThrow(() -> new InvalidOperationException("User not found"));
		LocalDate fromDate = LocalDate.now().minusDays(days);
		return bookRecordRepository.findByUserAndStatusAndIssueDateAfter(user, TransactionStatus.ISSUED, fromDate)
				.stream().map(this::mapToDto).toList();
	}

	private IssuedBookResponse mapToDto(BookRecord record) {
		IssuedBookResponse dto = new IssuedBookResponse();
		dto.setBookId(record.getBook().getId());
		dto.setBookName(record.getBook().getName());
		dto.setAuthor(record.getBook().getAuthor());
		dto.setCategory(record.getBook().getCategory());
		dto.setUserId(record.getUser().getId());
		dto.setUserName(record.getUser().getName());
		dto.setIssueDate(record.getIssueDate());
		dto.setReturnDate(record.getReturnDate());
		return dto;
	}

	public List<BookTransactionReport> getAllTransactionsInLastDays(int days) {

		LocalDate fromDate = LocalDate.now().minusDays(days);

		return bookRecordRepository
				.findByIssueDateAfterAndStatusIn(fromDate,
						List.of(TransactionStatus.ISSUED, TransactionStatus.RETURNED))
				.stream().map(this::mapToTransactionDto).toList();
	}
	
	private BookTransactionReport mapToTransactionDto(BookRecord record) {

	    BookTransactionReport dto = new BookTransactionReport();

	    dto.setTransactionId(record.getId());

	    dto.setBookId(record.getBook().getId());
	    dto.setBookName(record.getBook().getName());
	    dto.setAuthor(record.getBook().getAuthor());
	    dto.setCategory(record.getBook().getCategory());

	    dto.setUserId(record.getUser().getId());
	    dto.setUserName(record.getUser().getName());

	    dto.setIssueDate(record.getIssueDate());
	    dto.setReturnDate(record.getReturnDate());
	    dto.setStatus(record.getStatus());

	    return dto;
	}

}
