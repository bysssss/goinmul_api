package com.bysssss.goinmul.api.model.redis.temp.repository;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Repository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
public class TempRepository {
	@Autowired
    private RedisTemplate<String, Object> redisTemplate;

    public void set(String key, Object value, long timeout) {
        log.info("RedisRepository set() : key={}, value={}", key, value);
        ValueOperations<String, Object> valueOperations = redisTemplate.opsForValue();
        valueOperations.set(key, value, timeout, TimeUnit.MILLISECONDS);
        return;
    }

    public Object get(String key) {
        log.info("RedisRepository get() : key={}", key);
        ValueOperations<String, Object> valueOperations = redisTemplate.opsForValue();
        return valueOperations.get(key);
    }
}
