package com.bysssss.goinmul.api.core.contents.spec;

import java.util.Date;

import com.bysssss.goinmul.api.model.mysql.contents.data.ContentsData;
import com.bysssss.goinmul.api.spec.Response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Setter
@Getter
public class ContentsGetResponse extends Response {
	private ContentsGetResponse.Result result;
	
	@ToString
    @Setter
    @Getter
    @Builder
	public static class Result {
		Long seq;
		String id;
		Long memberSeq;
		String contentsTitle;
		String contentsDesc;
		String contentsType;
		Integer contentsCount;
		Integer testInt;
		Double testDbl;
		Double testGps;
		String testYn;
		Date createAt;
		Date updateAt;
	}
	
	public ContentsGetResponse(ContentsData data) {
		if( data==null) {
			this.result = null;
			return;
		}
		
		this.result = ContentsGetResponse.Result.builder()
				.seq(data.getSeq())
				.memberSeq(data.getMemberSeq())
				.contentsTitle(data.getContentsTitle())
				.testInt(data.getTestInt())
				.testDbl(data.getTestDbl())
				.testGps(data.getTestGps())
				.build();
	}
}
