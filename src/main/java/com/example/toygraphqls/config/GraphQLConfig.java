package com.example.toygraphqls.config;

import com.example.toygraphqls.exception.handler.GraphQLExceptionHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.graphql.GraphQlSourceBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@RequiredArgsConstructor
@Configuration(proxyBeanMethods = false)
class GraphQLConfig {

	private final GraphQLExceptionHandler graphQLExceptionHandler;

	@Bean
	public GraphQlSourceBuilderCustomizer sourceBuilderCustomizer() {

		return builder ->
				builder.exceptionResolvers(List.of(graphQLExceptionHandler));
	}
}