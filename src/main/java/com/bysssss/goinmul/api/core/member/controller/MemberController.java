package com.bysssss.goinmul.api.core.member.controller;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.bysssss.goinmul.api.core.member.service.MemberService;
import com.bysssss.goinmul.api.core.member.spec.MemberGetRequest;
import com.bysssss.goinmul.api.core.member.spec.MemberGetResponse;
import com.bysssss.goinmul.api.core.member.spec.MemberPostRequest;
import com.bysssss.goinmul.api.core.member.spec.MemberPostResponse;
import com.bysssss.goinmul.api.exception.GoinmulException;
import com.bysssss.goinmul.api.spec.Response;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Validated // @PathVariable 검증용 어노테이션
@RequestMapping("/api/v1/member")
@RestController
public class MemberController {
	@Autowired
    private MemberService memberService;
	
	@RequestMapping(value = "/{seq}", method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public ResponseEntity<Response> getMember(
    		@PathVariable(value = "seq") @NotNull @Min(100000L) Long seq) throws GoinmulException {
		MemberGetRequest request = new MemberGetRequest(seq);
		log.info("MemberController getMember() : request={}", request);
		
		MemberGetResponse response = memberService.getMember(request);
        return response.ok();
    }
	
	@RequestMapping(value = "", method = RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_UTF8_VALUE, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public ResponseEntity<Response> postMember(
    		@RequestBody @Valid MemberPostRequest request) throws GoinmulException {
		log.info("MemberController postMember() : request={}", request);
		
		MemberPostResponse response = memberService.postMember(request);
        return response.ok();
    }
}
