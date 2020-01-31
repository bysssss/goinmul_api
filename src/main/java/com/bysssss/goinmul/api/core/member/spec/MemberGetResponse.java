package com.bysssss.goinmul.api.core.member.spec;

import java.util.Date;

import com.bysssss.goinmul.api.model.mysql2.member.data.MemberData;
import com.bysssss.goinmul.api.spec.Response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Setter
@Getter
public class MemberGetResponse extends Response {
	private MemberGetResponse.Result result;
	
	@ToString
    @Setter
    @Getter
    @Builder
	public static class Result {
		Long seq;
		String memberId;
		String memberPw;
		Integer memberCd;
		String memberType;
		String memberNickname;
		String memberThumbnailUrl;
		String memberSex;
		String memberInfo;
		String cellphone;
		String telephone;
		Date passwordAt;
		Date createAt;
		Date updateAt;
	}
	
	public MemberGetResponse(MemberData data) {
		if( data==null) {
			this.result = null;
			return;
		}
		
		this.result = MemberGetResponse.Result.builder()
				.seq(data.getSeq())
				.memberId(data.getMemberId())
				.memberPw(data.getMemberPw())
				.memberCd(data.getMemberCd())
				.memberType(data.getMemberType())
				.memberNickname(data.getMemberNickname())
				.memberThumbnailUrl(data.getMemberThumbnailUrl())
				.memberSex(data.getMemberSex())
				.memberInfo(data.getMemberInfo())
				.cellphone(data.getCellphone())
				.telephone(data.getTelephone())
				.passwordAt(data.getPasswordAt())
				.createAt(data.getCreateAt())
				.updateAt(data.getUpdateAt())
				.build();
	}
}
