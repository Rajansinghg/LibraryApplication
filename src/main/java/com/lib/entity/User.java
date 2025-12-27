package com.lib.entity;

import java.time.LocalDateTime;

import com.lib.enums.UserType;

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
@Data
@Table(name = "users")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private String name;
	
	@Column(unique = true)
	private String email;
	
	@Column(nullable = false)
	private String password;
	
	
	private LocalDateTime membershipStartTime;
	
	private LocalDateTime membershipEndTime;
	
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private UserType userType;
		
}
