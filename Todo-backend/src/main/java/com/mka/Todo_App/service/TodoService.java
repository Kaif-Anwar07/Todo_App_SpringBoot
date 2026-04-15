package com.mka.Todo_App.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.mka.Todo_App.dto.TodoDto;
import com.mka.Todo_App.entity.Todo;

public interface TodoService {
	
	TodoDto createTodo(Todo todo);
	
	List<Todo> getAllTodos();
//	
	TodoDto getTodoById(Long id);
	
	List<Todo> createMultipleTodo(List<Todo> todos);
//	
	Todo updateTodo(Long id, Todo todo);
//	
	boolean deleteTodo(Long id);
//	
	Todo markCompleted(Long id);
	
	Page<TodoDto> getAllTodosInPage(int page, int size);
	
	Page<Todo> getTodosSortedAsc(int page, int size, String field);
	
	Page<Todo> getTodosSortedDesc(int page, int size, String field);
}
