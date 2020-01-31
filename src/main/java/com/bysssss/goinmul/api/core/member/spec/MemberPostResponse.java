package com.bysssss.goinmul.api.core.member.spec;

import com.bysssss.goinmul.api.spec.Response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Setter
@Getter
public class MemberPostResponse extends Response {
	private MemberPostResponse.Result result;
	
	@ToString
    @Setter
    @Getter
    @Builder
	public static class Result {
		Long seq;
	}
	
	public MemberPostResponse(Long seq) {
		this.result = MemberPostResponse.Result.builder()
				.seq(seq)
				.build();
	}
}
