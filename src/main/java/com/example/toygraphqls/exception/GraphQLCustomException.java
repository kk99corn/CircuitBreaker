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
	private String exceptionMessage;

	public GraphQLCustomException(String message) {
		super(message);
	}

	public GraphQLCustomException(String message, HttpStatus status, String exceptionMessage) {
		this(message);
		this.status = status;
		this.exceptionMessage = exceptionMessage;
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
		customAttributes.put("exceptionMessage", this.exceptionMessage);
		return customAttributes;
	}
}
