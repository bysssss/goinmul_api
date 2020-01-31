package com.bysssss.goinmul.api.aop;

import java.util.UUID;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.MDC;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.bysssss.goinmul.api.exception.GoinmulException;
import com.bysssss.goinmul.api.spec.IRequest;
import com.bysssss.goinmul.api.spec.Response;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Aspect
@Component
public class LogAspect {
	
	@Pointcut("execution(* com.bysssss.goinmul.api.core.member.controller.MemberController.*(..))"
			+ "|| execution(* com.bysssss.goinmul.api.core.token.controller.TokenController.*(..))"
			+ "|| execution(* com.bysssss.goinmul.api.core.contents.controller.ContentsController.*(..))")
	public void onLogPointcut() {
	}
	
	@Before("onLogPointcut()")
	public void onBeforeLogAdvice(JoinPoint joinPoint) {
		MDC.put("mdcKey", UUID.randomUUID().toString().substring(0,8));
		MDC.put("startTime", Long.valueOf(System.currentTimeMillis()).toString());
		
		for( Object arg : joinPoint.getArgs()) {
			if( arg instanceof IRequest) {
				IRequest request = (IRequest)arg;
				MDC.put("request", request.toLogging());
			}
		}
	}
	
	@AfterReturning(pointcut = "onLogPointcut()", returning = "returning")
	public void onAfterReturningLogAdvice(JoinPoint joinPoint, Object returning) {
		if( returning != null && returning instanceof ResponseEntity<?>) {
			try {
				ResponseEntity<Response> entity = (ResponseEntity<Response>)returning;
				Response response = entity.getBody();
				MDC.put("response", response.toLogging());
				MDC.put("resultCode", response.getResultCode());
				MDC.put("resultMessage", response.getResultMessage());
			} catch(Exception e) {
			}
		}
		
		this.log();
	}
	
	@AfterThrowing(pointcut = "onLogPointcut()", throwing = "throwing")
	public void onAfterThrowingLogAdvice(JoinPoint joinPoint, Object throwing) {
		if( throwing != null && throwing instanceof GoinmulException) {
			GoinmulException goinmulException = (GoinmulException)throwing;
			Response response = goinmulException.takeResponse();
			MDC.put("response", response.toLogging());
			MDC.put("resultCode", response.getResultCode());
			MDC.put("resultMessage", response.getResultMessage());
		}
		
		this.log();
	}
	
	private void log() {
		String method = MDC.get("method");
		String uri = MDC.get("uri");
		String param = MDC.get("param");
		//String session = MDC.get("session");
		
		String time = this.time(MDC.get("startTime"));
		
		String request = MDC.get("request");
		String response = MDC.get("response");
		String resultCode = MDC.get("resultCode");
		String resultMessage = MDC.get("resultMessage");
		
		StringBuffer sb = new StringBuffer();
		// GIM|method|uri|param|time|token|memSeq|request|response|resultCode|resultMessage
		sb.append("GIM")
		.append("|").append(method).append("|").append(uri).append("|").append(param).append("|").append(time)
		.append("|").append("token").append("|").append("memSeq")
		.append("|").append(request).append("|").append(response).append("|").append(resultCode).append("|").append(resultMessage);
		log.info("{}", sb.toString());
		
		MDC.clear();
	}
	
	private String time(String startTime) {
		if( StringUtils.isEmpty(startTime)) {
			return null;
		}
		
		String time = null;
		try {
			Long endTime = System.currentTimeMillis();
			time = String.format("%.2f", (double)((endTime - Long.parseLong(startTime))/1000.0));
		} catch(Exception e) {
			return null;
		}
		
		return time;
	}
}
