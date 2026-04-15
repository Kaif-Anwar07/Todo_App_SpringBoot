package com.mka.Todo_App.dto;

import java.time.LocalDateTime;

public class TodoDto {

	private Long id;
	private String title;
	private String description;
	private String status;
	private LocalDateTime createdAt;
	
	
	
	public Long getId() {
		return id;
	}
	public String getTitle() {
		return title;
	}
	public String getDescription() {
		return description;
	}
	public String getStatus() {
		return status;
	}
	public LocalDateTime getCreatedAt() {
		return createdAt;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}
	
	
}
