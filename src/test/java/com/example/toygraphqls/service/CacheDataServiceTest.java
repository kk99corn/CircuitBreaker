package com.example.toygraphqls.service;

import com.example.toygraphqls.model.dto.CacheDataDto;
import com.example.toygraphqls.model.entity.CacheData;
import com.example.toygraphqls.repository.jpa.CacheDataJpaRepository;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@ActiveProfiles("test")
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
@SpringBootTest
class CacheDataServiceTest {

	@Autowired
	CacheDataService cacheDataService;

	@Autowired
	CacheDataJpaRepository cacheDataJpaRepository;

	@Autowired
	StringRedisTemplate stringRedisTemplate;

	@Test
	@Order(1)
	void testDataInit() {
		// 테스트용 데이터 세팅
		for (int i = 1; i <= 10; i++) {
			CacheDataDto cacheDataDto = CacheDataDto.builder()
					.key("TEST_" + i)
					.value("TEST_VALUE_" + i)
					.build();
			cacheDataService.setCacheData(cacheDataDto);
		}
	}

	@Test
	void setCacheDataTest() {
		// given
		String key = "save_service_test_key";
		String value = "save_service_test_value";
		CacheDataDto cacheDataDto = CacheDataDto.builder()
				.key(key)
				.value(value)
				.build();

		// when
		cacheDataService.setCacheData(cacheDataDto);

		// then
		Optional<CacheData> cacheDataByKey = cacheDataJpaRepository.findByKey(key);
		if (cacheDataByKey.isPresent()) {
			assertThat(cacheDataByKey.get().getKey()).isEqualTo(key);
			assertThat(cacheDataByKey.get().getValue()).isEqualTo(value);
		}

		String getValue = stringRedisTemplate.opsForValue().get(key);
		assertThat(getValue).isEqualTo(value);
	}

	@Test
	void getCacheDataTest() {
		// given
		String key = "TEST_1";

		// when
		CacheDataDto cacheDataDto = cacheDataService.getCacheData("TEST_1");

		// then
		assertThat(cacheDataDto.getKey()).isEqualTo(key);
		assertThat(cacheDataDto.getValue()).isEqualTo("TEST_VALUE_1");
	}

	@Test
	void getCacheDataResultNullTest() {
		// given
		String key = "X_TEST_1";

		// when
		CacheDataDto cacheDataDto = cacheDataService.getCacheData(key);

		// then
		assertThat(cacheDataDto.getKey()).isEqualTo(key);
		assertThat(cacheDataDto.getValue()).isNull();
	}

}