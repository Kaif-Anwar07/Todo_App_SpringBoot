package com.mka.Todo_App.mapper;

import com.mka.Todo_App.dto.TodoDto;
import com.mka.Todo_App.entity.Todo;

public class TodoMapper {
	//Entity -> DTO
	public static TodoDto toDto(Todo todo) {
		
		TodoDto dto = new TodoDto();
		
		dto.setId(todo.getId());
		dto.setTitle(todo.getTitle());
		dto.setDescription(todo.getDescription());
		dto.setStatus(todo.getStatus().name());
		dto.setCreatedAt(todo.getCreatedAt());
		
		return dto;
	}
}
