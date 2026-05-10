package com.mka.Todo_App.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class TodoResponseDto {

	private Long id;
	private String title;
	private String description;
	private String status;
	private LocalDateTime createdAt;
	
	
}
