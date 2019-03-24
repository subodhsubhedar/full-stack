package com.myapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LibraryWelcomeController {

	@RequestMapping(value = "")
	public ModelAndView welcomePage(ModelMap map) {

		ModelAndView mv = new ModelAndView("default");

		return mv;
	}
}
