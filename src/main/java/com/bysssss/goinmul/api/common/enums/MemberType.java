package com.bysssss.goinmul.api.common.enums;

public enum MemberType {
	TYPE0("타입 0"), 
	TYPE1("타입 1"),
	TYPE2("타입 2"), 
	NONE(null)
    ;
	
	private String type;
	
	MemberType(String type) {
		this.type = type;
    }

    public String type() {
        return this.type;
    }

}
