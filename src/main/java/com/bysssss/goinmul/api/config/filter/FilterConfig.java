package com.bysssss.goinmul.api.config.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.MDC;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig implements Filter {
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest)request;
		HttpServletResponse httpResponse = (HttpServletResponse)response;
		//httpResponse.addHeader("Access-Control-Allow-Origin", "*");
		//httpResponse.addHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE");
		//httpResponse.addHeader("Access-Control-Max-Age", "3600");
		//httpResponse.addHeader("Access-Control-Allow-Headers", "X-Requested-With, Content-Type, Authorization, Origin, Accept, Access-Control-Request-Method, Access-Control-Request-Headers");
		//httpResponse.addHeader("Access-Control-Allow-Credentials", "true");
		
		MDC.put("method", httpRequest.getMethod());
		MDC.put("uri", httpRequest.getRequestURI());
		MDC.put("param", httpRequest.getQueryString());
		MDC.put("session", httpRequest.getRequestedSessionId());
		
		chain.doFilter(httpRequest, httpResponse);		
	}
	
	// TODO : lucy-xss
	
}
