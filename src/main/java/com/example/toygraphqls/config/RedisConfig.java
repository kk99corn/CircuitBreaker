package com.example.toygraphqls.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.redis.connection.RedisClusterConfiguration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.time.Duration;
import java.util.List;

/**
 * 레디스 사용 설정 방법
 * Repository :  Redis에 직접 Key, Value값을 설정하여 CRUD를 합니다. - 트랜잭션이용은 불가.
 * Template : Redis에 직접 Key, Value값을 설정하여 CRUD를 합니다.
 * RedisManager :  메서드나 타입에 캐싱처리를 합니다.
 */

@Profile("default")
@Configuration
public class RedisConfig {

    // redis node
    @Value("${spring.redis.cluster.nodes}")
    private List<String> clusterNodes;

    // redis password
    @Value("${spring.redis.password}")
    private String password;

    // redis redirect
    @Value("${spring.redis.cluster.max-redirects}")
    private Integer maxRedirects;

    @Bean
    public RedisConnectionFactory redisConnectionFactory() {
        RedisClusterConfiguration redisClusterConfiguration = new RedisClusterConfiguration(clusterNodes);
        redisClusterConfiguration.setPassword(password);
        redisClusterConfiguration.setMaxRedirects(maxRedirects);

        // redisCommandTimeout 설정
        LettuceClientConfiguration lettuceClientConfiguration = LettuceClientConfiguration.builder()
                .commandTimeout(Duration.ofMillis(200))
                .build();

        return new LettuceConnectionFactory(redisClusterConfiguration, lettuceClientConfiguration);
    }

    // 문자열특화 템플릿  (repository 이용할경우 필요X)
    @Bean
    public StringRedisTemplate stringRedisTemplate(RedisConnectionFactory redisConnectionFactory) {
        return new StringRedisTemplate(redisConnectionFactory);
    }

}
