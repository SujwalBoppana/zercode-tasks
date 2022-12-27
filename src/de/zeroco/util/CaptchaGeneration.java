package de.zeroco.util;

public class CaptchaGeneration {
	public String getCaptcha() {
		return getCaptcha(5);
	}

	public String getCaptcha(int size) {
		String captcha = "";
		String letters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
		for (int i = 0; i < size; i++) {
			int temp = (int) (Math.random() * 52);
			captcha += letters.charAt(temp);
		}
		return captcha;
	}

	public String getCaptcha(int size, String type) {
		String one = "alphanumeric";
		String captcha = "";
		if (one.equalsIgnoreCase(type)) {
			String value = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
			for (int i = 0; i < size; i++) {
				int temp = (int) (Math.random() * 62); // it is used to generate random number
				captcha += value.charAt(temp);
			}
			return captcha;
		}
		return getCaptcha(size);
	}
}
