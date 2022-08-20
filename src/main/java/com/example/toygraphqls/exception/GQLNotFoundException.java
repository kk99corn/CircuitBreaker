package com.example.toygraphqls.exception;

import org.springframework.http.HttpStatus;

public class GQLNotFoundException extends GraphQLCustomException {
	public GQLNotFoundException(String message) {
		super(message, HttpStatus.NOT_FOUND, "Resource not found");
	}
}
