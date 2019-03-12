package com.myapp.library.menu;

import java.util.Scanner;

import com.myapp.library.entity.Book;
import com.myapp.library.exception.LibraryServiceException;
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
	 * @throws LibraryServiceException
	 */
	public void menu(Scanner scanInput) throws LibraryServiceException {

		System.out.println("\nYou have selected to delete a Book ... ");
		scanInput.nextLine();
		System.out.println("\nPlease provide a title:");
		String title = scanInput.nextLine();

		Book book = searchBook(title);

		if (book != null) {
			System.out.println("\nBook found : " + book);
			System.out.println("\nAre you sure you want to delete, press Y to confirm, N to cancel ?");
			String confirmation = scanInput.nextLine();

			if ("Y".equalsIgnoreCase(confirmation)) {
				deleteBook(title);
			}

		} else {
			System.out.println("\nCould not find the book to be deleted.");
		}
	}

	/**
	 * 
	 * @param bookTitle
	 * @throws LibraryServiceException
	 */
	public Book searchBook(String bookTitle) throws LibraryServiceException {
		Book book = null;
		book = catalogueService.getBook(bookTitle);
		return book;
	}

	/**
	 * 
	 * @param bookTitle
	 * @throws LibraryServiceException
	 */
	public void deleteBook(String bookTitle) throws LibraryServiceException {

		catalogueService.deleteBook(bookTitle);

		System.out.println("\nBook with title :" + bookTitle + " deleted successfully.");
	}

}
