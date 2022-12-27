package de.zeroco.util;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

public class MapUtility {

	/**
	 * this method will converts the map into list and returns the values present
	 * inthe map
	 * @author sujwal B
	 * @since 2022-12-14
	 * @param input
	 * @return list
	 */
	public static List<Object> getValuesAsList(Map<?, ?> input) {
		if (Utility.isBlank(input)) {
			return null;
		}
		return new LinkedList<>(input.values());
	}

	/**
	 * this method will converts the map into the set and return the values present
	 * inthe map
	 * @author sujwal b
	 * @since 2022-12-14
	 * @param input
	 * @return set
	 */
	public static Set<Object> getValuesAsSet(Map<?, ?> input) {
		if (Utility.isBlank(input)) {
			return null;
		}
		return new TreeSet<>(input.values());
	}
	/**
	 * this method will gives all the key and values as a set
	 * @author sujwal b
	 * @since 2022-12-14
	 * @param input
	 * @return
	 */
	public static Set<?> toSet(Map<?, ?> input) {
		if (Utility.isBlank(input)) {
			return null;
		}
		return input.entrySet();	
	}
	/**
	 * this method will gives all the key and values as a list
	 * @author sujwal b
	 * @since 2022-12-14
	 * @param input
	 * @return list
	 */
	public static List<?> toList(Map<?, ?> input) {
		if (Utility.isBlank(input)) {
			return null;
		}
		return new LinkedList<>(input.entrySet());
	}
	


	public static void main(String[] args) {
		Map<Integer, String> studentMap = new HashMap<>();
		studentMap.put(301, "Raju");
		studentMap.put(302, "Vijay");
		studentMap.put(303, "Amit");
		studentMap.put(302, "Ajay");
		System.out.println(toSet(studentMap));
		System.out.println();
		System.out.println(getValuesAsList(studentMap));
		System.out.println(getValuesAsSet(studentMap));
		for (Map.Entry<Integer, String> map : studentMap.entrySet()) {
			System.out.println(map.getKey() + " " + map.getValue());
		}
		Map<String, Double> groceryItems = new HashMap<String, Double>();
		groceryItems.put("Tomatoes", 10.50);
		groceryItems.put("Onions", 4.50);
		groceryItems.put("Potatoes", 20.00);
		groceryItems.put("Red chills", 1.00);
		System.out.println(groceryItems);
		
		Map<String, Double> grocery = new LinkedHashMap<String, Double>();
		grocery.put("Tomatoes", 10.50);
		grocery.put("Onions", 4.50);
		grocery.put("Potatoes", 20.00);
		grocery.put("Red chills", 1.00);
		System.out.println(grocery);
		
		Map<String, Double> groceryOne = new TreeMap<String, Double>();
		groceryOne.put("Tomatoes", 10.50);
		groceryOne.put("Onions", 4.50);
		groceryOne.put("Potatoes", 20.00);
		groceryOne.put("Red chills", 1.00);
		System.out.println(groceryOne);


		Map<Integer, String> map = new HashMap<Integer, String>();
		map.put(100, "Amit");
		map.put(101, "Vijay");
		map.put(102, "Rahul");
		map.putIfAbsent(103, "Ravi");
		Map<Integer, Map<Integer, String>> mapOne = new TreeMap<>();
		mapOne.put(1, map);
		System.out.println("kkkk"+mapOne);
		System.out.println(mapOne.entrySet());
		
		Map<Integer, String> names = new TreeMap<Integer, String>();  
		names.put(110,"Ravi");  
		names.put(120,"Prateek");  
		names.put(130, "Davesh");    
		names.put(140, "Kamal");  
		names.put(150, "Pawan");  
		Iterator <Integer> it = names.keySet().iterator();       //keyset is a method  
		while(it.hasNext())  
		{  
		int key=(int)it.next();  
		System.out.println("Roll no.: "+key+"     name: "+names.get(key));  
		}  
		Map<String,String> states = new HashMap<String,String>();   
		states.put("Gujarat", "Gandhi Nagar");               
		states.put("Uttar Pradesh", "Lucknow");   
		states.put("Sikkim", "Ganagtok");   
		for (String State : states.keySet())   //using keyset() method for iteration over keySet  
		System.out.println("State: " + State);   
		for (String Capital : states.values())         //using values() for iteration over keys  
		System.out.println("Capiatl: " + Capital);  
		
		
		
	}
}
