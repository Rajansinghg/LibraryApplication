package com.lib.controller.v1;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.lib.dto.BookTransactionReport;
import com.lib.dto.CategoryUsageReport;
import com.lib.dto.IssuedBookResponse;
import com.lib.service.ReportService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/v1/reports")
@RequiredArgsConstructor
public class ReportController {

	private final ReportService reportService;

	@GetMapping("/category-usage")
	public ResponseEntity<List<CategoryUsageReport>> getCategoryUsage() {
		return ResponseEntity.ok(reportService.getCategoryUsage());
	}

	@GetMapping("/issued-books")
	public List<IssuedBookResponse> getAllIssuedBooks() {
		return reportService.getAllIssuedBooks();
	}

	@GetMapping("/users/{userId}/issued-books")
	public List<IssuedBookResponse> getIssuedBooksForUser(@PathVariable Long userId, @RequestParam int days) {

		return reportService.getIssuedBooksForUserInLastDays(userId, days);
	}

	@GetMapping("/transactions")
	public List<BookTransactionReport> getAllTransactions(@RequestParam int days) {

		return reportService.getAllTransactionsInLastDays(days);
	}

}
