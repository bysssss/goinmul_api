package com.bysssss.goinmul.api.core.temp.controller;

import javax.validation.constraints.NotBlank;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.bysssss.goinmul.api.common.util.DateUtil;
import com.bysssss.goinmul.api.exception.GoinmulException;
import com.bysssss.goinmul.api.model.redis.temp.repository.TempRepository;
import com.bysssss.goinmul.api.spec.Response;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Validated // @PathVariable 검증용 어노테이션
@RequestMapping("/api/v1/temp")
@RestController
public class TempController {
	@Autowired
	private TempRepository tempRepository;
	
	@RequestMapping(value = "/get/{key}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Response> getTemp(
    		@PathVariable(value = "key") @NotBlank String key) throws GoinmulException {
		log.info("TempController getTemp() : key={}", key);
		
		String value = (String) tempRepository.get("temp:"+key);
		if( StringUtils.isEmpty(value)) {
			value = "NULL";
		}
		
		Response response = new Response();
		response.setResultMessage(value);
        return response.ok();
    }
	
	@RequestMapping(value = "/post/{key}/{value}", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<Response> postTemp(
    		@PathVariable(value = "key") @NotBlank String key, @PathVariable(value = "value") @NotBlank String value) throws GoinmulException {
		log.info("TempController postTemp() : key={}", key);
		log.info("TempController postTemp() : value={}", value);
		
		tempRepository.set("temp:"+key, value, DateUtil.Min1);
		
		Response response = new Response();
		response.setResultMessage(value);
        return response.ok();
    }

}
