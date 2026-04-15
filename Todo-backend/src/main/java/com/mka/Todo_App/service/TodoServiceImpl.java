package com.mka.Todo_App.service;


import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.mka.Todo_App.dto.TodoDto;
import com.mka.Todo_App.dtoexception.ResourceNotFoundException;
import com.mka.Todo_App.entity.Todo;
import com.mka.Todo_App.entity.TodoStatus;
import com.mka.Todo_App.mapper.TodoMapper;
import com.mka.Todo_App.repository.TodoRepository;

@Service
public class TodoServiceImpl implements TodoService{
	
	@Autowired
	private TodoRepository todoRepository;
	
	@Override
	public TodoDto createTodo(Todo todo) {
		todo.setStatus(TodoStatus.PENDING);
		
		Todo savedTodo= todoRepository.save(todo);
		
		return TodoMapper.toDto(savedTodo);
	}

	@Override
	public List<Todo> getAllTodos() {
		return todoRepository.findAll();
	}
	
	@Override
	public TodoDto getTodoById(Long id) {
		Todo todo= todoRepository.findById(id)
				.orElseThrow(() ->
				new ResourceNotFoundException("Todo not found with id: "+id));
		
		return TodoMapper.toDto(todo);
	}

	@Override
	public List<Todo> createMultipleTodo(List<Todo> todos) {
		return todoRepository.saveAllAndFlush(todos);
	}

	@Override
	public Todo updateTodo(Long id, Todo newTodo) {
		
		
		Todo oldTodo=todoRepository.findById(id)
				.orElseThrow(()-> new ResourceNotFoundException("Todo not found with id: "+id));
		
		oldTodo.setTitle(newTodo.getTitle());
		oldTodo.setDescription(newTodo.getDescription());;
		
		if (oldTodo.getStatus() == null) {
	        oldTodo.setStatus(TodoStatus.PENDING);
	    }
		
		return todoRepository.save(oldTodo);
	}

	@Override
	public boolean deleteTodo(Long id) {
		
		Todo todo=todoRepository.findById(id)
				.orElseThrow(()-> 
				new ResourceNotFoundException("Todo not found with this id: "+id));
		todoRepository.delete(todo);
		return true;
	}

	@Override
	public Todo markCompleted(Long id) {
		Todo todo = todoRepository.findById(id)
				.orElseThrow(()-> new ResourceNotFoundException("Todo not found with id: "+id));
		
		todo.setStatus(TodoStatus.COMPLETED);
		
		return todoRepository.save(todo);
	}

	@Override
	public Page<TodoDto> getAllTodosInPage(int page, int size) {

		Pageable pageable = PageRequest.of(page, size);
		
		Page<Todo> todoPage= todoRepository.findAll(pageable);
				
		return todoPage.map(todo -> TodoMapper.toDto(todo));
		
		
	}

	@Override
	public Page<Todo> getTodosSortedAsc(int page, int size, String field) {
		
		Pageable pageable= PageRequest.of(page, size, Sort.by(Sort.Order.asc(field)));
		return todoRepository.findAll(pageable);
	}

	@Override
	public Page<Todo> getTodosSortedDesc(int page, int size, String field) {
		Pageable pageable=PageRequest.of(page, size, Sort.by(Sort.Order.desc(field)));
		
		return todoRepository.findAll(pageable);
	}
	
	
	
	
	
}
