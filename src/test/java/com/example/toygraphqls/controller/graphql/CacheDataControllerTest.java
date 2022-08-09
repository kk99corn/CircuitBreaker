package com.example.toygraphqls.controller.graphql;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.graphql.tester.AutoConfigureGraphQlTester;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.graphql.test.tester.GraphQlTester;

@Profile("test")
@SpringBootTest
@AutoConfigureGraphQlTester
class CacheDataControllerTest {

	@Autowired
	private GraphQlTester graphQlTester;

	@Test
	void findCacheDataTest() {
		// given
		// when
		GraphQlTester.Response execute = this.graphQlTester.documentName("CacheData")
				.variable("key", "FILE_CACHE_1")
				.execute();
		System.out.println(execute.toString());
		// then
	}
}