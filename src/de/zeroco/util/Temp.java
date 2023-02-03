package de.zeroco.util;

import java.util.Map;

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
		
	}
}
