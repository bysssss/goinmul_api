package com.bysssss.goinmul.api.core.member.spec;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.bysssss.goinmul.api.exception.GoinmulException;
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
public class MemberGetRequest implements IRequest {
	@NotNull
	@Min(100000L)
    private Long seq;

	@Override
	public void check() throws GoinmulException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String toLogging() {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		map.put("class", this.getClass().getSimpleName());
		map.put("seq", seq);
		
		return map.toString();
	}
}
