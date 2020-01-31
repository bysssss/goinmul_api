package com.bysssss.goinmul.api.spec;

import com.bysssss.goinmul.api.exception.GoinmulException;

public interface IRequest {
	public void check() throws GoinmulException;
	public String toLogging();
}
