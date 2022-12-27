package de.zeroco.util;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class WrapperClassUtility {
	/**
	 * This method will gives the greatest of two number
	 * @author sujwal b
	 * @since 2022-12-07
	 * @param first
	 * @param second
	 * @return value
	 */
	public static int getGreatest(int first, int second) {
		int temp = Integer.compare(first, second);
		if (temp == 0) {
			return first;
		} else if (temp < 0) {
			return second;
		}
		return first;
	}
	/**
	 * This method will gives the greatest of three numbers
	 * @author sujwal b
	 * @since 2022-12-07
	 * @param first
	 * @param second
	 * @param third
	 * @return largest number
	 */
	public static int getGreatest(int first, int second, int third) {
		return Integer.max(Integer.max(first, second), third);
	}
	
	public static int getSmallest(int first, int second, int third) {
		return Integer.min(Integer.min(first, second), third);
	}

	public static void main(String[] args) {
		System.out.println(getGreatest(23, 45, 72));
		System.out.println(getSmallest(45, 34, 67));
		
		int valueOne = 23;
		short valueTwo = 122;
		byte valueThree = 127;
		long valueFour = 123231414;
		char valueFive = 'a';
		boolean valueSix = true;
		double valueSeven = 23123.3343;
		float valueEight = 1112.234f;
		//autoBoxing
		Integer intOne = valueOne;
		Short shortOne = valueTwo;
		Byte byteOne = valueThree;
		Long longOne = valueFour;
		Character charOne = valueFive;
		Boolean booleanOne = valueSix;
		Double doubleOne = valueSeven;
		Float floatOne = valueEight;
		
		System.out.println(intOne);
		System.out.println(shortOne);
		System.out.println(byteOne);
		System.out.println(longOne);
		System.out.println(charOne);
		System.out.println(booleanOne);
		System.out.println(doubleOne);
		System.out.println(floatOne);
		
		int intTwo = intOne;
		short shortTwo = shortOne;
		byte byteTwo = byteOne;
		long longTwo = longOne;
		char charTwo = charOne;
		boolean booleanTwo = booleanOne;
		double doubleTwo = doubleOne;
		float floatTwo = floatOne;
		
		System.out.println(intTwo);
		System.out.println(shortTwo);
		System.out.println(byteTwo);
		System.out.println(longTwo);
		System.out.println(charTwo);
		System.out.println(booleanTwo);
		System.out.println(doubleTwo);
		System.out.println(floatTwo);
		
		
		
		
		int first = 550;
		Integer two = new Integer(first);
		System.out.println(two);
		Integer three = new Integer("23111123");
		System.out.println(three);
		int four = three;
		System.out.println(four);
		System.out.println(Integer.MAX_VALUE);
		System.out.println(Integer.MIN_VALUE);
		System.out.println(Integer.SIZE);
		System.out.println(Integer.compare(first, four));
		System.out.println(getGreatest(57, 56));
		System.out.println(Long.decode("12355555534436"));
		System.out.println(Integer.reverse(four));
		int value = 56745;
		String temp = Integer.toString(four);
		System.out.println(temp);
		char oneChar = 'a';
		Character twoCharacter = oneChar;
		System.out.println(twoCharacter);
		boolean oneBoolean = true;
		Boolean twoBoolean = oneBoolean;
		System.out.println(twoBoolean);
		Boolean threeBoolean = false;
		System.out.println("CompareTo method:" + twoBoolean.compareTo(threeBoolean)); // compare method
		System.out.println("Character count method:" + Character.charCount(value));
		System.out.println(Character.toTitleCase(oneChar)); // title case method
		System.out.println(Integer.max(first, four)); // max method
		System.out.println(Integer.min(first, four)); // min method
		System.out.println(Integer.parseInt("75482"));

	}

	public static void serialization() {

		int valueOne = 56745;
		int valueTwo = 0;
		try {
			FileOutputStream file = new FileOutputStream("D:\\files\\temp.txt");
			ObjectOutputStream out = new ObjectOutputStream(file);
			out.writeObject(valueOne);
			out.flush();
			out.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			FileInputStream file = new FileInputStream("D:\\files\\temp.txt");
			ObjectInputStream input = new ObjectInputStream(file);
			valueTwo = (int) input.readObject();
			input.close();
		} catch (IOException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		System.out.println(valueTwo);

	}
}
