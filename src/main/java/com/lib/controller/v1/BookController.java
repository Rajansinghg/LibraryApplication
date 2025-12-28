package com.lib.controller.v1;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lib.entity.Book;
import com.lib.service.BookService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/books")
public class BookController {

	private final BookService bookservice;

	@PostMapping
	ResponseEntity<Book> addBook(@RequestBody Book book) {
		return ResponseEntity.ok(bookservice.addBook(book));
	}

	@PutMapping
	ResponseEntity<Book> updateBook(@RequestBody Book book) {
		return ResponseEntity.ok(bookservice.updateBook(book));
	}

	@DeleteMapping("/{bookId}")
	ResponseEntity<String> deleteBook(@PathVariable Long bookid) {
		return bookservice.deleteBook(bookid) ? ResponseEntity.ok("Book deleted successfully")
				: ResponseEntity.badRequest().body("Book not found");

	}

	@GetMapping("/{bookId}")
	ResponseEntity<Book> getBookById(@PathVariable Long bookId) {
		return ResponseEntity.ok(bookservice.getBookById(bookId));
	}

	@GetMapping
	ResponseEntity<List<Book>> getAllBooks() {
		return ResponseEntity.ok(bookservice.getAllBooks());
	}

	// LIBRARY OPERATIONS ------->

	@PostMapping("/issue")
	ResponseEntity<String> issueBook(@RequestParam Long userId, @RequestParam Long bookId, @RequestParam int days) {
		return ResponseEntity.ok(bookservice.issueBook(userId, bookId, days));
	}

	@PostMapping("/return")
	ResponseEntity<String> returnBook(@RequestParam Long userId, @RequestParam Long bookId) {
		return ResponseEntity.ok(bookservice.returnBook(userId, bookId));
	}

	@GetMapping("/{bookId}/available")
	ResponseEntity<Boolean> isBookAvailable(Long bookId) {
		return ResponseEntity.ok(bookservice.isBookAvailable(bookId));
	}

}
