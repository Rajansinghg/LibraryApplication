package com.lib.dto;

import java.time.LocalDate;

import com.lib.enums.TransactionStatus;

import lombok.Data;

@Data
public class BookTransactionReport {

    private Long transactionId;

    private Long bookId;
    private String bookName;
    private String author;
    private String category;

    private Long userId;
    private String userName;

    private LocalDate issueDate;
    private LocalDate returnDate;

    private TransactionStatus status;
}
