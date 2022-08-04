package com.example.toygraphqls.repository.jpa;

import com.example.toygraphqls.model.entity.CacheData;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CacheDataJpaRepositoryTest {

    @Autowired
    CacheDataJpaRepository cacheDataJpaRepository;

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @Test
    void findByKey() {
        String key = "FILE_CACHE_1";
        Optional<CacheData> cacheData = cacheDataJpaRepository.findByKey(key);

        assertThat(cacheData).isNotEmpty();

        CacheData cache = cacheData.get();
        System.out.println(cache.toString());
    }

    @Test
    void test() {
        stringRedisTemplate.opsForValue().set("aaaa", "bbb");
    }
}