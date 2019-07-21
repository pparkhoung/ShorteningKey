package com.kakaopay.main;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

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
public class MainController {
	
	private static final Logger logger = LoggerFactory.getLogger(MainController.class);
	URLShortener u = new URLShortener(8, "kakao.pay/");
	/**
	 * Simply selects the home view to render by returning its name.
	 */

	
	@RequestMapping(value = "/insertUrl", method = RequestMethod.GET)
	public String insertUrl(Locale locale, Model model) {//URL �엯�젰�솕硫� 
				
		return "insertUrl";
	}
	
	@RequestMapping(value = "/checkUrl", method = RequestMethod.POST)
	public String checkUrl(HttpServletRequest req ,Model model) throws Exception {//�엯�젰諛쏆� URL泥댄겕 �썑 �엯�젰 �씠�젰�씠 �엳�쑝硫� �씠�쟾 Key 議고쉶 異쒕젰 �뾾�쑝硫� �떊洹� Key �깮�꽦 , �깮�꽦�맂 Key 媛� �엯�젰 �떆 originalUrl 議고쉶 �썑 由щ떎�씠�젆�듃
		req.setCharacterEncoding("UTF-8");
		String url = req.getParameter("url").trim();	
		
		if(url != null) {
			url = u.cleanXSS(url);
		}
		String checkUrl = u.checkUrl(url);
		if(!checkUrl.equals("")) {
			return  "redirect:" + checkUrl;
		}
		

		System.out.println("URL:" + url + "\tShortUrl: "+ u.shortenURL(url) + "\tOriginalUrl: "+ u.originalUrl(u.shortenURL(url)));
		
		model.addAttribute("shortUrl", u.shortenURL(url) );
		model.addAttribute("originalUrl", u.originalUrl(u.shortenURL(url)) );

		return "insertUrl";
	}
	

	
}
