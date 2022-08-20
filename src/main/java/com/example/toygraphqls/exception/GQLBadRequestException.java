package com.example.toygraphqls.exception;

import org.springframework.http.HttpStatus;

public class GQLBadRequestException extends GraphQLCustomException {
	public GQLBadRequestException(String message) {
		super(message, HttpStatus.BAD_REQUEST, "Bad Request");
	}
}
