package com.sad.yardmanagementsystem.service;

import java.util.UUID;

public class RandomStringGenerator {
	
	public static String generateString(String numero) {
		String u= UUID.randomUUID().toString();
		return numero+""+u;
	}

}
