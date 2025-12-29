package com.lib.service;

import java.util.List;

import com.lib.dto.BookTransactionReport;
import com.lib.dto.CategoryUsageReport;
import com.lib.dto.IssuedBookResponse;


public interface ReportService {
	List<CategoryUsageReport> getCategoryUsage();

	List<IssuedBookResponse> getAllIssuedBooks();

    List<IssuedBookResponse> getIssuedBooksForUserInLastDays(Long userId, int days);
    
    public List<BookTransactionReport> getAllTransactionsInLastDays(int days);
}
