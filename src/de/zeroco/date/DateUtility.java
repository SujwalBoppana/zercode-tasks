package de.zeroco.date;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

import de.zeroco.util.Utility;

public class DateUtility {
	/**
	 * this method will add minutes  to the given time 
	 * @author sujwal b
	 * @since 2022-12-16
	 * @param minutes
	 * @param date
	 * @return date
	 */
	public static Date addMinToTime(int minutes, Date date) {
		if (Utility.isBlank(date)) {
			return null;
		}
		return new Date((date.getTime() + (minutes*60000)));
	}
	
	/**
	 * this method will adds the duration to the given date
	 * @author sujwal b
	 * @since 2022-12-16
	 * @param input
	 * @param format
	 * @param durationType
	 * @param duration
	 * @return date
	 */
	public static String addDurationToDate(Date input, String format, String durationType, int duration) {
		if (Utility.isBlank(format)||Utility.isBlank(durationType)||Utility.isBlank(duration)) {
			return "";
		}
		DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern(format);
		LocalDateTime start = LocalDateTime.ofInstant(input.toInstant(), ZoneId.systemDefault());
		if (durationType.equalsIgnoreCase("days")) {
			return start.plusDays(duration).format(dateFormat);
		} else if (durationType.equalsIgnoreCase("months")) {
			return start.plusMonths(duration).format(dateFormat);
		} else if (durationType.equalsIgnoreCase("years")) {
			return start.plusYears(duration).format(dateFormat);
		}
		return start.format(dateFormat);
	}
	
	/**
	 * this method subtract the duration to the given date
	 * @author sujwal b
	 * @since 2022-12-16
	 * @param input
	 * @param format
	 * @param durationType
	 * @param duration
	 * @return date
	 */
	public static String subtractDurationToDate(Date input, String format, String durationType, int duration) {
		if (Utility.isBlank(format)||Utility.isBlank(durationType)||Utility.isBlank(duration)) {
			return "";
		}
		DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern(format);
		LocalDateTime start = LocalDateTime.ofInstant(input.toInstant(), ZoneId.systemDefault());
		if (durationType.equalsIgnoreCase("days")) {
			return start.minusDays(duration).format(dateFormat);
		} else if (durationType.equalsIgnoreCase("months")) {
			return start.minusMonths(duration).format(dateFormat);
		} else if (durationType.equalsIgnoreCase("years")) {
			return start.minusYears(duration).format(dateFormat);
		}
		return start.format(dateFormat);
	}
	/**
	 * this method will takes the input date subtracts the days and gives the output date
	 * @author sujwal
	 * @since 2022-12-16
	 * @param input
	 * @param days
	 * @return date
	 */
	public static String subtractDurationToDate(String input,int days) {
		if (Utility.isBlank(input)) {
			return null;
		}
		DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		LocalDate date = LocalDate.parse(input, dateFormat);
		return date.minusDays(days).toString();	
	}
	/**
	 * this method will converts the given date to string in the specified format
	 * @author sujwal b
	 * @since 2022-12-16
	 * @param date
	 * @param format
	 * @return date 
	 */
	public static String dateToString(Date date, String format) {
		if (Utility.isBlank(date)||Utility.isBlank(format)) {
			return null;
		}
		SimpleDateFormat dateFormat = new SimpleDateFormat(format);
		return dateFormat.format(date);
	}
	/**
	 * this method will converts the given date to string 
	 * @author sujwal b
	 * @since 2022-12-16
	 * @param date
	 * @return date 
	 */
	public static String dateToString(Date date) {
		if (Utility.isBlank(date)) 
			return null;
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		return dateFormat.format(date);
	}
	
