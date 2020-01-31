package com.bysssss.goinmul.api.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

import com.bysssss.goinmul.api.exception.GoinmulException;
import com.bysssss.goinmul.api.spec.IRequest;

//@Slf4j
//@Aspect
//@Component
public class PermissionAspect {
	/*
	@Pointcut("execution(* com.bysssss.goinmul.api.core.member.controller.MemberController.*(..))"
			+ "|| execution(* com.bysssss.goinmul.api.core.contents.controller.ContentsController.*(..))")
	public void onPermissionPointcut() {
	}
	
	@Before("onPermissionPointcut()")
	public void onBeforePermissionAdvice(JoinPoint joinPoint) throws GoinmulException {
		for( Object arg : joinPoint.getArgs()) {
			if( arg instanceof 회원 비지니스 타입 ) {
		
			}
			if( arg instanceof 콘텐츠 수정 및 삭제 권한) {
		
			}
		}
	}
	*/
}
