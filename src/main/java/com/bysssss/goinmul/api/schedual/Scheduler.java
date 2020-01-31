package com.bysssss.goinmul.api.schedual;

import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.bysssss.goinmul.api.common.util.DateUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Profile("prod")
@EnableScheduling
@Component
public class Scheduler {
	
	@Scheduled(cron = "0 0 9 * * *")
	@Transactional(propagation=Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor=Exception.class)
    public void tokenJob() {
		log.info("Scheduler tokenJob()");
		this.waitToAvoidTheScheduledJobsConflictOfMultiServers();
		// 만료된 토큰 삭제
	}
	
	@Scheduled(cron = "0 0 12 * * *")
	@Transactional(propagation=Propagation.REQUIRED, isolation=Isolation.DEFAULT, rollbackFor=Exception.class)
    public void memberJob() {
		log.info("Scheduler memberJob()");
		this.waitToAvoidTheScheduledJobsConflictOfMultiServers();
		// 로그아웃 -> 6개월 -> 휴면회원
		// 휴면회원 -> 3개월 -> 탈퇴회원
		// 탈퇴회원 -> 1개월 -> 파기
	}
	
	private void waitToAvoidTheScheduledJobsConflictOfMultiServers() {
		Double sleep = Math.random() * 1000.0d;
		try {
			System.out.println("sleep() : " + sleep.intValue());
			Thread.sleep(sleep.intValue() * 1000);
			System.out.println("wakeup() : " + DateUtil.now());
		} catch (InterruptedException e) {
			System.out.println(e.getMessage());
		}
	}
}
