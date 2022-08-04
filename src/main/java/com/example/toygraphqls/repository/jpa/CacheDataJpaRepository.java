package com.example.toygraphqls.repository.jpa;

import com.example.toygraphqls.model.entity.CacheData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CacheDataJpaRepository extends JpaRepository<CacheData, Integer> {

    Optional<CacheData> findByKey(String key);
}
