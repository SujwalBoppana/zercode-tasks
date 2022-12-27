package de.zeroco.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ListUtility {
	/**
	 * this method will remove the duplicates present inside the input
	 * @author sujwal b
	 * @since 2022-12-08
	 * @param input
	 * @return newList
	 */
	public static ArrayList<Object> removeDuplicates(ArrayList<Object> input) {
		if (Instance.isBlank(input)) {
			return null;
		}
		ArrayList<Object> temp = new ArrayList<Object>();
		for (Object object : input) {
			if (!temp.contains(object)) {
				temp.add(object);
			}
		}
		return temp;
	}

	/**
	 * this method will takes arraylist as input and returns string array as output
	 * @author sujwal b
	 * @since 2022-12-08
	 * @param input
	 * @return array
	 */
	public static String[] getArray(ArrayList<String> input) {
		if (Instance.isBlank(input)) {
			return null;
		}
		return input.toArray(new String[input.size()]);
	}

	/**
	 * this method will takes Object array as input and returns list as output
	 * @author sujwal b
	 * @since 2022-12-08
	 * @param input
	 * @return array
	 */
	public static List<Object> getList(Object[] input) {
		if (Instance.isBlank(input)) {
			return null;
		}
		return Arrays.asList(input);
	}
	
	/**
	 * this method will sort the given input in ascending order
	 * @author sujwal B
	 * @since 2022-12-08
	 * @param input
	 * @return list
	 */
	public static List<String> getSort(List<String> input) {
		if (Instance.isBlank(input)) {
			return null;
		}
		Collections.sort(input);
		return input;
	}
	/**
	 * this methos will gives the count of dupicates present inside the list
	 * @author sujwal b
	 * @since 2022-12-08
	 * @param input
	 * @return
	 */
	public static int countDuplicates(ArrayList<Object> input) {
		if (Instance.isBlank(input)) {
			return 0;
		}
		return input.size()-removeDuplicates(input).size();
	}
	/**
	 * this method will gives the list of even number from 1 to given range
	 * @author sujwal b
	 * @since 2022-12-08
	 * @param range
	 * @return even numberlist
	 */
	public static List<Integer> getEvenNumber(int range) {
		List<Integer> list = new ArrayList<Integer>();
		for (int i = 1; i <= range; i++) {
			if (i % 2 == 0) {
				list.add(i);
			}
		}
		return list;
	}

	public static void main(String[] args) throws IOException {
		Object[] details = { "raju", "ravi", "sujwal", "venky", "raju", "raju", "raju", "raju" };
		ArrayList<Object> list = new ArrayList<Object>(Arrays.asList(details));
		System.out.println(removeDuplicates(list));
		List<Object> listOne = removeDuplicates(list);
		Collections.sort(listOne, Collections.reverseOrder());
		ArrayList<String> listTwo = new ArrayList<>(Arrays.asList("ravi", "ravi123@gmail.com"));
		Collections.sort(listTwo);
		String[] temp = listTwo.toArray(new String[listTwo.size()]);
		System.out.println(Arrays.toString(temp));
		Object[] input = { "raju", "ravi", "sujwal", "venky", "raju", "raju", "raju", "raju", 4, 45, 45 };
		System.out.println(getList(input));
		System.out.println(listTwo);
		System.out.println();
		System.out.println(getSort(listTwo));
		System.out.println(countDuplicates(list));
		System.out.println(getEvenNumber(100));
		List<Integer> even = getEvenNumber(100);
	//	Files.appendData("D://evennumber.csv", even.toString());
		

	}

}
