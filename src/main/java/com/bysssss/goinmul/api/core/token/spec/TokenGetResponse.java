package com.bysssss.goinmul.api.core.token.spec;

import com.bysssss.goinmul.api.model.redis.token.data.TokenData;
import com.bysssss.goinmul.api.spec.Response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Setter
@Getter
public class TokenGetResponse extends Response {
	private TokenGetResponse.Result result;
	
	@ToString
    @Setter
    @Getter
    @Builder
	public static class Result {
		Long memberSeq;
		Integer memberCd;
		String memberType;
		Long createTs;
		Long updateTs;
	}
	
	public TokenGetResponse(TokenData token) {
		this.result = TokenGetResponse.Result.builder()
				.memberSeq(token.getMemberSeq())
				.memberCd(token.getMemberCd())
				.memberType(token.getMemberType())
				.createTs(token.getCreateTs())
				.updateTs(token.getUpdateTs())
				.build();
	}
}
