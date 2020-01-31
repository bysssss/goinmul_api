package com.bysssss.goinmul.api.core.contents.spec;

import com.bysssss.goinmul.api.spec.Response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Setter
@Getter
public class ContentsDeleteResponse extends Response {
	private ContentsDeleteResponse.Result result;
	
	@ToString
    @Setter
    @Getter
    @Builder
	public static class Result {
		Integer count;
	}
	
	public ContentsDeleteResponse(Integer count) {
		this.result = ContentsDeleteResponse.Result.builder()
				.count(count)
				.build();
	}
}
