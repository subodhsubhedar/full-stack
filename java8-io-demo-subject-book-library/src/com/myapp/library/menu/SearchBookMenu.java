package com.myapp.library.menu;

import java.io.IOException;
import java.util.Scanner;
import java.util.Set;

import com.myapp.library.entity.Book;
import com.myapp.library.menu.service.LibraryService;
import com.myapp.library.menu.service.LibraryServiceImpl;

/**
 * 
 * @author Admin
 *
 */
public class SearchBookMenu {
	private LibraryService catalogueService;

	public SearchBookMenu() {
		catalogueService = new LibraryServiceImpl();
	}

	public void menu(Scanner scanInput) {

		System.out.println("You have selected to search a Book ... ");
		scanInput.nextLine();
		System.out.println("Please provide a title:");
		String title = scanInput.nextLine();

		System.out.println("TITLE : " + title);

		searchBook(title);

	}

	/**
	 * 
	 * @param bookTitle
	 */
	public void searchBook(String bookTitle) {
		Book book = null;
		try {
			book = catalogueService.getBook(bookTitle);
		} catch (ClassNotFoundException e) {

			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		}

		if (book != null) {
			System.out.println("Book retrieved : " + book);
		} else {
			System.out.println("Book not Found.");
		}
	}

	/**
	 * 
	 * @param bookTitle
	 */
	public void findAllBooks() {
		
		Set<Book> bookSet = null;
		try {
			bookSet = catalogueService.findAllBooks();
		} catch (ClassNotFoundException e) {

			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		}
		if (bookSet != null && !bookSet.isEmpty()) {
			System.out.println("Total Books available : " + bookSet.size());
			bookSet.forEach(name -> {
				System.out.println(name.toString());
			});
		}else {
			System.out.println("No Books are available currently in the Catalogue.");
		}


	}

}
