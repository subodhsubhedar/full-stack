package com.myapp.library.menu;

import java.io.IOException;
import java.util.Scanner;

import com.myapp.library.entity.Book;
import com.myapp.library.menu.service.LibraryService;
import com.myapp.library.menu.service.LibraryServiceImpl;

/**
 * 
 * @author Admin
 *
 */
public class DeleteBookMenu {
	private LibraryService catalogueService;

	public DeleteBookMenu() {
		catalogueService = new LibraryServiceImpl();
	}

	/**
	 * 
	 * @param scanInput
	 */
	public void menu(Scanner scanInput) {

		System.out.println("You have selected to delete a Book ... ");
		scanInput.nextLine();
		System.out.println("Please provide a title:");
		String title = scanInput.nextLine();

		Book book = searchBook(title);

		if (book != null) {
			System.out.println("Book found : " + book);
			System.out.println("Are you sure you want to delete, press Y to confirm, N to cancel ?");
			String confirmation = scanInput.nextLine();

			if ("Y".equalsIgnoreCase(confirmation)) {
				deleteBook(title);
			}

		} else {
			System.out.println("Could not find the book to be deleted.");
		}
	}

	/**
	 * 
	 * @param bookTitle
	 */
	public Book searchBook(String bookTitle) {
		Book book = null;
		try {
			book = catalogueService.getBook(bookTitle);
		} catch (ClassNotFoundException e) {

			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		}
		return book;
	}

	/**
	 * 
	 * @param bookTitle
	 */
	public void deleteBook(String bookTitle) {

		try {
			catalogueService.deleteBook(bookTitle);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		System.out.println("Book with title :" + bookTitle + " deleted successfully.");
	}

}
