package com.lib.dto;


import java.time.LocalDate;
import lombok.Data;

@Data
public class IssuedBookResponse {

    private Long bookId;
    private String bookName;
    private String author;
    private String category;

    private Long userId;
    private String userName;

    private LocalDate issueDate;
    private LocalDate returnDate;
}
