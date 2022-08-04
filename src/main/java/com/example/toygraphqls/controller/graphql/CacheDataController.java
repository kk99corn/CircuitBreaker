package com.example.toygraphqls.controller.graphql;

import com.example.toygraphqls.model.dto.CacheDataDto;
import com.example.toygraphqls.service.CacheDataService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

@Slf4j
@Controller
public class CacheDataController {

    private final CacheDataService cacheDataService;

    public CacheDataController(CacheDataService cacheDataService) {
        this.cacheDataService = cacheDataService;
    }

    @MutationMapping
    public CacheDataDto saveCacheData(@Argument String key, @Argument String value) {
        CacheDataDto cacheDataDto = CacheDataDto.builder()
                .key(key)
                .value(value)
                .build();

        cacheDataService.setCacheData(cacheDataDto);
        return cacheDataDto;
    }

    @QueryMapping
    public CacheDataDto findCacheData(@Argument String key) {
        return cacheDataService.getCacheData(key);
    }
}
