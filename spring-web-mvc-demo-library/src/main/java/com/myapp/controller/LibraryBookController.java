package com.myapp.controller;

import java.beans.PropertyEditor;
import java.beans.PropertyEditorSupport;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

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

import com.myapp.controller.validation.BookValidator;
import com.myapp.library.entity.Book;
import com.myapp.library.entity.Subject;
import com.myapp.library.exception.LibraryServiceException;
import com.myapp.library.menu.service.LibraryService;

@Controller
@RequestMapping("/menu")
public class LibraryBookController {

	@Autowired
	private LibraryService catalogueService;

	@Autowired
	private BookValidator validator;

	@InitBinder("book")
	protected void initBinder(WebDataBinder binder) {
		binder.setValidator(validator);

		PropertyEditor editor = new PropertyEditorSupport() {
			@Override
			public void setAsText(String text) throws IllegalArgumentException {
				if (!text.trim().isEmpty()) {
					super.setValue(LocalDate.parse(text.trim(), DateTimeFormatter.ofPattern("yyyy-MM-dd")));
				}
			}

			@Override
			public String getAsText() {
				if (super.getValue() == null) {
					return null;
				}
				LocalDate value = (LocalDate) super.getValue();
				return value.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
			}
		};
		binder.registerCustomEditor(LocalDate.class, "publishDate", editor);

	}

	@RequestMapping(value = "/all-books", method = RequestMethod.GET)
	public ModelAndView listAllBooks(ModelMap map) {

		System.out.println("listAllBooks method...");
		ModelAndView mv = new ModelAndView("all-books-view");

		try {
			Set<Book> bookSet = this.findAllBooks();
			if (bookSet != null) {
				mv.addObject("allBooksList", bookSet);
			}

		} catch (LibraryServiceException e) {
			e.printStackTrace();
		}
		return mv;

	}

	@RequestMapping(value = "/search-book", method = RequestMethod.GET)
	public ModelAndView searchBook(@ModelAttribute("searchBookTitle") final String bookTitle) {

		ModelAndView mv = new ModelAndView("search-book-view");

		try {
			Book book = getBook(bookTitle);
			if (book != null) {
				mv.addObject("book", book);
			}

		} catch (LibraryServiceException e) {
			e.printStackTrace();
		}
		return mv;

	}

	@RequestMapping(value = "/delete-book", method = RequestMethod.GET)
	public ModelAndView deleteBook(@ModelAttribute("deleteBookTitle") final String bookTitle) {

		ModelAndView mv = new ModelAndView("delete-book-view");

		try {
			Book book = getBook(bookTitle);
			if (book != null) {
				mv.addObject("book", book);
			}

		} catch (LibraryServiceException e) {
			e.printStackTrace();
		}
		return mv;
	}

	@RequestMapping(value = "/delete-book", method = RequestMethod.POST)
	public ModelAndView processDeleteBook(@ModelAttribute("book") final Book book) {

		ModelAndView mv = new ModelAndView("delete-book-view");
		System.out.println("book title to be deleted : " + book.getTitle());
		try {
			catalogueService.deleteBook(book.getTitle());
			mv.addObject("deletedBookTitle", book.getTitle());

		} catch (LibraryServiceException e) {
			e.printStackTrace();
		}
		return mv;
	}

	/**
	 * 
	 * @param bookTitle
	 * @throws LibraryServiceException
	 */
	public Book getBook(String bookTitle) throws LibraryServiceException {
		Book book = catalogueService.getBook(bookTitle);

		if (book != null) {
			System.out.println("\nBook retrieved : " + book);
		} else {
			System.out.println("\nBook not Found.");
		}
		return book;
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

	@ModelAttribute("availableSubjectList")
	public List<Subject> populateSubjectList() {

		Set<Subject> allSubjects = null;

		try {
			allSubjects = catalogueService.findAllSubjects();
		} catch (LibraryServiceException e) {
			e.printStackTrace();
		}

		List<Subject> subjectList = new ArrayList<Subject>();

		if (allSubjects != null) {
			allSubjects.stream().forEach((subjct) -> subjectList.add(subjct));
		}

		return subjectList;
	}

	@RequestMapping(value = "/add-new-book", method = RequestMethod.GET)
	public ModelAndView addNewBook(@ModelAttribute("book") final Book book) {

		return new ModelAndView("add-book-view");
	}

	@RequestMapping(value = "/add-new-book", method = RequestMethod.POST)
	public ModelAndView createBook(@ModelAttribute("book") final @Validated Book book, BindingResult result,
			HttpServletRequest request) {

		ModelAndView mv = new ModelAndView("add-book-view");

		if (result.hasErrors()) {
			return mv;
		}

		System.out.println("Book  to be added: " + book);

		Random random = new Random();

		book.setBookId(random.nextLong());

		try {
			catalogueService.createBook(book);
		} catch (Exception e) {
			e.printStackTrace();
		}

		mv.addObject("addedBookTitle", book.getTitle());

		return mv;
	}

}
