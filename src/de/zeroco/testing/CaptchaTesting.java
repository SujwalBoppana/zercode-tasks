package de.zeroco.testing;

import de.zeroco.util.CaptchaGeneration;

public class CaptchaTesting {
	public static void main(String[] args) {
		CaptchaGeneration captchaGeneration = new CaptchaGeneration();
		System.out.println(captchaGeneration.getCaptcha());
		System.out.println(captchaGeneration.getCaptcha(100, "Alphanumeric"));
	}

}
