package com.example.toygraphqls.exception.handler;

import com.example.toygraphqls.exception.GQLBadRequestException;
import com.example.toygraphqls.exception.GQLNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class ControllerAdvice {

	@ExceptionHandler(GQLBadRequestException.class)
	public ResponseEntity<HttpStatus> badRequestExceptionHandler(GQLBadRequestException e) {
		return new ResponseEntity<>(e.getStatus());
	}

	@ExceptionHandler(GQLNotFoundException.class)
	public ResponseEntity<HttpStatus> notFoundExceptionHandler(GQLNotFoundException e) {
		return new ResponseEntity<>(e.getStatus());
	}
}