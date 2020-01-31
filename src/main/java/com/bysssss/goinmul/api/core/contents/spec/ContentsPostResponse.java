package com.bysssss.goinmul.api.core.contents.spec;

import com.bysssss.goinmul.api.spec.Response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Setter
@Getter
public class ContentsPostResponse extends Response {
	private ContentsPostResponse.Result result;
	
	@ToString
    @Setter
    @Getter
    @Builder
	public static class Result {
		Long seq;
	}
	
	public ContentsPostResponse(Long seq) {
		this.result = ContentsPostResponse.Result.builder()
				.seq(seq)
				.build();
	}
}
