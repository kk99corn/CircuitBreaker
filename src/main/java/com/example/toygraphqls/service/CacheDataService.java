package com.example.toygraphqls.service;

import com.example.toygraphqls.exception.GQLBadRequestException;
import com.example.toygraphqls.exception.GQLNotFoundException;
import com.example.toygraphqls.model.dto.CacheDataDto;
import com.example.toygraphqls.model.entity.CacheData;
import com.example.toygraphqls.repository.jpa.CacheDataJpaRepository;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

@Service
public class CacheDataService {
	private final CacheDataJpaRepository cacheDataJpaRepository;
	private final StringRedisTemplate stringRedisTemplate;

	public CacheDataService(CacheDataJpaRepository cacheDataJpaRepository, StringRedisTemplate stringRedisTemplate) {
		this.cacheDataJpaRepository = cacheDataJpaRepository;
		this.stringRedisTemplate = stringRedisTemplate;
	}

	public void setCacheData(CacheDataDto cacheDataDto) {
		CacheData cacheData = new CacheData();

		// cache db내 존재여부 확인
		Optional<CacheData> cacheDataByKey = cacheDataJpaRepository.findByKey(cacheDataDto.getKey());
		if (cacheDataByKey.isPresent()) {
			cacheData = cacheDataByKey.get();
		} else {
			cacheData.setKey(cacheDataDto.getKey());
		}

		Date date = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		cacheData.setDate(formatter.format(date));
		cacheData.setValue(cacheDataDto.getValue());

		// db 저장
		cacheDataJpaRepository.save(cacheData);

		// redis 저장
		stringRedisTemplate.opsForValue().set(cacheDataDto.getKey(), cacheDataDto.getValue());
	}

	@CircuitBreaker(name = "redisCircuitBreaker", fallbackMethod = "getCacheDataFallback")
	public CacheDataDto getCacheData(String key) {
		String value = stringRedisTemplate.opsForValue().get(key);
		if (value == null) {
			CacheData cacheData;
			Optional<CacheData> cacheDataByKey = cacheDataJpaRepository.findByKey(key);
			if (cacheDataByKey.isPresent()) {
				cacheData = cacheDataByKey.get();
				value = cacheData.getValue();

				// db에만 있고 redis에 없는 캐시 -> redis 저장
				stringRedisTemplate.opsForValue().set(cacheData.getKey(), cacheData.getValue());
			} else {
				throw new GQLNotFoundException("data not found: key=" + key);
			}
		}

		return CacheDataDto.builder()
				.key(key)
				.value(value)
				.build();
	}

	public CacheDataDto getCacheDataFallback(String key, Throwable throwable) {
		if (throwable instanceof GQLBadRequestException) {
			throw (GQLBadRequestException) throwable;
		}
		if (throwable instanceof GQLNotFoundException) {
			throw (GQLNotFoundException) throwable;
		}

		CacheData cacheData;
		Optional<CacheData> cacheDataByKey = cacheDataJpaRepository.findByKey(key);
		if (cacheDataByKey.isPresent()) {
			cacheData = cacheDataByKey.get();
		} else {
			throw new GQLNotFoundException("data not found: key=" + key);
		}

		return CacheDataDto.builder()
				.key(key)
				.value("[CB test]: " + cacheData.getValue())
				.build();
	}
}
