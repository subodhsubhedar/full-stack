package com.myapp.library.menu;

import java.util.Scanner;

import com.myapp.library.entity.Subject;
import com.myapp.library.exception.LibraryServiceException;
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
	 * @throws LibraryServiceException
	 */
	public void menu(Scanner scanInput) throws LibraryServiceException {

		System.out.println("\nYou have selected to delete a Subject ... ");
		scanInput.nextLine();
		System.out.println("\nPlease provide a Subject title:");
		String title = scanInput.nextLine();

		Subject subject = searchSubject(title);

		if (subject != null) {
			System.out.println("\nSubject found : " + subject);

			if (subject.getReferences() == null || subject.getReferences().isEmpty()) {

				System.out.println("\nAre you sure you want to delete, press Y to confirm, N to cancel ?");
				String confirmation = scanInput.nextLine();

				if ("Y".equalsIgnoreCase(confirmation)) {
					deleteSubject(title);
				}
			} else {
				System.out.println("\nCannot delete the Subject as there are one or more Books associated with it.");
			}

		} else {
			System.out.println("\nCould not find the Subject to be deleted.");
		}
	}

	/**
	 * 
	 * @param bookTitle
	 * @throws LibraryServiceException
	 */
	public Subject searchSubject(String subTitle) throws LibraryServiceException {
		return catalogueService.getSubject(subTitle);
	}

	/**
	 * 
	 * @param bookTitle
	 * @throws LibraryServiceException
	 */
	public void deleteSubject(String subTitle) throws LibraryServiceException {

		catalogueService.deleteSubject(subTitle);

		System.out.println("\nSubject with title :" + subTitle + " deleted successfully.");
	}

}
