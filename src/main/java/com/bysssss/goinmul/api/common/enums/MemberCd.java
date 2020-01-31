package com.bysssss.goinmul.api.common.enums;

public enum MemberCd {
	NORMAL(0), 
	DORMANT(1),
	WITHDRAW(2), 
	NONE(null)
    ;
	
	private Integer code;
	
	MemberCd(Integer code) {
		this.code = code;
    }

    public Integer code() {
        return this.code;
    }

}
