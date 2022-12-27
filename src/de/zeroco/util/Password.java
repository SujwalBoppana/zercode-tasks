package de.zeroco.util;

import java.util.regex.Pattern;

public class Password {
	public boolean getValidate(String input) {
		return  Pattern.compile("^(?=.*[0-9])(?=.*[A-Z])(?=.*[!@#&$]).{8,15}$").matcher(input).find();
	}
}
