package com.bysssss.goinmul.api.model.redis.token.data;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class TokenData {
	//Long seq;
	Long memberSeq;
	Integer memberCd;
	String memberType;
	Long createTs;
	Long updateTs;
}