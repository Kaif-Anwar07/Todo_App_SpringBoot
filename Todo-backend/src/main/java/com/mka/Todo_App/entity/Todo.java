package com.mka.Todo_App.entity;

import java.time.LocalDateTime;
import jakarta.validation.constraints.NotBlank;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
@Data
@Entity
@Table(name="todos")
public class Todo {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message="Title must not be empty")
	@Column(nullable = false)
	private String title;
	

	@NotBlank(message = "Description must not be empty")
	private String description;
	
	
	@Enumerated(EnumType.STRING)
	private TodoStatus status;
	
	private LocalDateTime createdAt;

	public Todo() {
		
	}
	
	public Todo(String title, String description, TodoStatus status) {
		this.title = title;
		this.description = description;
		this.status = status;
		this.createdAt = LocalDateTime.now();
	}
	
	
}
