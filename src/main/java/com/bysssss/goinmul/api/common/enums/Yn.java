package com.bysssss.goinmul.api.common.enums;

public enum Yn {
	Y("Y"), 
	N("N");

	private String yn;
	
	Yn(String yn) {
		this.yn = yn;
	}
	
	public String getYn() {
		return yn;
	}

	public void setYn(String yn) {
		this.yn = yn;
	}
	
	public static boolean isYes(String yn) {
		if( "Y".equals(yn)) {
			return true;
		} else {
			return false;
		}
	}
	
	public static Yn convert(String yn) {
		if( "Y".equals(yn)) {
			return Yn.Y;
		} else {
			return Yn.N;
		}
    }
	
	public static Yn convert(Boolean isYn) {
		if( isYn == null) {
    		return Yn.N;
        }
		
		if(isYn) {
    		return Yn.Y;
        } else {
			return Yn.N;
		}
    }

}
