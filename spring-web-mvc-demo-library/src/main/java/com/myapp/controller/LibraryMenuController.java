package com.myapp.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.myapp.library.entity.Book;
import com.myapp.library.entity.Subject;
import com.myapp.library.exception.LibraryServiceException;
import com.myapp.library.menu.service.LibraryService;
import com.myapp.menu.MainMenu;
import com.myapp.menu.MainMenuModel;

@Controller
@RequestMapping("/menu")
public class LibraryMenuController {

	@Autowired
	private LibraryService catalogueService;

	@RequestMapping(value = "", method = RequestMethod.GET)
	public ModelAndView showMainMenu(ModelMap map) {

		ModelAndView mv = new ModelAndView("main-menu");

		List<MainMenuModel> menuModelList = populateMenu();

		map.addAttribute("mainMenuModel",
				new MainMenuModel(MainMenu.ADD_NEW_SUBJECT.getKey(), MainMenu.ADD_NEW_SUBJECT.getValue()));

		map.addAttribute("welcomeMsg", "Welcome ..");

		map.addAttribute("selectionMsg", "Please choose one of the option below");

		map.addAttribute("menuModelList", menuModelList);

		return mv;
	}

	// @ModelAttribute("menuModelList")
	public List<MainMenuModel> populateMenu() {
		List<MainMenuModel> menuModelList = new ArrayList<MainMenuModel>();

		for (MainMenu menu : MainMenu.values()) {
			MainMenuModel menuModel = new MainMenuModel(menu.getKey(), menu.getValue());
			menuModelList.add(menuModel);
		}

		return menuModelList;

	}

	@RequestMapping(value = "/show-main", method = RequestMethod.POST)
	public ModelAndView processMenuSelection(ModelMap map, @ModelAttribute("mainMenuModel") MainMenuModel model,
			BindingResult result, RedirectAttributes redirAttr) throws IOException {

		int selMenuModelIndex = model.getMenuIndex();

		System.out.println("selMenuModelIndex : " + selMenuModelIndex);

		String modelAndView;
		ModelAndView mv = new ModelAndView();

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
			modelAndView = "quit";
			break;
		}

		default: {
			modelAndView = "menu";
			break;
		}

		}
		mv.setViewName("redirect:" + modelAndView);
		return mv;
	}

	@RequestMapping(value = "/goBackToMainMenu", method = RequestMethod.GET)
	public ModelAndView goBack() {
		return new ModelAndView("redirect:/library/menu");
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
