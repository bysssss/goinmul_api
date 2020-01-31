package com.bysssss.goinmul.api.spec;

public class ResultCode {
	public static final String OK = "200000";
    
    public static final String BAD_REQUEST = "400000";
    public static final String BAD_REQUEST1 = "400001";
    public static final String UNAUTHORIZED = "401000";
    public static final String PAYMENT_REQUIRED = "402000";
    public static final String FORBIDDEN = "403000";
    public static final String NOT_FOUND = "404000";
    public static final String PROXY_AUTHENTICATION_REQUIRED = "407000";
    public static final String REQUEST_TIMEOUT = "408000";
    public static final String CONFLICT = "409000";
    public static final String GONE = "410000";
    public static final String RANGE_NOT_SATISFIABLE = "416000";
    public static final String I_AM_A_TEAPOT = "418000";
    public static final String LOCKED = "423000";
    public static final String TOO_MANY_REQUESTS = "429000";
    
    public static final String INTERNAL_SERVER_ERROR = "500000";
    public static final String BAD_GATEWAY = "502000";
    public static final String SERVICE_UNAVAILABLE = "503000";
    
    public static final String DB_EXCEPTION = "555000";
    
    public static final String UNKNOWN = "999999";
}
