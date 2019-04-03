package com.myapp.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LogoutController {

	@RequestMapping(value = "/menu/logout", method = RequestMethod.GET)
	public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null) {

			UserDetails userDetails = null;

			if (userDetails instanceof UserDetails) {
				userDetails = (UserDetails) auth.getPrincipal();
				System.out.println("Logout controller ...Logging out user : " + userDetails.getUsername());
			}

			new SecurityContextLogoutHandler().logout(request, response, auth);
			request.getSession().invalidate();
		} else {
			System.out.println("Auth is null");
		}

		return "redirect:/login?logout=true";
	}
}
