package com.mka.Todo_App.exception;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.mka.Todo_App.response.ErrorResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ErrorResponse> handleNotFound(ResourceNotFoundException ex){
		ErrorResponse response=new ErrorResponse(false, 404, ex.getMessage(), null);
		
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
	}
	
	@ExceptionHandler(value=MethodArgumentNotValidException.class)
	public ResponseEntity<ErrorResponse> handleValidationErrors(
			MethodArgumentNotValidException ex){
		
		Map<String,String> map = new HashMap<>();
		
		List<FieldError> fieldErrors = ex.getFieldErrors();
		
		for(FieldError fieldError: fieldErrors) {
			String fieldName=fieldError.getField();
			String fieldMsg = fieldError.getDefaultMessage();
			map.put(fieldName, fieldMsg);
		}
		ErrorResponse response=new ErrorResponse(false, 400, "Validation failed", map);
		return ResponseEntity.badRequest().body(response);
		}
}	

