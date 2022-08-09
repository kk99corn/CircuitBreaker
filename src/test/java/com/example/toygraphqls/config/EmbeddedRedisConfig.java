package com.example.toygraphqls.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import redis.embedded.Redis;
import redis.embedded.cluster.RedisCluster;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.Arrays;
import java.util.Optional;

@Profile("test")
@Configuration
public class EmbeddedRedisConfig {

	private Redis redisCluster;

	@PostConstruct
	public void redisCluster() {
		redisCluster = new RedisCluster.Builder()
				.serverPorts(Arrays.asList(7379, 7380, 7381))
				.numOfReplicates(1)
				.numOfRetries(1)
				.build();
		try {
			redisCluster.start();
		} catch (Exception e) {
			// redis 중복실행 방지용 임시처리
			System.out.println("redisCluster = " + redisCluster);
		}
	}

	@PreDestroy
	public void stopRedis() {
		Optional.ofNullable(redisCluster).ifPresent(Redis::stop);
	}
}
