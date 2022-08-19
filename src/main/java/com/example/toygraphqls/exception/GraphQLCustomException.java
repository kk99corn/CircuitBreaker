package com.example.toygraphqls.exception;

import graphql.ErrorClassification;
import graphql.GraphQLError;
import graphql.language.SourceLocation;
import lombok.Getter;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Getter
public class GraphQLCustomException extends RuntimeException implements GraphQLError {
	private int errorCode;

	public GraphQLCustomException(String message) {
		super(message);
	}

	public GraphQLCustomException(String message, int errorCode) {
		this(message);
		this.errorCode = errorCode;
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
		customAttributes.put("errorCode", this.errorCode);
		customAttributes.put("errorMessage", this.getMessage());
		return customAttributes;
	}
}
