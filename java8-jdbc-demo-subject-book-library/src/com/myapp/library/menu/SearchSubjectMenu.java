package com.myapp.library.menu;

import java.util.Scanner;
import java.util.Set;

import com.myapp.library.entity.Subject;
import com.myapp.library.exception.LibraryServiceException;
import com.myapp.library.menu.service.LibraryService;
import com.myapp.library.menu.service.LibraryServiceImpl;

/**
 * 
 * @author Admin
 *
 */
public class SearchSubjectMenu {
	private LibraryService catalogueService;

	public SearchSubjectMenu() {
		catalogueService = new LibraryServiceImpl();
	}

	public void menu(Scanner scanInput) throws LibraryServiceException {

		System.out.println("You have selected to search a Subject ... ");
		scanInput.nextLine();
		System.out.println("Please provide a title:");
		String title = scanInput.nextLine();

		searchSubject(title);

	}

	/**
	 * 
	 * @param bookTitle
	 * @throws LibraryServiceException
	 */
	public void searchSubject(String subjTitle) throws LibraryServiceException {
		Subject subject = catalogueService.getSubject(subjTitle);

		if (subject != null) {
			System.out.println("Subject retrieved : " + subject);
		} else {
			System.out.println("Subject not Found.");
		}
	}

	/**
	 * 
	 * 
	 * @throws LibraryServiceException
	 */
	public void findAllSubjects() throws LibraryServiceException {

		Set<Subject> subjectSet = catalogueService.findAllSubjects();

		if (subjectSet != null && !subjectSet.isEmpty()) {
			System.out.println("Total Subjects available : " + subjectSet.size());
			subjectSet.forEach(name -> {
				System.out.println(name.toString());
			});
		} else {
			System.out.println("No Subjects are available currently in the Library Catalogue.");
		}
	}

	/**
	 * 
	 * @throws LibraryServiceException
	 */
	public void findAllSubjectsSortBySubjTitle() throws LibraryServiceException {

		Set<Subject> subjectSet = catalogueService.findAllSubjectsSortByTitle();

		if (subjectSet != null && !subjectSet.isEmpty()) {
			System.out.println("Total Subjects available : " + subjectSet.size());

			System.out.println("\nSubjects Sorted by Subject Title :");
			subjectSet.forEach(name -> {
				System.out.println(name.toString());
			});
		} else {
			System.out.println("No Subjects are available currently in the Library Catalogue.");
		}
	}

}
