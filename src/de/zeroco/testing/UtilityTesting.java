package de.zeroco.testing;

import de.zeroco.util.Utility;

public class UtilityTesting {
	public static void main(String[] args) {
		System.out.println(Utility.getCaptcha(100,null));
		System.out.println(
				Utility.getTicket("Dear customer, Your ticket number is : {{HYDTS123456}}. Happy Journey", null, null));
		System.out.println(Utility.isValidPassword(null));
		System.out.println(Utility.hasData(""));
	}
}
