package de.zeroco.util;

import java.lang.reflect.Array;
import java.util.Collection;
import java.util.Map;
import java.util.regex.Pattern;

public class Utility {
	public final static String ALPHABETS = "abcdefghijklmnopqrstuvwxyz";
	public final static String ALPHANUMERIC = ALPHABETS.toUpperCase() + ALPHABETS + "0123456789";
	/**
	 * this method will generates a captcha of 5 letters
	 * @author sujwal B
	 * @since 2022-12-01
	 * @return captcha
	 */
	public static String getCaptcha() {
		return getCaptcha(5);
	}

	/**
	 * this method will generates a captcha of given size with alphabets
	 * @author sujwal B
	 * @since 2022-12-01
	 * @param size
	 * @return captcha
	 */
	public static String getCaptcha(int size) {
		if (size <= 10) {
			String captcha = "";
			String allCasing = ALPHABETS + ALPHABETS.toUpperCase();
			for (int i = 0; i < size; i++) {
				int temp = (int) (Math.random() * (allCasing).length());
				captcha += (allCasing).charAt(temp);
			}
			return captcha;
		}
		return getCaptcha(10);
	}

	/**
	 * this method will generates a captcha of given size with alphanumeric
	 * @author sujwal B
	 * @since 2022-12-01
	 * @param size
	 * @param type
	 * @return captcha
	 */
	public static String getCaptcha(int size, String type) {
		if (isBlank(type) || type.equalsIgnoreCase("alphabet")) {
			return getCaptcha(size);
		}
		if (size <= 10) {
			String captcha = "";
			if ("alphanumeric".equalsIgnoreCase(type)) {
				for (int i = 0; i < size; i++) {
					int temp = (int) (Math.random() * ALPHANUMERIC.length()); // it is used to generate random number
					captcha += ALPHANUMERIC.charAt(temp);
				}
				return captcha;
			}
		}
		return getCaptcha(10,"alphanumeric");
	}
	/**
	 * this method will check wheather the given input is empty or not
	 * @author sujwal B
	 * @since 2022-12-02
	 * @param input
	 * @return true or false
	 */
	public static boolean isBlank(Object input) {
		if(input==null) 
			return true;
		else if (input instanceof String) {
			String variable = (String) input;
			if ((variable.trim().equals(""))) 
				return true;
		} else if (input instanceof Boolean) {
			if ((Boolean) input == false)
				return true;
		} else if (input instanceof Character) {
			if ((Character) input == ' ') 
				return true;
		} else if (input instanceof Byte) {
			if ((Byte) input <= 0)
				return true;
		} else if (input instanceof Short) {
			if ((Short) input <= 0) 
				return true;
		} else if (input instanceof Integer) {
			if ((Integer) input <= 0) 
				return true;
		}  else if (input instanceof Long) {
			if ((Long) input <= 0)
				return true;
		} else if (input instanceof Float) {
			if ((Float) input <= 0)
				return true;
		} else if (input instanceof Double) {
			if ((Double) input <= 0)
				return true;
		} else if (input.getClass().isArray()) {
			if (Array.getLength(input)==0)
				return true;
		} else if (input instanceof Collection<?>) {
			if (((Collection<?>) input).size()==0)
				return true;
		} else if(input instanceof Map<?, ?>) {
			if (((Map<?, ?>) input).size()==0) 
				return true;
		}
			
		return false;
	}
	public static boolean isBlankWithVarArguments(Object...input) {
		if (isBlank(input)) {
			return true;
		}
		for (Object object : input) {
			if (isBlank(object)) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * this method will check wheather the given input has data or not
	 * @author sujwal B
	 * @since 2022-12-02
	 * @param input
	 * @return true or false
	 */
	public static boolean hasData(Object input) {
		return !isBlank(input);
	}

	/**
	 * this method will check wheather the given password is as per requirements
	 * @author sujwal B
	 * @since 2022-12-01
	 * @param input
	 * @return true 0r false
	 * @throws NullPointerException
	 */
	public static boolean isValidPassword(String input) {
		if(isBlank(input)) {
			return false;
		} 
		return Pattern.compile("^(?=.*[0-9])(?=.*[A-Z])(?=.*[!@#&$]).{8,15}$").matcher(input).find();
	}

	/**
	 * this method will gives the string that is present inside the start and end
	 * strings
	 * @author sujwal B
	 * @since 2022-12-01
	 * @param data
	 * @param start
	 * @param end
	 * @return required String
	 * @throws StringIndexOutOfBoundsException
	 */
	public static String getTicket(String data, String start, String end) {
		if(isBlank(data)||isBlank(start)||isBlank(end)) {
			return "";
		}
		try {
			return data.substring(data.indexOf(start) + 2, data.indexOf(end));
		} catch (ArrayIndexOutOfBoundsException e) {
			return "";
		}
	}
}
