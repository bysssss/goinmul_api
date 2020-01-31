package com.bysssss.goinmul.api.core.member.spec;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Pattern;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.util.StringUtils;

import com.bysssss.goinmul.api.common.util.CryptoUtil;
import com.bysssss.goinmul.api.exception.GoinmulException;
import com.bysssss.goinmul.api.model.mysql2.member.data.MemberData;
import com.bysssss.goinmul.api.spec.IRequest;
import com.bysssss.goinmul.api.spec.ResultCode;

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
public class MemberPostRequest implements IRequest {
	@NotBlank
	@Size(min = 1, max = 100)
    private String memberId;
	
	@NotBlank
	@Size(min = 1, max = 100)
    private String memberPw;
	
	@Size(min = 0, max = 50)
	private String memberNickname;
	
	@NotBlank
	@Size(min = 1, max = 50)
	private String cellphone;
	
	@Size(min = 0, max = 50)
	private String telephone;
	
	public MemberData takeMember() {
		return MemberData.builder()
				.memberId(this.memberId)
				.memberPw(this.memberPw)
				.memberNickname(this.memberNickname)
				.cellphone(this.cellphone)
				.telephone(this.telephone)
				.build();
	}

	@Override
	public void check() throws GoinmulException {
		if( !Pattern.matches("^(?=.*[a-zA-Z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{9,20}$", this.memberPw)) {
			// ^(?=.*[a-zA-Z])(?=.*?[0-9])(?=.*?[~!@#$%^&*()_+`\-\=|\\{}\[\]:";',./<>?]).{8,20}$
			throw new GoinmulException(ResultCode.BAD_REQUEST1, "영문, 숫자, 특수문자 조합으로 최소 9~20자 이내로 사용 가능합니다.");
		}
		
		// TODO Auto-generated method stub
	}

	@Override
	public String toLogging() {
		String telephone2 = null;
		if( !StringUtils.isEmpty(telephone) && telephone.length() > 7) {
			StringBuffer sb = new StringBuffer(telephone);
			telephone2 = (sb.replace(3, 7, "****")).toString();
		}
		
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		map.put("class", this.getClass().getSimpleName());
		map.put("memberId", memberId);
		map.put("memberPw", CryptoUtil.hashPassword(memberPw));
		map.put("memberNickname", memberNickname);
		map.put("cellphone", CryptoUtil.encPrivacy(cellphone));
		map.put("telephone", telephone2);
		
		return map.toString();
	}
}
