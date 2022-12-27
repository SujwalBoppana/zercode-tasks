package de.zeroco.date;

import java.util.Calendar;

public class CalendarUtil {
	public static void main(String[] args) {
		Calendar calendar = Calendar.getInstance();
		System.out.println(calendar.getMaximum(Calendar.DAY_OF_WEEK));
		System.out.println(calendar.getMaximum(Calendar.DAY_OF_MONTH));
		System.out.println(calendar.getTime());
	}
}
