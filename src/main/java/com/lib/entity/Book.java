package com.lib.entity;

import com.lib.enums.BookStatus;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "books")
@Data
public class Book {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private String name;
	
	@Column(nullable = false)
	private String author;
	
	@Column(nullable = false)
	private String content;
	
	@Column(nullable = false)
	private String category;
	
	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private BookStatus status;
	
	@Column(nullable = false)
	private Integer totalQuantity;
	
	@Column(nullable = false)
	private Integer availableQuantity;
	
}
