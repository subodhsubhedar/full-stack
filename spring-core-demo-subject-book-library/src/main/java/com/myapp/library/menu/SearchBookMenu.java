package com.myapp.library.menu;

import java.util.Scanner;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.myapp.library.entity.Book;
import com.myapp.library.exception.LibraryServiceException;
import com.myapp.library.menu.service.LibraryService;

/**
 * 
 * @author Admin
 *
 */
@Component
public class SearchBookMenu {

	@Autowired
	private LibraryService catalogueService;

	public void menu(Scanner scanInput) throws LibraryServiceException {

		System.out.println("\nYou have selected to search a Book ... ");
		scanInput.nextLine();
		System.out.println("\nPlease provide a title:");
		String title = scanInput.nextLine();

		System.out.println("TITLE : " + title);

		searchBook(title);

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
	public void findAllBooks() throws LibraryServiceException {

		Set<Book> bookSet = catalogueService.findAllBooks();
		if (bookSet != null && !bookSet.isEmpty()) {
			System.out.println("\nTotal Books available : " + bookSet.size());
			bookSet.forEach(name -> {
				System.out.println(name.toString());
			});
		} else {
			System.out.println("\nNo Books are available currently in the Catalogue.");
		}

	}

	/**
	 * 
	 * @throws LibraryServiceException
	 */
	public void findAllBooksSortByTitle() throws LibraryServiceException {

		Set<Book> bookSet = catalogueService.findAllBooksSortByTitle();
		if (bookSet != null && !bookSet.isEmpty()) {
			System.out.println("\nTotal Books available : " + bookSet.size());

			System.out.println("\nBooks Sorted by Title :");
			bookSet.forEach(name -> {
				System.out.println(name.toString());
			});
		} else {
			System.out.println("\nNo Books are available currently in the Catalogue.");
		}

	}

	/**
	 * 
	 * @throws LibraryServiceException
	 */
	public void findAllBooksSortByPublishedDt() throws LibraryServiceException {

		Set<Book> bookSet = catalogueService.findAllBooksSortByPublishdDt();
		if (bookSet != null && !bookSet.isEmpty()) {
			System.out.println("Total Books available : " + bookSet.size());

			System.out.println("\nBooks Sorted by Published dt :");
			bookSet.forEach(name -> {
				System.out.println(name.toString());
			});
		} else {
			System.out.println("No Books are available currently in the Catalogue.");
		}

	}

}
