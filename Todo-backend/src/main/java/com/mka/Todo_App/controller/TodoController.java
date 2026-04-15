package com.mka.Todo_App.controller;



import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.service.annotation.PatchExchange;

import com.mka.Todo_App.dto.TodoDto;
import com.mka.Todo_App.entity.Todo;
import com.mka.Todo_App.entity.TodoStatus;
import com.mka.Todo_App.service.TodoService;

import jakarta.validation.Valid;

@CrossOrigin(origins="*")
@RestController
@RequestMapping("/todos")
public class TodoController {
	
	
	private final TodoService todoService;
	
	public TodoController(TodoService todoService) {
		this.todoService=todoService;
	}
	
	
	@PostMapping("/saveTodo")
	public ResponseEntity<TodoDto> createTodo(@Valid @RequestBody Todo todo) {
		
		TodoDto savedTodo = todoService.createTodo(todo);
		
		return ResponseEntity
				.status(HttpStatus.CREATED)
				.body(savedTodo);
	}
	
	@GetMapping("/getAllTodo")
	public ResponseEntity<List<Todo>> getAllTodo(){
		List<Todo> todos= todoService.getAllTodos();
		
		return ResponseEntity.ok(todos);
	}
	
	@GetMapping("/getTodo/{id}")
	public ResponseEntity<TodoDto> getTodoByIdController(@PathVariable Long id) {
		
		TodoDto todo=todoService.getTodoById(id);
		
		return ResponseEntity.ok(todo);
	}
	
	@PostMapping("/saveMultipleTodo")
	public ResponseEntity<List<Todo>> createMultipleTodoController(@Valid @RequestBody List<Todo> todos){

	    for (Todo todo : todos) {
	        if (todo.getStatus() == null) {   // client ne status nahi diya
	            todo.setStatus(TodoStatus.PENDING);  // toh hum set kar rahe
	        }
	    }
	    List<Todo> savedTodos = todoService.createMultipleTodo(todos);
		
		return ResponseEntity
				.status(HttpStatus.CREATED)
				.body(savedTodos);
	}
	
	@PutMapping("/updateTodo/{id}")
	public ResponseEntity<Todo> updateTodoById(@PathVariable Long id ,@Valid @RequestBody Todo todo) {
		
		Todo updatedTodo=todoService.updateTodo(id, todo);
		
		return ResponseEntity.ok(updatedTodo);
	}
	
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<String> deleteTodoById(@PathVariable Long id) {
		
		todoService.deleteTodo(id);
		
		return ResponseEntity.ok("Todo deleted successfully");
	}
	
	@PatchMapping("/complete/{id}")
	public ResponseEntity<Todo> markCompleted(@PathVariable Long id) {
		
		Todo todo=todoService.markCompleted(id);
		
		return ResponseEntity.ok(todo);
	}
	
	@GetMapping("/getAllTodoInPage")
	public ResponseEntity<Page<TodoDto>> getAllTodo(
			@RequestParam(defaultValue ="0")int page,
			@RequestParam(defaultValue ="3")int size){
		
		Page<TodoDto> todos = todoService.getAllTodosInPage(page, size);
		return ResponseEntity.ok(todos);
	}
	
	@GetMapping("/getAllTodo/asc")
	public ResponseEntity<Page<Todo>> getTodoAsc(
			@RequestParam(defaultValue="0") int page,
			@RequestParam(defaultValue="3") int size,
			@RequestParam String field){
		
		return ResponseEntity.ok(todoService.getTodosSortedAsc(page, size, field));
	}
	
	@GetMapping("/getAllTodo/Desc")
	public ResponseEntity<Page<Todo>> getTodoDesc(
			@RequestParam(defaultValue="0")int page,
			@RequestParam(defaultValue="2")int size,
			@RequestParam String field){
		
		return ResponseEntity.ok(todoService.getTodosSortedDesc(page, size, field));
	}
	

}
