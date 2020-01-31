package com.bysssss.goinmul.api.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisClusterConfiguration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

//@EnableRedisRepositories
@Configuration
public class RedisConfig {
	@Autowired
    RedisProperties redisProperties;
	
    @Bean 
	RedisConnectionFactory redisConnectionFactory() {
		RedisClusterConfiguration redisClusterConfiguration = new RedisClusterConfiguration(redisProperties.getCluster().getNodes());
        redisClusterConfiguration.setMaxRedirects(redisProperties.getCluster().getMaxRedirects());
        redisClusterConfiguration.setPassword(redisProperties.getPassword());
        
        //JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        //return new JedisConnectionFactory(redisClusterConfiguration, jedisPoolConfig);
        return new LettuceConnectionFactory(redisClusterConfiguration);
    }

    @Bean
    public RedisTemplate<String, Object> redisTemplate() {
        final RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(redisConnectionFactory());
        template.setKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(new StringRedisSerializer());
        return template;
    }
}
