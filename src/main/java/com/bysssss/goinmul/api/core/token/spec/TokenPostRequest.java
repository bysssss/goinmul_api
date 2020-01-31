package com.bysssss.goinmul.api.core.token.spec;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.bysssss.goinmul.api.common.util.CryptoUtil;
import com.bysssss.goinmul.api.exception.GoinmulException;
import com.bysssss.goinmul.api.model.mysql2.member.data.MemberData;
import com.bysssss.goinmul.api.spec.IRequest;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class TokenPostRequest implements IRequest {
	@NotBlank
	@Size(min = 1, max = 100)
    private String memberId;
	
	@NotBlank
	@Size(min = 1, max = 100)
    private String memberPw;
	
	public MemberData takeMember() {
		return MemberData.builder()
				.memberId(this.memberId)
				.memberPw(this.memberPw)
				.build();
	}

	@Override
	public void check() throws GoinmulException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String toLogging() {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		map.put("class", this.getClass().getSimpleName());
		map.put("memberId", memberId);
		map.put("memberPw", CryptoUtil.hashPassword(memberPw));
		
		return map.toString();
	}
}
