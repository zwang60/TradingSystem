package com.mercury.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.mercury.beans.User;
import com.mercury.beans.UserInfo;
import com.mercury.service.MainService;

@Controller
@SessionAttributes
public class MainController {
	@Autowired
	private MainService ms;
	
	public MainService getMs() {
		return ms;
	}
	public void setMs(MainService ms) {
		this.ms = ms;
	}
	
	@RequestMapping(value="/next", method=RequestMethod.POST)
	public ModelAndView process(@ModelAttribute("user") 
			User user, BindingResult result) {
		UserInfo userInfo = ms.process(user);
		ModelAndView mav = new ModelAndView();
		mav.setViewName("next");
		mav.addObject("userInfo", userInfo);
		return mav;
	}
	
	@RequestMapping("/main")
	public String mainPage() {		
		return "main";
	}
}
