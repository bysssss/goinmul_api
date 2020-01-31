package com.bysssss.goinmul.api.core.token.component;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.bysssss.goinmul.api.common.consts.Consts;
import com.bysssss.goinmul.api.common.enums.MemberType;
import com.bysssss.goinmul.api.common.util.DateUtil;
import com.bysssss.goinmul.api.common.util.JsonUtil;
import com.bysssss.goinmul.api.exception.GoinmulException;
import com.bysssss.goinmul.api.model.mysql2.member.data.MemberData;
import com.bysssss.goinmul.api.model.redis.token.data.TokenData;
import com.bysssss.goinmul.api.spec.ResultCode;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class TokenComponent {
	public void bakeCookie(String jwt, HttpServletResponse responseHead) throws GoinmulException {
		Cookie cookie = new Cookie(Consts.cookie, jwt);
		cookie.setMaxAge(60*60*24);
		cookie.setPath("/");
		cookie.setDomain("127.0.0.1");
		cookie.setHttpOnly(true);
		responseHead.addCookie(cookie);
		
	}
	
	public void crackCookie(HttpServletResponse responseHead) {
		Cookie cookie = new Cookie(Consts.cookie, null);
		cookie.setMaxAge(60);
		cookie.setPath("/");
		cookie.setDomain("127.0.0.1");
		cookie.setHttpOnly(true);
		responseHead.addCookie(cookie);
	}
	
	public String issueToken(MemberData member) throws GoinmulException {
		TokenData token = TokenData.builder()
				.memberSeq(member.getSeq())
				.memberCd(0)
				.memberType(MemberType.TYPE0.toString())
				.createTs(System.currentTimeMillis())
				.build();
		
		Map<String,Object> headers = new HashMap<>();
		headers.put("typ", "JWT");
		headers.put("alg", "HS256");
		
		Map<String,Object> payloads = new HashMap<>();
		payloads.put("token", token);
		
		String jwt = Jwts.builder()
				.setHeader(headers)
				//.setIssuer("goinmul")
				//.setSubject("subject")
				//.setAudience("api")
				.setIssuedAt(DateUtil.now())
				//.setNotBefore(DateUtil.now())
				.setExpiration(DateUtil.now(DateUtil.Day1))
				.setId(UUID.randomUUID().toString())
				.addClaims(payloads)
				.signWith(SignatureAlgorithm.HS256, this.secretKey())
				.compact();
		
		// TODO : redis set
		
		log.info("TokenComponent issueToken() : jwt={}", jwt);
		if( StringUtils.isEmpty(jwt)) {
			throw new GoinmulException(ResultCode.UNKNOWN, "jwt null");
		}
		
		return jwt;
	}
	
	public TokenData bringToken(String jwt) throws GoinmulException {
		if( StringUtils.isEmpty(jwt)) {
			throw new GoinmulException(ResultCode.UNKNOWN, "jwt null");
		}
		
		Claims claims = null;
		try {
			claims = Jwts.parser()
					.setSigningKey(this.secretKey())
					.parseClaimsJws(jwt)
					.getBody();
		} catch(Exception e) {
			throw new GoinmulException(ResultCode.UNKNOWN, e.getMessage());
		}
		
		Date expireAt = claims.getExpiration();
		if( DateUtil.isExpire(expireAt)) {
			throw new GoinmulException(ResultCode.UNKNOWN, "expiration err");
		}
		
		// TODO : redis get
		
		Object obj = claims.get("token");
		if( obj==null) {
			throw new GoinmulException(ResultCode.UNKNOWN, "obj null");
		}
		
		String json = obj.toString();
		TokenData token = JsonUtil.toObject(json, TokenData.class);
		if( token==null) {
			throw new GoinmulException(ResultCode.UNKNOWN, "token null");
		}
		
		return token;
	}
	
	private byte[] secretKey(){
        byte[] key = null;
        try {
            key = "secretKey".getBytes("UTF-8");
        } catch (UnsupportedEncodingException e) {
            return null;
        }
        
        return key;
    }
}
