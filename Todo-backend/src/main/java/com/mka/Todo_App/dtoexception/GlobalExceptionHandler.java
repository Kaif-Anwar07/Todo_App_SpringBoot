package com.mka.Todo_App.dtoexception;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<?> handleNotFound(ResourceNotFoundException ex){
		return ResponseEntity.status(HttpStatus.NOT_FOUND)
				.body(ex.getMessage());
	}
	
	@ExceptionHandler(value=MethodArgumentNotValidException.class)
	public ResponseEntity<?> handleValidationErrors(
			MethodArgumentNotValidException ex){
		
		Map<String,String> map = new HashMap<>();
		
		List<FieldError> fieldErrors = ex.getFieldErrors();
		
		for(FieldError fieldError: fieldErrors) {
			String fieldName=fieldError.getField();
			String fieldMsg = fieldError.getDefaultMessage();
			map.put(fieldName, fieldMsg);
		}
		ex.printStackTrace();
		return ResponseEntity.badRequest().body(map);
		}
}	

