package com.myapp.controller;

import java.util.HashSet;
import java.util.Set;

import javax.servlet.http.HttpSession;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class LoginController {

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView defaultFlow() {
		return new ModelAndView("redirect:/login");
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ModelAndView login(@RequestParam(value = "error", required = false) String error,
			@RequestParam(value = "logout", required = false) String logout,
			@RequestParam(value = "registered", required = false) boolean registered) {

		System.out.println("login method for /doLogin");

		String errorMessge = null;
		String logoutMsg = null;
		String registerMsg = null;
		ModelAndView mv = new ModelAndView("login-view");

		if (error != null) {
			errorMessge = "Username or Password is incorrect !!";
			mv.addObject("errorMessge", errorMessge);
		}
		if (logout != null) {
			logoutMsg = "You have been successfully logged out of Library !  Thanks for visiting.";
			mv.addObject("logoutMsg", logoutMsg);
		}
		if (registered) {
			registerMsg = "You have been successfully registered ! Please login with your credentials.";
			mv.addObject("registerMsg", registerMsg);
		}

		return mv;
	}

	@RequestMapping(value = "/processLogin", method = RequestMethod.POST)
	public ModelAndView postLogin(Model model, HttpSession session, RedirectAttributes redirAttr) {

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		UserDetails userDetails = (UserDetails) authentication.getPrincipal();

		String loggedInUser = userDetails.getUsername();
		Set<String> role = new HashSet<String>();

		userDetails.getAuthorities().forEach((auth) -> role.add(auth.getAuthority()));

		System.out.println("loggedInUser : " + loggedInUser);
		System.out.println("loggedInUser Roles : " + role);
		System.out.println("User has authorities: " + userDetails.getAuthorities());

		String modelAndView = "welcome";
		redirAttr.addFlashAttribute("loggedInUser", loggedInUser);
		redirAttr.addFlashAttribute("loggedInUserRoles", role);

		ModelAndView mv = new ModelAndView();
		mv.setViewName("redirect:" + modelAndView);

		return mv;

	}

}
