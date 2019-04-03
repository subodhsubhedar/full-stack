
package com.myapp.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.myapp.controller.validation.UserValidator;
import com.myapp.library.entity.LibraryRole;
import com.myapp.library.entity.LibraryUser;
import com.myapp.library.exception.LibraryServiceException;
import com.myapp.library.menu.service.LibraryUserService;

@Controller
public class RegistrationController {
	@Autowired
	private LibraryUserService libraryUserService;

	@Autowired
	private UserValidator validator;

	@InitBinder("registrationForm")
	protected void initBinder(WebDataBinder binder) {
		binder.setValidator(validator);
	}

	@RequestMapping(value = "/register", method = RequestMethod.GET)
	public ModelAndView viewRegistrationPg(@ModelAttribute("registrationForm") LibraryUser registrationForm) {
		registrationForm = new LibraryUser(null, null, null);

		return new ModelAndView("registration-view");
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ModelAndView processRegistration(@ModelAttribute("registrationForm") @Validated LibraryUser registrationForm,
			BindingResult result, RedirectAttributes redirAttr) throws LibraryServiceException {
		ModelAndView mv = null;
		if (result.hasErrors()) {
			mv = new ModelAndView("registration-view");
			return mv;
		}

		// service call here
		libraryUserService.createUser(registrationForm);

		redirAttr.addAttribute("registered", true);

		mv = new ModelAndView();
		mv.setViewName("redirect:/login");

		return mv;
	}

	@ModelAttribute("availableRoleList")
	public List<LibraryRole> populateRoleList() throws LibraryServiceException {

		// service call
		Set<LibraryRole> libRoles = libraryUserService.findAllRoles();
		return new ArrayList<LibraryRole>(libRoles);
	}

}
