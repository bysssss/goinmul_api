package com.bysssss.goinmul.api.exception;

import com.bysssss.goinmul.api.spec.Response;

public class GoinmulException extends Exception {
	private static final long serialVersionUID = 6763011797919819654L;
	
	private Response response;
	
	public GoinmulException() {
		this.response = new Response();
	}
	
	public GoinmulException(String code, String message) {
		this.response = new Response();
		this.response.setResultCode(code);
		this.response.setResultMessage(message);
	}
	
	public Response takeResponse() {
		return this.response;
	}
}
