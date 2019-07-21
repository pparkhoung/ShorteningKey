package com.kakaopay.main;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Handles requests for the application home page.
 */
@Controller
public class MainControllerTest {
	
	private static final Logger logger = LoggerFactory.getLogger(MainController.class);
	URLShortener u = new URLShortener(8, "kakao.pay/");
	/**
	 * Simply selects the home view to render by returning its name.
	 */

	
	@RequestMapping(value = "/insertUrl", method = RequestMethod.GET)
	public String insertUrl(Locale locale, Model model) {
				
		return "insertUrl";
	}
	
	@Test
	public void checkUrl()  {
		String url ="www.kakaopay.com ";
		
		assertThat(url, is(not(nullValue())));//url 값 유무
		assertThat(u, is(not(nullValue()))); //URLShortener 객체 유무
		assertThat(u.checkUrl(url), is(not(nullValue())));//url 이전 호출이력 유무
		assertEquals(url,u.originalUrl(u.shortenURL(url))); //입력된 url 과 키 및 URL 저장 후 originalUrl 체크


	}
	

	
}
