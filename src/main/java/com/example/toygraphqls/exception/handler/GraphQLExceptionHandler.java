package com.example.toygraphqls.exception.handler;

import com.example.toygraphqls.exception.GQLInvalidParameterException;
import graphql.GraphQLError;
import graphql.GraphqlErrorBuilder;
import graphql.language.SourceLocation;
import graphql.schema.DataFetchingEnvironment;
import lombok.extern.slf4j.Slf4j;
import org.springframework.graphql.execution.DataFetcherExceptionResolver;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.Collections;
import java.util.List;

@Slf4j
@Component
public class GraphQLExceptionHandler implements DataFetcherExceptionResolver {

	@Override
	public Mono<List<GraphQLError>> resolveException(Throwable exception, DataFetchingEnvironment environment) {

		log.info("[GraphQLExceptionHandler] NotFoundException type {}", exception);
		List<SourceLocation> sourceLocation = List.of(environment.getField().getSourceLocation());

		if (exception instanceof GQLInvalidParameterException) {
			GQLInvalidParameterException invalidParameterException = (GQLInvalidParameterException) exception;
			return Mono.just(Collections.singletonList(invalidParameterException));
		}


		return Mono.empty();
	}
}