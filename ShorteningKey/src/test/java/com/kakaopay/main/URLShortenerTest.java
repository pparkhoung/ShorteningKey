package com.kakaopay.main;


import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import java.util.HashMap;
import java.util.Random;

import org.junit.Before;
import org.junit.Test;

/*
 * URL Shortener
 */
public class URLShortenerTest {
	private HashMap<String, String> keyMap; // key-originalUrl map
	private HashMap<String, String> valueMap;// originalUrl-key map 
												
	private String domain; // Use this attribute to generate urls for a custom
							// domain name defaults to kakao.pay/
	private char arrChars[]; // 숫자, 영문 대소문자 담는 arry 변수
	private Random myRand; // Random object generate random integers
	private int keyLength; //  key length 8
	private int length =8;
	private String originalUrl="www.kakaopay.com";
	private String key = "";
	private String value = "";
	// Default Constructor
	@Before
	public void URLShortenerTestInit() throws Exception{
		keyMap = new HashMap<String, String>();
		valueMap = new HashMap<String, String>();
		myRand = new Random();
		keyLength = 8;
		arrChars = new char[62];
		for (int i = 0; i < 62; i++) {
			int j = 0;
			if (i < 10) {
				j = i + 48;
			} else if (i > 9 && i <= 35) {
				j = i + 55;
			} else {
				j = i + 61;
			}
			arrChars[i] = (char) j;
		}
		domain = "kakao.pay/";
	}
	
	


	@Test
	public void shortenURL() {
		String shortUrl = "";
	
		if (valueMap.containsKey(originalUrl)) {
			shortUrl = domain + "/" + valueMap.get(originalUrl);
		}
		assertThat(shortUrl, is(not(nullValue())));//
	}


	
	@Test
	public void cleanXSS() { 
		String oUrl=""; 
		oUrl = originalUrl.replaceAll("<", "&lt;").replaceAll(">", "&gt;");
		oUrl = oUrl.replaceAll("\\(", "&#40;").replaceAll("\\)", "&#41;");
		oUrl = oUrl.replaceAll("'", "& #39;");
		oUrl = oUrl.replaceAll("eval\\((.*)\\)", "");
		oUrl = oUrl.replaceAll("[\\\"\\\'][\\s]*javascript:(.*)[\\\"\\\']","\"\"");
		oUrl = oUrl.replaceAll("script", "");
		assertEquals(oUrl,originalUrl); 

	}
	
	@Test
	public void checkUrl() {
		
		if("kakao.pay/".equals(originalUrl.substring(0,10))) {
			if (keyMap.containsKey(originalUrl.substring(10, originalUrl.length()))) {
				value = keyMap.get(originalUrl.substring(10, originalUrl.length()));
			} 
		}
		assertThat(value, is(not(nullValue())));//
	}
	
	@Test
	public void getKey() {
	//key = generateKey();
		keyMap.put(key, originalUrl);
		valueMap.put(originalUrl, key);
		assertThat(keyMap, is(not(nullValue())));
		assertThat(valueMap, is(not(nullValue())));

	}

	// Key 생성
	@Test
	public void generateKey() {
		
		boolean flag = true;

		assertThat(flag, is(true));
		while (flag) {
			key = "";
			for (int i = 0; i <= keyLength; i++) {
				key += arrChars[myRand.nextInt(62)];
			}
			if (!keyMap.containsKey(key)) {
				flag = false;
				assertThat(flag, is(false));
			}
		}
		
		
	}
	
}