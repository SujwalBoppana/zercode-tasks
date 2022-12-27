package de.zeroco.util;

import java.lang.reflect.Array;
import java.util.List;

public class Instance {
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
		} else if (input instanceof List<?>) {
			if (((List<?>) input).size() == 0)
				return true;
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

	public static void main(String[] args) {
		int [] temp = {4,6,5};
		System.out.println(isBlank(temp));
		
		//System.out.println(isBlank(one));
		
		//System.out.println(hasData(5.44444));
	}

}
