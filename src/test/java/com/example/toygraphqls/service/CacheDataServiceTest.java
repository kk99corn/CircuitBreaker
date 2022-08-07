package com.example.toygraphqls.service;

import com.example.toygraphqls.model.dto.CacheDataDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CacheDataServiceTest {

	@Autowired
	CacheDataService cacheDataService;

	@Test
	void setCacheDataTest() {
		String key = "test1";
		String value = "test data 1";

		CacheDataDto cacheDataDto = CacheDataDto.builder()
				.key(key)
				.value(value)
				.build();
		cacheDataService.setCacheData(cacheDataDto);

		CacheDataDto cacheData = cacheDataService.getCacheData(key);
		assertThat(cacheData.getValue()).isEqualTo(value);
	}

	@Test
	void getCacheDataTest() {
		String key = "test";
		CacheDataDto cacheData = cacheDataService.getCacheData(key);

		assertThat(cacheData.getValue()).isNotNull();
	}

	@Test
	void getCacheData2Test() {
		String key = "null";
		CacheDataDto cacheData = cacheDataService.getCacheData(key);

		assertThat(cacheData.getValue()).isNull();
	}
}