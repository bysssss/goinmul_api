package com.bysssss.goinmul.api.core.token.service;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bysssss.goinmul.api.core.token.component.TokenComponent;
import com.bysssss.goinmul.api.core.token.spec.TokenGetResponse;
import com.bysssss.goinmul.api.core.token.spec.TokenPostRequest;
import com.bysssss.goinmul.api.core.token.spec.TokenPostResponse;
import com.bysssss.goinmul.api.exception.GoinmulException;
import com.bysssss.goinmul.api.model.mysql2.member.data.MemberData;
import com.bysssss.goinmul.api.model.mysql2.member.repository.MemberRepository;
import com.bysssss.goinmul.api.model.redis.token.data.TokenData;
import com.bysssss.goinmul.api.spec.ResultCode;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class TokenService {
	@Autowired
	private TokenComponent tokenComponent;
	
	@Autowired
	private MemberRepository memberRepository;
	
	public TokenPostResponse postToken(TokenPostRequest request, HttpServletResponse responseHead) throws GoinmulException {
		String memberId = request.getMemberId();
		String memberPw = request.getMemberPw();
		log.info("TokenService postToken() : memberId={}", memberId);
		log.info("TokenService postToken() : memberPw={}", memberPw);
        
        // TODO : 분리보관 withdraw 및 dormant 회원 로직.
		// throw new GoinmulException(ResultCode.GONE, "withdraw");
		// throw new GoinmulException(ResultCode.GONE, "dormant");
        
        MemberData member = null; 
        member = memberRepository.selectMemberById(memberId);
        if( member == null) {
        	throw new GoinmulException(ResultCode.NOT_FOUND, "memberId err");
        }
        
        // TODO : memberSeq 기반으로, 로그인 Count 조회
        //throw new GoinmulException(ResultCode.TOO_MANY_REQUESTS, "try5 err");
        
        member = memberRepository.selectMemberByIdPw(memberId, memberPw);
        if( member == null) {
        	// TODO : memberSeq 기반으로, 로그인 Count 삽입
        	throw new GoinmulException(ResultCode.UNAUTHORIZED, "memberPw err");
        }
        
        // TODO : passwordAt 로직
        
        String jwt = tokenComponent.issueToken(member);
        tokenComponent.bakeCookie(jwt, responseHead);
        
        TokenPostResponse response = new TokenPostResponse(jwt);
        return response;
    }
	
	public TokenGetResponse getToken(String jwt) throws GoinmulException {
		log.info("TokenService getToken() : jwt={}", jwt);
		
		TokenData token = tokenComponent.bringToken(jwt);
		
		TokenGetResponse response = new TokenGetResponse(token);
		return response;
	}
}
