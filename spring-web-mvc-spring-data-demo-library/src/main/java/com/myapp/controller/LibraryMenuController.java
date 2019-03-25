package com.myapp.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.myapp.controller.validation.MenuValidator;
import com.myapp.library.entity.Book;
import com.myapp.library.exception.LibraryServiceException;
import com.myapp.library.menu.service.LibraryService;
import com.myapp.menu.MainMenu;
import com.myapp.menu.MainMenuModel;

@Controller
@RequestMapping("/menu")
public class LibraryMenuController {

	@Autowired
	private LibraryService catalogueService;

	@Autowired
	private MenuValidator validator;

	@InitBinder("mainMenuModel")
	protected void initBinder(WebDataBinder binder) {
		binder.setValidator(validator);
	}

	@RequestMapping(value = "/show-main", method = RequestMethod.GET)
	public ModelAndView showMainMenu(ModelMap map, @ModelAttribute("mainMenuModel") MainMenuModel model,
			BindingResult result) {

		ModelAndView mv = buildMainMenuModelView();

		if (model == null || (model != null && ((model.getMenuDesc() == null) || (model.getMenuDesc().equals(""))))) {
			mv.addObject("mainMenuModel",
					new MainMenuModel(MainMenu.ADD_NEW_SUBJECT.getKey(), MainMenu.ADD_NEW_SUBJECT.getValue()));
		}

		return mv;
	}

	/**
	 * 
	 * @return
	 */
	protected ModelAndView buildMainMenuModelView() {
		ModelAndView mv = new ModelAndView("main-menu");

		List<MainMenuModel> menuModelList = populateMenu();

		mv.addObject("welcomeMsg", "Welcome ..");
		mv.addObject("selectionMsg", "Please choose one of the option below");
		mv.addObject("menuModelList", menuModelList);

		return mv;
	}

	public List<MainMenuModel> populateMenu() {
		List<MainMenuModel> menuModelList = new ArrayList<MainMenuModel>();

		for (MainMenu menu : MainMenu.values()) {
			MainMenuModel menuModel = new MainMenuModel(menu.getKey(), menu.getValue());
			menuModelList.add(menuModel);
		}

		return menuModelList;

	}

	@RequestMapping(value = "/process-main", method = RequestMethod.POST)
	public ModelAndView processMenuSelection(ModelMap map,
			@ModelAttribute("mainMenuModel") @Validated MainMenuModel model, BindingResult result,
			RedirectAttributes redirAttr) throws IOException {

		int selMenuModelIndex = model.getMenuIndex();

		System.out.println("selMenuModelIndex : " + selMenuModelIndex);

		for (MainMenu menu : MainMenu.values()) {
			if (menu.getKey() == selMenuModelIndex) {
				model.setMenuDesc(menu.getValue());
			}
		}

		String modelAndView;

		if (result.hasErrors()) {

			ModelAndView mv = buildMainMenuModelView();

			mv.addObject("mainMenuModel", model);
			mv.addObject("org.springframework.validation.BindingResult", result);

			return mv;
		}

		switch (selMenuModelIndex) {

		case 1: {
			modelAndView = "add-new-subject";
			break;
		}
		case 2: {
			modelAndView = "add-new-book";
			break;
		}
		case 3: {
			modelAndView = "delete-subject";
			redirAttr.addFlashAttribute("deleteSubjectTitle", model.getMenuCriteria());
			break;
		}
		case 4: {
			modelAndView = "delete-book";
			redirAttr.addFlashAttribute("deleteBookTitle", model.getMenuCriteria());
			break;
		}
		case 5: {
			modelAndView = "search-subject";
			redirAttr.addFlashAttribute("searchSubjectTitle", model.getMenuCriteria());
			break;
		}
		case 6: {
			modelAndView = "search-book";
			redirAttr.addFlashAttribute("searchBookTitle", model.getMenuCriteria());
			break;
		}
		case 7: {
			modelAndView = "all-books";
			break;
		}
		case 8: {
			modelAndView = "all-subjects";
			break;
		}
		case 9: {
			modelAndView = "search-subject-by-duration";
			break;
		}
		case 0: {
			modelAndView = "quit";
			break;
		}

		default: {
			modelAndView = "#";
			break;
		}

		}
		ModelAndView mv = new ModelAndView();
		mv.setViewName("redirect:" + modelAndView);
		return mv;
	}

	@RequestMapping(value = "/goBackToMainMenu", method = RequestMethod.GET)
	public ModelAndView goBack() {
		return new ModelAndView("redirect:/library/menu/show-main");
	}

	@RequestMapping(value = "/quit", method = RequestMethod.GET)
	public ModelAndView quit() {
		return new ModelAndView("redirect:/library/");
	}

	/**
	 * 
	 * @param bookTitle
	 * @throws LibraryServiceException
	 */
	public void searchBook(String bookTitle) throws LibraryServiceException {
		Book book = catalogueService.getBook(bookTitle);

		if (book != null) {
			System.out.println("\nBook retrieved : " + book);
		} else {
			System.out.println("\nBook not Found.");
		}
	}

	/**
	 * 
	 * @param bookTitle
	 * @throws LibraryServiceException
	 */
	public Set<Book> findAllBooks() throws LibraryServiceException {

		Set<Book> bookSet = catalogueService.findAllBooks();
		if (bookSet != null && !bookSet.isEmpty()) {
			System.out.println("\nTotal Books available : " + bookSet.size());
			bookSet.forEach(name -> {
				System.out.println(name.toString());
			});
		} else {
			System.out.println("\nNo Books are available currently in the Catalogue.");
		}

		return bookSet;
	}

}
