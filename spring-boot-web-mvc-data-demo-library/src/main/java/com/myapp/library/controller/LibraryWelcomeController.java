package com.myapp.library.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

@Controller
@SessionAttributes("loggedInUser")
public class LibraryWelcomeController {

	@RequestMapping(value = "")
	public ModelAndView welcomePage(@ModelAttribute("loggedInUser") String loggedInUser) {

		ModelAndView mv = new ModelAndView("default");
		return mv;
	}
	


	@ModelAttribute("loggedInUser")
	public String getLoggedInUserName() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = (User) auth.getPrincipal();
		
		return user.getUsername();
	
	}
	
}
