package com.myapp.controller;

import java.util.Set;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

@Controller
@SessionAttributes({"loggedInUser","loggedInUserRoles"})
public class LibraryWelcomeController {

	@RequestMapping(value = "welcome")
	public ModelAndView welcomePage1(@ModelAttribute("loggedInUser") String loggedInUser,
			@ModelAttribute("loggedInUserRoles") Set<String> loggedInUserRoles
	) {

		ModelAndView mv = new ModelAndView("welcome-view");

		mv.addObject("loggedInUser", loggedInUser);
		mv.addObject("loggedInUserRoles", loggedInUserRoles);

		return mv;
	}
}
