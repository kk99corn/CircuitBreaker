package com.example.toygraphqls.exception;

public class GQLInvalidParameterException extends GraphQLCustomException {
	public GQLInvalidParameterException(String message) {
		super(message, 400);
	}
}
