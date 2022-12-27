package de.zeroco.date;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;
import java.util.regex.Pattern;

public class DateUtil {
	/**
	 * this method will returns the day of the given date
	 * @author sujwal b
	 * @since 2022-12-16
	 * @param inputDay
	 * @return day
	 */
	public static String getDay(String inputDay) {
		if (isValid(inputDay)) {
			SimpleDateFormat formt = new SimpleDateFormat("dd/MM/yyyy");
			try {
				SimpleDateFormat frmt = new SimpleDateFormat("EEEE");
				return frmt.format(formt.parse(inputDay));
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	/**
	 * this method will checks the given date is valid or not
	 * @author sujwal b
	 * @since 2022-12-16
	 * @param input
	 * @return true or false
	 */
	public static boolean isValid(String input) {
		return Pattern.matches("^(3[01]|[12][0-9]|0[1-9])/(1[0-2]|0[1-9])/[0-9]{4}$", input);
	}
	public static LocalDate findDate(String date,int days) {
		LocalDate input = LocalDate.parse(date);
		LocalDate output =input.plusDays(days);
		return output;
	}

	public static String getDifference(String startDate, String endDate) {
		if (isValid(startDate) && isValid(endDate)) {
			DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			try {
				LocalDate start = LocalDate.parse(startDate, dateFormat);
				LocalDate end = LocalDate.parse(endDate, dateFormat);
				Period diff = Period.between(start, end);
				return "Difference is " + diff.getYears() + " Years " + diff.getMonths() + " Months " + diff.getDays()
						+ " days ";
			} catch (DateTimeParseException e) {
				return "DateTimeParseException";
			}
		}
		return "";
	}

	public static void main(String[] args) {
		System.out.println(getDifference("12/12/2016", "16/12/2022"));
		System.out.println(findDate("2022-12-02", 365));
		System.out.println(getDay("14/01/2023"));
		Date date = new Date();
		System.out.println(date.getTime());
//		System.out.println(DateFormat.getInstance().format(date));
//		System.out.println(DateFormat.getDateInstance().format(date));
//		System.out.println(DateFormat.getDateTimeInstance().format(date));
//		System.out.println(DateFormat.getTimeInstance().format(date));
//		System.out.println(DateFormat.getInstance().getCalendar());
		
	}
	

}
