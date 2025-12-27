package com.lib.entity;

import java.time.LocalDate;

import com.lib.enums.TransactionStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "book_records")
public class BookRecord {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@ManyToOne
	private User user;

	@ManyToOne
	private Book book;

	@Column(nullable = false)
	private LocalDate issueDate;
	
	@Column(nullable = false)
	private LocalDate returnDate;
	
	@Enumerated(EnumType.STRING)
	private TransactionStatus status;

}
