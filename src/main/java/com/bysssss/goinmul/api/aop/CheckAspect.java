package com.bysssss.goinmul.api.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

import com.bysssss.goinmul.api.exception.GoinmulException;
import com.bysssss.goinmul.api.spec.IRequest;

//@Slf4j
//@Aspect
//@Component
public class CheckAspect {
	/*
	@Pointcut("execution(* com.bysssss.goinmul.api.core.member.service.MemberService.*(..))"
			+ "|| execution(* com.bysssss.goinmul.api.core.token.service.TokenService.*(..))"
			+ "|| execution(* com.bysssss.goinmul.api.core.contents.service.ContentsService.*(..))")
	public void onCheckPointcut() {
	}
	
	@Before("onCheckPointcut()")
	public void onBeforeCheckAdvice(JoinPoint joinPoint) throws GoinmulException {
		for( Object arg : joinPoint.getArgs()) {
			//if( arg instanceof String && ((String) arg).contains("RBX ")) { ... }
			if( arg instanceof IRequest) {
				IRequest request = (IRequest)arg;
				request.check();
			}
		}
	}
	*/
}
