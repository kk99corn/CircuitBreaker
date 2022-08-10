package com.example.toygraphqls.controller.graphql;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.HashMap;
import java.util.Map;

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
class CacheDataControllerTest {

	@Autowired
	MockMvc mockMvc;

	@Test
	void findCacheDataTest() throws Exception {
		// given
		// when
		// then

		Map<String, String> params = new HashMap<>();

		mockMvc.perform(MockMvcRequestBuilders.post("/graphql")
				.contentType(MediaType.APPLICATION_JSON)
				.content("{\"query\":\"query{\\n test:findCacheData(key: \\\"test\\\") {\\n    key,\\n    value\\n  }\\n}\"}")
		)
				.andDo(MockMvcResultHandlers.print())
				.andExpect(MockMvcResultMatchers.status().isOk());
	}
}