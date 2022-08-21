package com.example.toygraphqls.exception.handler;

import com.example.toygraphqls.exception.GQLBadRequestException;
import com.example.toygraphqls.exception.GQLNotFoundException;
import graphql.GraphQLError;
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
		if (exception instanceof GQLBadRequestException) {
			GQLBadRequestException badRequestException = (GQLBadRequestException) exception;
			return Mono.just(Collections.singletonList(badRequestException));
		}

		if (exception instanceof GQLNotFoundException) {
			GQLNotFoundException notFoundException = (GQLNotFoundException) exception;
			return Mono.just(Collections.singletonList(notFoundException));
		}

		return Mono.empty();
	}
}