package com.example.toygraphqls.controller;

import com.example.toygraphqls.model.Member;
import com.example.toygraphqls.repository.redis.MemberRepository;
import com.example.toygraphqls.service.CacheDataService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Slf4j
@RestController
@Api(tags = {"rest api test"})
@RequestMapping("/rest")
public class restController {

    private final MemberRepository memberRepository;
    private final CacheDataService cacheDataService;

    public restController(MemberRepository memberRepository, CacheDataService cacheDataService) {
        this.memberRepository = memberRepository;
        this.cacheDataService = cacheDataService;
    }

    @GetMapping(value = "/redisInit")
    public void redisInit() {
        for (int i = 1; i <= 10; i++) {
            Member member = new Member(i, "test" + i, i * 10);
            memberRepository.save(member);
        }
    }

    @GetMapping(value = "/redis")
    public Map<Integer, Optional<Member>> redis() {
        Map<Integer, Optional<Member>> map = new HashMap<>();
        for (int i = 1; i <= 10; i++) {
            map.put(i, memberRepository.findById(i));
        }
        return map;
    }

    @ApiOperation(
            value = "rest test",
            notes = "rest test"
    )
    @GetMapping(value = "/info")
    public Map<String, String> projectInfo() {
        Map<String, String> map = new HashMap<>();
        map.put("test", "test");
        map.put("message", "welcome");
        map.put("code", "code");
        return map;
    }

    @GetMapping(value = "/test")
    public Map<String, String> get() {
        log.info("aaaaaaa");
        Map<String, String> map = new HashMap<>();
        map.put("prodSeq", "11111");
        map.put("prodName", "테스트상품111");
        return map;
    }

    @PostMapping(value = "/test")
    public Map<String, String> post() {
        log.info("aaaaaaa");
        Map<String, String> map = new HashMap<>();
        map.put("prodSeq", "11111");
        map.put("prodName", "테스트상품111");
        return map;
    }

    @PostMapping(value = "/test2")
    public Map<String, String> post2(@RequestParam(value = "param1") String param1, @RequestParam(value = "param2") String param2) {
        log.info("aaaaaaa");
        Map<String, String> map = new HashMap<>();
        map.put("prodSeq", "11111");
        map.put("prodName", "테스트상품111");
        return map;
    }
}
