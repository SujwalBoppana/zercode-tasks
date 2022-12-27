package de.zeroco.util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Vector;

public class LinkedListUtility {
	/**
	 * this method will converts the arraylist to linkedlist
	 * @author sujwal b
	 * @since 2022-12-09
	 * @param input
	 * @return linkedlist
	 */
	public static List<String> getLinkedList(ArrayList<String> input) {
		return new LinkedList<String>(input);
	}

	/**
	 * this method will gives the Linked list of the given range
	 * @author sujwal B
	 * @since 2022-12-09
	 * @param range
	 * @return list
	 */
	public static List<Integer> getPrime(int range) {
		if (Instance.isBlank(range)) 
			return null;
		List<Integer> prime = new LinkedList<Integer>();
		for (int i = 1; i <= range; i++) {
			boolean temp = true;
			for (int j = 2; j < i; j++) {
				if (i % j == 0) {
					temp = false;
					break;
				}
			}
			if (temp) {
				prime.add(i);
			}
		}
		return prime;
	}
	/**
	 * this method will gives the count of each vowels present in the linkedlist
	 * @author sujwal B
	 * @since 2022-12-09
	 * @param input
	 * @return countofVowels
	 */
	public static List<Integer> getVowelsCount(LinkedList<String> input) {
		if (Instance.isBlank(input))
			return null;
		List<Integer> vowelsCount = new LinkedList<Integer>();
		String vowels = "aeiouAEIOU";

		for (String temp : input) {
			int count = 0;
			for (int i = 0; i < temp.length(); i++) {
				char value = temp.charAt(i);
				for (int j = 0; j < vowels.length(); j++) {
					if (value == vowels.charAt(j)) {
						count++;
					}
				}

			}
			vowelsCount.add(count);
		}
		
		return vowelsCount;	
	}

	public static void main(String[] args) {
		
		
		
		System.out.println(getPrime(100));
		List<Integer> prime = getPrime(100);
		List<Integer> primeOne = prime;
		List<Integer> primeTwo = primeOne;
		List<Integer> primeThree = new ArrayList<>();
		primeThree.addAll(primeTwo);
		primeThree.addAll(primeOne);
		System.out.println(primeThree);
		
		
		
		
		
		LinkedList<String> citys = new LinkedList<String>();
		citys.add("hyderbad");
		citys.add("aaaaa");
		citys.addFirst("vijayawada");
		citys.add("guntur");
		citys.addLast("Vizag");
		System.out.println(getVowelsCount(citys));
		
		System.out.println(citys.peek());
		System.out.println(citys.pollFirst());
		System.out.println(citys.pollLast());
		ArrayList<String> cityList = new ArrayList<>(citys);
		System.out.println(getLinkedList(cityList));
		Iterator<String> iterator = citys.descendingIterator();
		while (iterator.hasNext()) {
			System.out.println(iterator.next());
		}
		System.out.println(citys);
		Vector<String> vector = new Vector<String>();
		vector.add("78897");
		vector.add("7889");
		vector.add("897");
		vector.add("789");
		System.out.println(vector);
		Collections.sort(vector);
		System.out.println(vector);
		
	}

}
