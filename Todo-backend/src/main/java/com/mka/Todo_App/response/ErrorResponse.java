package com.mka.Todo_App.response;

import java.time.LocalDateTime;
import java.util.Map;

import lombok.Data;

@Data
public class ErrorResponse {
	private boolean success;
	private int status;
	private String message;
	private Map<String,String> errors;
	private LocalDateTime timeStamp;
	
	
	public ErrorResponse(boolean success, int status, String message, Map<String, String> errors) {
		
		this.success = success;
		this.status = status;
		this.message = message;
		this.errors = errors;
		this.timeStamp = LocalDateTime.now();
	}
	
	
}
