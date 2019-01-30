package com.myapp.library.menu;

import java.io.IOException;
import java.util.Scanner;

import com.myapp.library.entity.Subject;
import com.myapp.library.menu.service.LibraryService;
import com.myapp.library.menu.service.LibraryServiceImpl;

/**
 * 
 * @author Admin
 *
 */
public class DeleteSubjectMenu {
	private LibraryService catalogueService;

	public DeleteSubjectMenu() {
		catalogueService = new LibraryServiceImpl();
	}

	/**
	 * 
	 * @param scanInput
	 */
	public void menu(Scanner scanInput) {

		System.out.println("You have selected to delete a Subject ... ");
		scanInput.nextLine();
		System.out.println("Please provide a Subject title:");
		String title = scanInput.nextLine();

		Subject subject = searchSubject(title);

		if (subject != null) {
			System.out.println("Subject found : " + subject);

			if (subject.getReferences() == null || subject.getReferences().isEmpty()) {

				System.out.println("Are you sure you want to delete, press Y to confirm, N to cancel ?");
				String confirmation = scanInput.nextLine();

				if ("Y".equalsIgnoreCase(confirmation)) {
					deleteSubject(title);
				}
			} else {
				System.out.println("Cannot delete the Subject as there are one or more Books associated with it.");
			}

		} else {
			System.out.println("Could not find the Subject to be deleted.");
		}
	}

	/**
	 * 
	 * @param bookTitle
	 */
	public Subject searchSubject(String subTitle) {
		Subject subject = null;
		try {
			subject = catalogueService.getSubject(subTitle);
		} catch (ClassNotFoundException e) {

			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		}
		return subject;
	}

	/**
	 * 
	 * @param bookTitle
	 */
	public void deleteSubject(String subTitle) {

		try {
			catalogueService.deleteSubject(subTitle);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		System.out.println("Subject with title :" + subTitle + " deleted successfully.");
	}

}
