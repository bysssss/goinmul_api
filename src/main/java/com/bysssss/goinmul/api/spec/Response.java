package com.bysssss.goinmul.api.spec;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;

import com.bysssss.goinmul.api.config.MessageSourceConfig;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Setter
@Getter
public class Response implements IResponse {
	//@JsonProperty("resultCode")
	private String resultCode;
	
	//@JsonProperty("resultMessage")
	private String resultMessage;
	
	public ResponseEntity<Response> ok() {
		if( StringUtils.isEmpty(this.resultCode)) {
			this.resultCode = ResultCode.OK;
		}
		if( StringUtils.isEmpty(this.resultMessage)) {
			this.resultMessage = MessageSourceConfig.getMessage(this.resultCode);
		}
		
		return ResponseEntity.ok(this);
	}

	@Override
	public String toLogging() {
		Map<String, Object> map = new LinkedHashMap<String, Object>();
		map.put("class", this.getClass().getSimpleName());
		
		return map.toString();
	}
}
