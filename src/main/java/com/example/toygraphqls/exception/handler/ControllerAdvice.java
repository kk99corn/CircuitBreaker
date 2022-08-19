package com.example.toygraphqls.exception.handler;

import com.example.toygraphqls.exception.GQLInvalidParameterException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class ControllerAdvice {

	@ExceptionHandler(GQLInvalidParameterException.class)
	public ResponseEntity<HttpStatus> invalidParameterExceptionHandler(GQLInvalidParameterException e) {
		log.info("[ControllerAdvice] Processing NotFoundException...");
		return new ResponseEntity<HttpStatus>(HttpStatus.BAD_GATEWAY);
	}
}