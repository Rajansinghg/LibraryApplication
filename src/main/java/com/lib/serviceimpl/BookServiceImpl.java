package com.lib.serviceimpl;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.lib.entity.Book;
import com.lib.entity.BookRecord;
import com.lib.entity.User;
import com.lib.enums.BookStatus;
import com.lib.enums.TransactionStatus;
import com.lib.repository.BookRecordRepository;
import com.lib.repository.BookRepository;
import com.lib.repository.UserRepository;
import com.lib.service.BookService;
import com.lib.service.MembershipService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BookServiceImpl implements BookService {

	private final BookRecordRepository bookRecordRepository;
	private final BookRepository bookRepository;
	private final UserRepository userRepository;
	private final MembershipService membershipService;

	@Override
	public Book addBook(Book book) {
		book.setAvailableQuantity(book.getTotalQuantity());
		book.setStatus(BookStatus.AVAILABLE);
		return bookRepository.save(book);
	}

	@Override
	public Book updateBook(Book book) {
		Book existing = bookRepository.findById(book.getId()).orElseThrow(() -> new RuntimeException("Book not found"));

		existing.setName(book.getName());
		existing.setAuthor(book.getAuthor());
		existing.setCategory(book.getCategory());
		existing.setContent(book.getContent());
		existing.setTotalQuantity(book.getTotalQuantity());

		// adjust available quantity safely
		if (existing.getAvailableQuantity() > book.getTotalQuantity()) {
			existing.setAvailableQuantity(book.getTotalQuantity());
		}

		return bookRepository.save(existing);
	}

	@Override
	public boolean deleteBook(Long bookId) {
		if (!bookRepository.existsById(bookId)) {
			return false;
		}
		bookRepository.deleteById(bookId);
		return true;
	}

	@Override
	public Book getBookById(Long id) {

		return bookRepository.findById(id).orElseThrow(() -> new RuntimeException("Book not found"));
	}

	@Override
	public List<Book> getAllBooks() {

		return bookRepository.findAll();
	}

	@Override
	public String issueBook(Long userId, Long bookId, int days) {

		User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));

		Book book = bookRepository.findById(bookId).orElseThrow(() -> new RuntimeException("Book not found"));

		if (!membershipService.isMembershipValid(user)) {
			return "Membership expired. Please renew.";
		}

		if (book.getAvailableQuantity() <= 0) {
			return "Book not available";
		}

		boolean alreadyIssued = bookRecordRepository.existsByUserAndBookAndStatus(user, book, TransactionStatus.ISSUED);

		if (alreadyIssued) {
			return "User already has this book";
		}

		BookRecord record = new BookRecord();
		record.setUser(user);
		record.setBook(book);
		record.setIssueDate(LocalDate.now());
		record.setReturnDate(LocalDate.now().plusDays(days));
		record.setStatus(TransactionStatus.ISSUED);

		book.setAvailableQuantity(book.getAvailableQuantity() - 1);
		if (book.getAvailableQuantity() == 0) {
			book.setStatus(BookStatus.NOT_AVAILABLE);
		}

		bookRecordRepository.save(record);
		bookRepository.save(book);

		return "Book issued successfully";
	}

	@Override
	public String returnBook(Long userId, Long bookId) {
		User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));

		Book book = bookRepository.findById(bookId).orElseThrow(() -> new RuntimeException("Book not found"));

		BookRecord record = bookRecordRepository.findByUserAndBookAndStatus(user, book, TransactionStatus.ISSUED)
				.orElseThrow(() -> new RuntimeException("No active issue record"));

		record.setStatus(TransactionStatus.RETURNED);
		record.setReturnDate(LocalDate.now());

		book.setAvailableQuantity(book.getAvailableQuantity() + 1);
		book.setStatus(BookStatus.AVAILABLE);

		bookRecordRepository.save(record);
		bookRepository.save(book);

		return "Book returned successfully";

	}

	@Override
	public boolean isBookAvailable(Long bookId) {
		Book book = bookRepository.findById(bookId).orElseThrow(() -> new RuntimeException("Book not found"));

		return book.getAvailableQuantity() > 0;
	}

}
