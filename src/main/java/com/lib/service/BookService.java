package com.lib.service;

import java.util.List;

import com.lib.entity.Book;
		

public interface BookService {
	Book addBook(Book book);

	Book updateBook(Book book);

	boolean deleteBook(Long Bookid);

	Book getBookById(Long id);

	List<Book> getAllBooks();

	String issueBook(Long userId, Long bookId, int days);

	String returnBook(Long userId, Long bookId);

	boolean isBookAvailable(Long bookId);
}
