package com.bysssss.goinmul.api.model.redis.temp.data;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.TimeToLive;

import lombok.Builder;
import lombok.Data;

@RedisHash("temp")
@Builder
@Data
public class TempData {
	@Id
	Integer tempId;
	
	Date tempAt;
	
	@TimeToLive 
	Long ttl;
}
