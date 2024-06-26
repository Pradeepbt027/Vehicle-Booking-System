package com.eikona.tech.util;

import java.util.Calendar;
import java.util.Date;

import org.springframework.stereotype.Component;

@Component
public class CalendarUtil {

	public Date getConvertedDate(Date date, int hr, int min, int sec) {
		Calendar calender = Calendar.getInstance();
		calender.setTime(date);
		calender.set(Calendar.HOUR, hr);
		calender.set(Calendar.MINUTE, min);
		calender.set(Calendar.SECOND, sec);
		
		return calender.getTime();
	}
	
	public Date getConvertedDateSetSec(Date date, int sec) {
		Calendar calender = Calendar.getInstance();
		calender.setTime(date);
		calender.set(Calendar.SECOND, sec);
		
		return calender.getTime();
	}
	
	public Calendar getCalendar(Date date, int hr, int min, int sec) {
		Calendar calender = Calendar.getInstance();
		calender.setTime(date);
		calender.set(Calendar.HOUR, hr);
		calender.set(Calendar.MINUTE, min);
		calender.set(Calendar.SECOND, sec);
		
		return calender;
	}
	public Calendar dateToCalendar(Date date) {

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar;

	}
	public Date getConvertedDate(Date date, int day, int hr, int min, int sec) {
		Calendar calender = Calendar.getInstance();
		calender.setTime(date);
		calender.set(Calendar.DATE, day);
		//calender.add(Calendar.DATE, day);
		calender.set(Calendar.HOUR, hr);
		calender.set(Calendar.MINUTE, min);
		calender.set(Calendar.SECOND, sec);
		
		return calender.getTime();
	}
	
	
	
	public Date getPreviousDate(Date date, int day, int hr, int min, int sec) {
		Calendar calender = Calendar.getInstance();
		calender.setTime(date);
		calender.add(Calendar.DATE, day);
		calender.set(Calendar.HOUR, hr);
		calender.set(Calendar.MINUTE, min);
		calender.set(Calendar.SECOND, sec);
		
		return calender.getTime();
	}
	
	public Date getPreviousDateByHr(Date date, int hr, int sec) {
		Calendar calender = Calendar.getInstance();
		calender.setTime(date);
		calender.add(Calendar.HOUR, hr);
		calender.set(Calendar.SECOND, sec);
		
		return calender.getTime();
	}
}
