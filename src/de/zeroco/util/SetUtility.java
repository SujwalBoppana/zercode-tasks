package de.zeroco.util;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.TreeSet;

public class SetUtility {
	/**
	 * this method will add the elements present in two input sets and gives one set as output
	 * @author sujwal b
	 * @since 2022-12-12
	 * @param inputOne
	 * @param inputTwo
	 * @return set
	 */
	public static Set<String> combineData(Set<String>inputOne , Set<String>inputTwo) {
		if (inputOne.isEmpty()&&inputTwo.isEmpty()) {
			return null;
		}
		Set<String> temp = new HashSet<>();
		temp.addAll(inputOne);
		temp.addAll(inputTwo);
		return temp;
	}
	
	/**
	 * this method will gives a common data present in given input sets
	 * @author sujwal b
	 * @since 2022-12-12
	 * @param inputOne
	 * @param inputTwo
	 * @return set
	 */
	public static Set<String> commanData(Set<String>inputOne , Set<String>inputTwo) {
		if (inputOne.isEmpty()&&inputTwo.isEmpty()) {
			return null;
		}
		Set<String> temp = new HashSet<>();
		temp.addAll(inputOne);
		temp.retainAll(inputTwo);
		return temp;
	}
	
	/**
	 * This method will removes duplicates present in the given inputs and returns set 
	 * @author sujwal b
	 * @since 2022-12-12
	 * @param inputOne
	 * @param inputTwo
	 * @return set
	 */
	public static Set<String> removeDuplicates(Set<String>inputOne , Set<String>inputTwo) {
		if (inputOne.isEmpty()&&inputTwo.isEmpty()) {
			return null;
		}
		Set<String> temp = new HashSet<>();
		temp.addAll(inputOne);
		temp.removeAll(inputTwo);
		return temp;
	}
	
	/**
	 * this method will search the element present inside the set
	 * @author sujwal b
	 * @since 2022-12-12
	 * @param inputOne
	 * @param element
	 * @return true or false
	 */
	public static boolean searchElement(Set<String>input,String element) {
		if (input.isEmpty()||Instance.isBlank(element)) {
			return false;
		}
		return input.contains(element);
	}
	
	/**
	 * this method will search the all elements present inside the given inputsetone
	 * @author sujwal b
	 * @since 2022-12-12 
	 * @param inputOne
	 * @param inputTwo
	 * @return true or false
	 */
	public static boolean containsAllElements(Set<String>inputOne , Set<String>inputTwo) {
		return inputOne.containsAll(inputTwo);
	}
	
	
	
	public static void main(String[] args) {
		
		String[] rollNumber = { "18NH1A0301", "18NH1A0302", "18NH1A0303", "18NH1A0304", "18NH1A0305" };
		String[] rollNumberOne = { "18NH1A0303", "18NH1A0307", "18NH1A0308", "18NH1A0301", "18NH1A0310", "18NH1A0309",
				"18NH1A0306" };
		Set<String> set = new HashSet<>(Arrays.asList(rollNumber));
		Set<String> setOne = new HashSet<>(Arrays.asList(rollNumberOne));
		System.out.println(combineData(set,setOne));
		System.out.println(removeDuplicates(set, setOne));
		System.out.println(commanData(set, setOne));
		System.out.println(combineData(set, setOne).containsAll(setOne));
		System.out.println(searchElement(set,"18NH1A0302"));
		
		
//		set.addAll(Arrays.asList(rollNumberOne)); //this will adds the elements present inside the list
//		System.out.println(set);
//		set.retainAll(Arrays.asList(rollNumberOne)); // this method will keep the common elements present inside the two lists
//		System.out.println(set);
		set.removeAll(Arrays.asList(rollNumberOne)); // this method will reemoves the common elements
		System.out.println(set);
		set.add("null");
		System.out.println(set);

		Set<String> phNo = new HashSet<>();
		phNo.add("6309638199");
		phNo.add("7888745145");
		phNo.add("7842415544");
		phNo.add("7875441585");
		phNo.add("6309638199");
		System.out.println(phNo.remove("6309638199"));
		phNo.add("6309638199");
		System.out.println(phNo.contains("7842415544"));
		System.out.println();
		System.out.println(phNo);
		Set<Integer> data = new HashSet<Integer>();
		data.add(31);
		data.add(21);
		data.add(41);
		data.add(91);
		data.add(71);
		data.add(81);
		data.add(31);
		System.out.println(data);
		Iterator<Integer>iterator = data.iterator();
		while(iterator.hasNext()) {
			System.out.println(iterator.next());
		}
		
		 Set<String> states = new LinkedHashSet<String>();
	        states.add("India");
	        states.add("Australia");
	        states.add("South Africa");
	        states.add("India");
	        System.out.println(states);
	        
	        Set<String> colorsSet = new HashSet<String>(); 
	        colorsSet.add("Red"); 
	        colorsSet.add("Green"); 
	        colorsSet.add("Blue"); 
	        colorsSet.add("Cyan"); 
	        colorsSet.add("Magenta"); 
	        System.out.println(colorsSet); 
	        Set<String> treeSet = new TreeSet<String>(colorsSet); 
	        System.out.println(treeSet);
	        
	        Set<Object> objectSet = new TreeSet<Object>();
	        objectSet.add(7);
	        objectSet.add(5);
	        objectSet.add(3);
	        objectSet.add(1);
	        System.out.println(objectSet);
	        
	        TreeSet<String> treeSetOne=new TreeSet<String>();  
	         treeSetOne.add("A");  
	         treeSetOne.add("B");  
	         treeSetOne.add("C");  
	         treeSetOne.add("D");  
	         treeSetOne.add("E");  
	         System.out.println("Initial Set: "+treeSetOne);  
	           
	         System.out.println("Reverse Set: "+treeSetOne.descendingSet());  
	           
	         System.out.println("Head Set: "+treeSetOne.headSet("C", true));  
	          
	         System.out.println("SubSet: "+treeSetOne.subSet("A", true, "E", true));  
	           
	         System.out.println("TailSet: "+treeSetOne.tailSet("C", true));  
	         
	        
		

	}
}
