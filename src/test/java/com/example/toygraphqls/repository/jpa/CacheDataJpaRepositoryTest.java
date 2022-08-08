package com.example.toygraphqls.repository.jpa;

import com.example.toygraphqls.model.entity.CacheData;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;


@ActiveProfiles("test")
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
@SpringBootTest
class CacheDataJpaRepositoryTest {

    @Autowired
    CacheDataJpaRepository cacheDataJpaRepository;

    @Test
    void findByKeyTest() {
        // given
        String key = "test";

        // when
        Optional<CacheData> cacheData = cacheDataJpaRepository.findByKey(key);

        // then
        assertThat(cacheData).isNotEmpty();
        assertThat(cacheData.get().getValue()).isEqualTo("test");
    }

    @Test
    void findByKeyResultNullTest() {
        // given
        String key = "empty_key";

        // when
        Optional<CacheData> cacheData = cacheDataJpaRepository.findByKey(key);

        // then
        assertThat(cacheData).isEmpty();
    }

    @Test
    void saveCacheDataTest() {
        // given
        String key = "save_test_key";
        String value = "save_test_value";
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        // when
        CacheData inputCacheData = CacheData.builder()
                .key(key)
                .value(value)
                .date(formatter.format(date))
                .build();
        cacheDataJpaRepository.save(inputCacheData);

        // then
        Optional<CacheData> cacheDataByKey = cacheDataJpaRepository.findByKey(key);
        cacheDataByKey.ifPresent(cacheData -> assertThat(cacheData.getValue()).isEqualTo(value));
    }

    @Test
    void deleteCacheDataTest() {
        // given
        String key = "delete_test_key";
        String value = "delete_test_key";
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        CacheData inputCacheData = CacheData.builder()
                .key(key)
                .value(value)
                .date(formatter.format(date))
                .build();
        cacheDataJpaRepository.save(inputCacheData);

        // when
        cacheDataJpaRepository.deleteByKey(key);

        // then
        Optional<CacheData> cacheDataByKey = cacheDataJpaRepository.findByKey(key);
        assertThat(cacheDataByKey).isEmpty();
    }
}