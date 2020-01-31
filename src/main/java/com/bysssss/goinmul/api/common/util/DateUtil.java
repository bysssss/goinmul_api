package com.bysssss.goinmul.api.common.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.springframework.util.StringUtils;

public class DateUtil {
	public static final String Pattern = "yyyy-MM-dd'T'HH:mm:ssZ";
	public static final String Timezone = "Asia/Seoul";
	
	public static final Long Min1 = 1000L*60L;
	public static final Long Day1 = 1000L*60L*60L*24L;
	public static final Long Day7 = 1000L*60L*60L*24L*7L;
	public static final Long Day30 = 1000L*60L*60L*24L*30L; // 1초 x 60 x 60 == 60분 == 1시간 -> 1시간 x 24 x 30 == 30일 == 한달
	
	public static Date now() {
		return new Date();
	}
	
	public static Date now(long next) {
		return new Date(System.currentTimeMillis() + next);
	}
	
	public static Date nextDay(Date date, int next) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		//cal.add(Calendar.DATE, next);
		cal.add(Calendar.DAY_OF_YEAR, next);
		return cal.getTime();
	}
	
	public static Date nextHour(Date date, int next) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		//cal.add(Calendar.HOUR_OF_DAY, next);
		cal.add(Calendar.HOUR, next);
		return cal.getTime();
	}
	
	public static Date nextMinute(Date date, int next) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.MINUTE, next);
		return cal.getTime();
	}
	
	public static Date toHourly(Date date) {
		if( date==null) {
			return null;
		}
		
		Date hourly = null;
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHH");
			hourly = sdf.parse(sdf.format(date));
			return hourly;
		} catch (ParseException e) {
			return null;
		}
	}
	
	public static Date toDate(String str) {
		if( StringUtils.isEmpty(str)) {
			return null;
		}
		
		SimpleDateFormat sdf = new SimpleDateFormat(DateUtil.Pattern);
		try {
			return sdf.parse(str);
		} catch (ParseException e) {
			return null;
		}
	}
	
	public static Date toDate(String str, String format) {
		if( StringUtils.isEmpty(str)) {
			return null;
		}
		if( StringUtils.isEmpty(format)) {
			return null;
		}
		
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		try {
			return sdf.parse(str);
		} catch (ParseException e) {
			return null;
		}
	}
	
	public static Date toDate(Long timestamp) {
		if( timestamp==null || timestamp < 1L ) {
			return null;
		}
		
		return new Date(timestamp);
	}
	
	public static Long toTimestamp(Date date) {
		if( date==null) {
			return null;
		}
		
		return date.getTime();
	}
	
	public static String toString(Date date) {
		if( date==null) {
			return null;
		}
		
		SimpleDateFormat sdf = new SimpleDateFormat(DateUtil.Pattern);
		return sdf.format(date).toString();
	}
	
	public static String toString(Date date, String format) {
		if( date==null) {
			return null;
		}
		if( StringUtils.isEmpty(format)) {
			return date.toString();
		}
		
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(date).toString();
	}
	
	public static Long difference(Date fromAt, Date toAt) {
		if( fromAt==null || toAt==null) {
			return null;
		}
		
		return  toAt.getTime() - fromAt.getTime();
	}
	
	public static Long compareNow(Date compareAt) {
		if( compareAt==null) {
			return null;
		}
		
		Date now = new Date();
		return now.getTime() - compareAt.getTime();
	}
	
	public static boolean isExpire(Date expireAt) {
		if( expireAt==null) {
			// 만료확인 로직에서, expireAt이 null이라는건... 잘못된 예외상황!
			return true;
		}
		
		Date now = new Date();
		return now.after(expireAt);
	}
	/*
	public static boolean isBefore(Date date) {
		if( date==null) {
			return false;
		}
		
		Date now = new Date();
		return date.before(now);
	}
	
	public static boolean isAfter(Date date) {
		if( date==null) {
			return false;
		}
		
		Date now = new Date();
		return date.after(now);
	}
	*/
}
