package de.zeroco.testing;

import de.zeroco.util.Password;

public class PasswordTesting {
	public static void main(String[] args) {
		Password pass = new Password();
		System.out.println(pass.getValidate("Zeroco.de#abcd1"));
	}
}
