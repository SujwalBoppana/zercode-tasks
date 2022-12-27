package de.zeroco.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Temp {


	public static String getFormat(String stateName) {
		stateName = (stateName.trim()).toLowerCase();
		stateName = stateName.replaceAll("[^a-zA-Z0-9]+", "_");
		return stateName.endsWith("_") ? stateName.substring(0, stateName.length() - 1) : stateName;
	}

	public static String obj(Object input) {
		if (input instanceof Map<?, ?>) {
			if (((Map<?, ?>) input).size() == 0)
				return "posssible";
		}
		return "Not possible";
	}

	public static void main(String[] args) {
		Map<String, Double> groceryOne = new TreeMap<String, Double>();
		System.out.println(Utility.isBlankWithVarArguments(null));
		System.out.println("helloo");
//		groceryOne.put("Tomatoes", 10.50);
//		groceryOne.put("Onions", 4.50);
//		groceryOne.put("Potatoes", 20.00);
//		groceryOne.put("Red chills", 1.00);
//		System.out.println(groceryOne);
		System.out.println(obj(groceryOne));
	}
}
