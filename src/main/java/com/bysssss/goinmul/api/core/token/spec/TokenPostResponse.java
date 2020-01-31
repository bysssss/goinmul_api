package com.bysssss.goinmul.api.core.token.spec;

import java.util.Date;

import com.bysssss.goinmul.api.spec.Response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Setter
@Getter
public class TokenPostResponse extends Response {
	private TokenPostResponse.Result result;
	
	@ToString
    @Setter
    @Getter
    @Builder
	public static class Result {
		String jwt;
		Date passwordAt;
	}
	
	public TokenPostResponse(String jwt) {
		this.result = TokenPostResponse.Result.builder()
				.jwt(jwt)
				.build();
	}
}
