package com.kakaopay.main;


import java.util.HashMap;
import java.util.Random;

/*
 * URL Shortener
 */
public class URLShortener {
	
	private HashMap<String, String> keyMap; // key-originalUrl map
	private HashMap<String, String> valueMap;// originalUrl-key map 
												
	private String domain; // Use this attribute to generate urls for a custom
							// domain name defaults to kakao.pay/
	private char arrChars[]; // 숫자, 영문 대소문자 담는 arry 변수
	private Random myRand; // Random object generate random integers
	private int keyLength; //  key length 8

	URLShortener() {
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


	URLShortener(int length, String newDomain) {
		this();
		this.keyLength = length;
		if (!newDomain.isEmpty()) {
			newDomain = sanitizeURL(newDomain);
			domain = newDomain;
		}
	}

	// URL 입력 시  valueMap 체크 유무에 따라 키값 조회 및 키 생성
	public String shortenURL(String originalUrl) {
		String shortUrl = "";
		
		originalUrl = sanitizeURL(originalUrl);
		if (valueMap.containsKey(originalUrl)) {
			shortUrl = domain + "/" + valueMap.get(originalUrl);
		} else {
			shortUrl = domain + "/" + getKey(originalUrl);
		}
		
		return shortUrl;
	}

	//domain + key 형태 URL 입력 시  keyMap 체크 후 originalUrl 추출
	public String originalUrl(String shortUrl) {
		String originalUrl = "";
		String key = shortUrl.substring(domain.length() + 1);
		originalUrl = keyMap.get(key);
		return originalUrl;
	}

	// sanitizeURL
	String sanitizeURL(String url) {
	//	if (url.substring(0, 7).equals("http://"))
	//		url = url.substring(7);

	//	if (url.substring(0, 8).equals("https://"))
	//		url = url.substring(8);

		if (url.charAt(url.length() - 1) == '/')
			url = url.substring(0, url.length() - 1);
		return url;
	}
	
	public String cleanXSS(String url) {  //입력된 URL XSS 체크
		url = url.replaceAll("<", "&lt;").replaceAll(">", "&gt;");
		url = url.replaceAll("\\(", "&#40;").replaceAll("\\)", "&#41;");
		url = url.replaceAll("'", "& #39;");
		url = url.replaceAll("eval\\((.*)\\)", "");
		url = url.replaceAll("[\\\"\\\'][\\s]*javascript:(.*)[\\\"\\\']","\"\"");
		url = url.replaceAll("script", "");
		return url;
	}

	public String checkUrl(String url) {//입력된 URL 체크 도메인 +key 인지 일반 URL 인지
		String value = "";
		url = sanitizeURL(url);
		if("kakao.pay/".equals(url.substring(0,10))) {
			if (keyMap.containsKey(url.substring(10, url.length()))) {
				value = keyMap.get(url.substring(10, url.length()));
			} 
		}
		
		return value;
	}
	
	//get Key method
	private String getKey(String originalUrl) {
		String key;
		key = generateKey();
		keyMap.put(key, originalUrl);
		valueMap.put(originalUrl, key);
		return key;
	}

	// Key 생성
	private String generateKey() {
		String key = "";
		boolean flag = true;
		while (flag) {
			key = "";
			for (int i = 0; i <= keyLength; i++) {
				key += arrChars[myRand.nextInt(62)];
			}
			if (!keyMap.containsKey(key)) {
				flag = false;
			}
		}
		return key;
	}
	
}