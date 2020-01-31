package com.bysssss.goinmul.api.core.token.controller;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.bysssss.goinmul.api.common.consts.Consts;
import com.bysssss.goinmul.api.core.token.service.TokenService;
import com.bysssss.goinmul.api.core.token.spec.TokenGetResponse;
import com.bysssss.goinmul.api.core.token.spec.TokenPostRequest;
import com.bysssss.goinmul.api.core.token.spec.TokenPostResponse;
import com.bysssss.goinmul.api.exception.GoinmulException;
import com.bysssss.goinmul.api.spec.Response;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Validated // @PathVariable 검증용 어노테이션
@RequestMapping("/api/v1/token")
@RestController
public class TokenController {
	@Autowired
    private TokenService tokenService;
	
	@RequestMapping(value = "", method = RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_UTF8_VALUE, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public ResponseEntity<Response> postToken(
    		@RequestBody @Valid TokenPostRequest request, HttpServletResponse responseHead) throws GoinmulException {
		log.info("TokenController postToken() : request={}", request);
		
		TokenPostResponse response = tokenService.postToken(request, responseHead);
        return response.ok();
    }
	
	@RequestMapping(value = "", method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public ResponseEntity<Response> getToken(
    		@CookieValue(name=Consts.cookie, defaultValue="") String cookie, @RequestHeader(HttpHeaders.AUTHORIZATION) String authorization) throws GoinmulException {
		log.info("TokenController getToken() : cookie={}", cookie);
		log.info("TokenController getToken() : authorization={}", authorization);
		
		TokenGetResponse response = tokenService.getToken(cookie);
        return response.ok();
    }
}
