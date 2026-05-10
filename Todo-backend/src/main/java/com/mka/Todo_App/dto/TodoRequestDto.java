package com.mka.Todo_App.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class TodoRequestDto {
	
	@NotBlank(message = "Title is required")
	@Size(min = 3, max=100, message = "Title must be between 3 and 100 characters")
	private String title;
	
	@NotBlank(message = "Description is required")
	private String description;
	
	
}
