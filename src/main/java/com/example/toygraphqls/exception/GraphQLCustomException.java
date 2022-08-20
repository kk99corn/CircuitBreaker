package com.example.toygraphqls.exception;

import graphql.ErrorClassification;
import graphql.GraphQLError;
import graphql.language.SourceLocation;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Getter
public class GraphQLCustomException extends RuntimeException implements GraphQLError {
	private HttpStatus status;
	private String message;

	public GraphQLCustomException(String exceptionMessage) {
		super(exceptionMessage);
	}

	public GraphQLCustomException(String exceptionMessage, HttpStatus status, String message) {
		this(exceptionMessage);
		this.status = status;
		this.message = message;
	}

	@Override
	public List<SourceLocation> getLocations() {
		return null;
	}

	@Override
	public ErrorClassification getErrorType() {
		return null;
	}

	@Override
	public Map<String, Object> getExtensions() {
		Map<String, Object> customAttributes = new LinkedHashMap<>();
		customAttributes.put("status", this.status.value());
		customAttributes.put("message", this.message);
		customAttributes.put("exceptionMessage", this.getMessage());
		return customAttributes;
	}
}