	/**
	 * this method will convert the string to date 
	 * @author sujwal b
	 * @since 2022-12-16 
	 * @param date
	 * @return date
	 */
	public static Date stringToDate(String date) {
		if (Utility.isBlank(date)) 
			return null;
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Date output = null;
		try {
			output = dateFormat.parse(date);
			return output;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return output;
	}
	/**
	 * this method will converts the string to date according to given datepattern
	 * @author sujwal b
	 * @since 2022-12-16
	 * @param date
	 * @param format
	 * @return date
	 */
	public static Date stringToDate(String date,String format) {
		if (Utility.isBlank(date)) 
			return null;
		SimpleDateFormat dateFormat = new SimpleDateFormat(format);
		Date output = null;
		try {
			output = dateFormat.parse(date);
			return output;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return output;
	}
	/**
	 * this method will adds comma to the given input according to indian number system
	 * @author sujwal b
	 * @since 2022-12-17
	 * @param input
	 * @return number
	 */
	public static String getIndianFormat(double input) {
		if (input <= 1000) {
			return getFormat(",###.00", input);
		} else {
			return getFormat(",##", (int) (input / 1000)) + "," + getFormat("000", (int) (input % 1000))+".00";
		}
	}
	/**
	 * this method will adds comma to the given input according to us number system
	 * @author sujwal b
	 * @since 2022-12-17
	 * @param input
	 * @return number
	 */
	public static String getUSFormat(double input) {
		return getFormat(",###", input);
		
	}
	/**
	 * this method will returns the number formated string according input format 
	 * @author sujwal b
	 * @since 2022-12-17
	 * @param format
	 * @param input
	 * @return string
	 */
	public static String getFormat(String format, double input) {
		if (Utility.isBlankWithVarArguments(format,input)) {
			return "";
		}
		DecimalFormat decimalFormat = new DecimalFormat(format);
		return decimalFormat.format(input);
	}
	/**
	 * this method will compares the given dates and gives the latest date
	 * @author sujwal b
	 * @since 2022-12-16
	 * @param dateOne
	 * @param dateTwo
	 * @return date
	 */
	public static boolean compare(Date dateOne,Date dateTwo) {
		if (dateOne.compareTo(dateTwo)<0) 
			return true;
		return false;
	}
	
	/**
	 * this method will returns the earlier date
	 * @param dateOne
	 * @param dateTwo
	 * @return date
	 */
	public static Date getElder(Date dateOne, Date dateTwo) {
		if (dateOne.before(dateTwo)) {
			return dateOne;
		}
		return dateTwo;
	}
	/**
	 * this method will takes the input as date gives output as number of days
	 * @author sujwal b
	 * @since 2022-12-15
	 * @param inputDate
	 * @return count
	 */
	public static long getDays(Date inputDate) {
		Date date = new Date();
		return (date.getTime() - inputDate.getTime()) / (1000 * 60 * 60 * 24);
	}
	/**
	 * this method will returns the days from date and to date
	 * @author sujwal b
	 * @since 2022-12-15
	 * @param fromDate
	 * @param twoDate
	 * @return days
	 */
	public static long getDays(Date fromDate, Date twoDate) {
		return (twoDate.getTime() - fromDate.getTime()) / (1000 * 60 * 60 * 24);
	}
	/**
	 * this method will gives the difference of two dates
	 * @author sujwal b
	 * @since 2022-12-15
	 * @param fromDate
	 * @param toDate
	 * @return years and days
	 */
	public static String getDifference(String fromDate, String toDate) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date dateOne = format.parse(fromDate);
			Date dateTwo = format.parse(toDate);
			long difference = dateTwo.getTime() - dateOne.getTime();
			long diffInSeconds = (difference / 1000) % 60;
			long diffInMinutes = (difference / (1000 * 60)) % 60;
			long diffInHours = (difference / (1000 * 60 * 60)) % 24;
			long diffInDays = (difference / (1000 * 60 * 60 * 24));
			long diffInYears = (difference / (1000l * 60 * 60 * 24 * 365)) % 365;
			return diffInYears + " years, " + diffInDays + " days, " + diffInHours
					+ " hours, " + diffInMinutes + " minutes, " + diffInSeconds + " seconds";
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * this method will check the given pattern is in the dd/mm/yyyy format
	 * @author sujwal b
	 * @since 2022-12-15
	 * @param input
	 * @return true or false
	 */
	@Deprecated
	public static boolean getValidate(String input) {
	return Pattern.matches("^(3[01]|[12][0-9]|0[1-9])/(1[0-2]|0[1-9])/[0-9]{4}$", input);
	}
	public static List<String> getDetails(List<String>dOB,String search){
		List<String>output = new ArrayList<>();
		for (String data : dOB) {
			if(data.contains(search))
				output.add(data);
		}	
		return null;	
	}

	public static void main(String[] args) {
		System.out.println(getFormat(null, 456135));
		System.out.println(getUSFormat(456445454));
		System.out.println(getIndianFormat(100000456));
		System.out.println(stringToDate("22/12/2022"));
		System.out.println(stringToDate("22-12-2022", "dd-MM-yy"));
		System.out.println(compare(new Date(2022 - 1900, 01, 01), new Date(2022 - 1900, 12, 16)));
		System.out.println(addMinToTime(10, new Date()));
		System.out.println(addDurationToDate(new Date(2022-1900,12,19), "dd/MM/yyyy", "months", 4));
		System.out.println(addDurationToDate(new Date(2022 - 1900, 11, 16), null, null, 4));
		System.out.println(subtractDurationToDate(new Date(2022 - 1900, 11, 16), "dd/MM/yy", "months", 4));
	}
	
	@SuppressWarnings("deprecation")
	public static void main01(String[] args) throws ParseException {
		System.out.println(getElder(new Date(2000, 11, 01,00,00), new Date(2000, 10, 01,00,00)));
		System.out.println(getValidate("12/12/2000"));
		System.out.println(getDifference("2000-06-08 00:00:00", "2022-06-08 00:00:00"));
		System.out.println("days  "+getDays(new Date(2022-1900,11,15)));
		SimpleDateFormat formt = new SimpleDateFormat("yyyy-MM-dd");
		Date date = formt.parse("2022-12-12");
		System.out.println(date);
		System.out.println(getDays(new Date(2022 , 01, 12), new Date(2022 , 11, 31)));
		SimpleDateFormat dateFormatter = new SimpleDateFormat("E, y-M-d 'at' h:m:s a z");
		Date dateOne = new Date();
		System.out.println(dateFormatter.format(dateOne));
		List<String>dOB=new ArrayList<>(Arrays.asList("10/12/2022","15/12/2022","12/12/2000","12/13/2000"));
		
		for (String string : dOB) {
			System.out.println(getValidate(string));
		}
		
		
		
		// System.out.println(getDays(new Date(2022,12,01)));
//		Date date = new Date();
//		System.out.println(date);
		String format = "10/12/2022";
		String formatOne = "15/12/2022";
		Date dateFrom = null;
		Date datesTwo = null;
		try {
			dateFrom = new SimpleDateFormat("dd/MM/yyyy").parse(format);
			datesTwo = new SimpleDateFormat("dd/MM/yyyy").parse(formatOne);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		long days = (datesTwo.getTime() - dateFrom.getTime());
		System.out.println((int) days / (1000 * 60 * 60 * 24));
//	//	System.out.println(dates+"--------");
//		Date dateTwo = new Date(System.currentTimeMillis());
//		System.out.println(dateTwo);
//		// Date dateThree = new Date(2000, 11, 21);
//		Date dateFour = new Date(2010, 1, 3);
//		System.out.println(date.after(dateFour));
//		System.out.println(date.before(dateFour));
//		System.out.println(date.getYear() + 1900);
//		System.out.println(date.getMonth());
//		System.out.println(date.getDate());
//		System.out.println(date.getDay());
//		System.out.println(date.getHours());
//		date.setYear(2000 - 1900);
//		System.out.println(date);

	}


}
