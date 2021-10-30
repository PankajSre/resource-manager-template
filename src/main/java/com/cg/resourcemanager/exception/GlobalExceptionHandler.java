package com.cg.resourcemanager.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.cg.resourcemanager.dto.ResourceExceptionResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(AllocationException.class)
	public ResponseEntity<ResourceExceptionResponse> AllocationHandler(AllocationException ex) {
		ResourceExceptionResponse resp = new ResourceExceptionResponse(ex.getMessage(), System.currentTimeMillis(),
				HttpStatus.BAD_REQUEST.value());

		ResponseEntity<ResourceExceptionResponse> response = new ResponseEntity<ResourceExceptionResponse>(resp,
				HttpStatus.BAD_REQUEST);
		return response;
	}

}
