package com.example.toygraphqls.controller.graphql;

import com.example.toygraphqls.model.dto.CacheDataDto;
import com.example.toygraphqls.service.CacheDataService;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.HashMap;
import java.util.Map;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
class CacheDataControllerTest {

	@MockBean
	CacheDataService cacheDataService;

	@Autowired
	MockMvc mockMvc;

	@Order(1)
	@Test
	void saveCacheDataTest() throws Exception {
		// given
		String body = "{\"query\":\"mutation{cacheData(key: \\\"test__2\\\", value: \\\"test12345\\\"){key,value}}\"}";

		CacheDataDto cacheDataDto = CacheDataDto.builder()
				.key("test__2")
				.value("asdadasda")
				.build();

		// when
		// then
		MvcResult result = mockMvc.perform(post("/graphql")
				.contentType(MediaType.APPLICATION_JSON)
				.content(body))
				.andDo(MockMvcResultHandlers.print())
				.andReturn();
		String responseBody = result.getResponse().getContentAsString();

		System.out.println("resut: " + responseBody);
	}

	@Test
	void findCacheDataTest() throws Exception {
		// given
		String body = "{\"query\":\"query{cacheData(key: \\\"test_2\\\"){key,value}}\"}";

		// when
		// then

		mockMvc.perform(post("/graphql")
				.contentType(MediaType.APPLICATION_JSON)
				.content(body))
				.andDo(MockMvcResultHandlers.print());
	}
}