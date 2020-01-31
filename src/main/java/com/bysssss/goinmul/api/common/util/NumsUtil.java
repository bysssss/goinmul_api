package com.bysssss.goinmul.api.common.util;

public class NumsUtil {
	public static Double toDouble(String str) {
		Double dbl = null;
		try {
			//Double.parseDouble(str); // double
			dbl = Double.valueOf(str);
		} catch (NumberFormatException e) {
			return null;
		}
        
        return dbl;
    }
	
	public static Long toLong(String str) {
		Long int64 = null;
		try {
			//Long.parseDouble(str); // double
			int64 = Long.valueOf(str);
		} catch (NumberFormatException e) {
			return null;
		}
        
        return int64;
    }
}
