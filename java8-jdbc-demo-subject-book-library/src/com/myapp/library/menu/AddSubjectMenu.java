package com.myapp.library.menu;

import java.util.Random;
import java.util.Scanner;

import com.myapp.library.entity.Subject;
import com.myapp.library.menu.service.LibraryService;
import com.myapp.library.menu.service.LibraryServiceImpl;

/**
 * 
 * @author Admin
 *
 */
public class AddSubjectMenu {

	private LibraryService catalogueService;

	public AddSubjectMenu() {
		catalogueService = new LibraryServiceImpl();
	}

	public void menu(Scanner scanInput) {
		System.out.println("You have selected to add a new Subject ... ");
		scanInput.nextLine();

		System.out.println("Please provide a Subject title:");
		String title = scanInput.nextLine();

		System.out.println("Please provide Subject duration in hrs:");
		int duration = scanInput.nextInt();

		Random random = new Random();

		Subject subject = new Subject(random.nextLong(), title, duration, null);

		addSubject(subject);
	}

	/**
	 * 
	 * @param book
	 */
	public void addSubject(Subject subject) {

		Subject addedSubject = null;
		try {
			addedSubject = catalogueService.createSubject(subject);
			System.out.println("New Subject added Sucessfully ..");
		} catch (Exception e) {

			e.printStackTrace();
		}

		System.out.println(addedSubject);
	}

}
