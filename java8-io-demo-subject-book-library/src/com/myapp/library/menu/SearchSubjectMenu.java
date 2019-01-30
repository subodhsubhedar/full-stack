package com.myapp.library.menu;

import java.io.IOException;
import java.util.Scanner;
import java.util.Set;

import com.myapp.library.entity.Subject;
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

	public void menu(Scanner scanInput) {

		System.out.println("You have selected to search a Subject ... ");
		scanInput.nextLine();
		System.out.println("Please provide a title:");
		String title = scanInput.nextLine();

		searchSubject(title);

	}

	/**
	 * 
	 * @param bookTitle
	 */
	public void searchSubject(String subjTitle) {
		Subject subject= null;
		try {
			subject = catalogueService.getSubject(subjTitle);
		} catch (ClassNotFoundException e) {

			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		}

		if (subject != null) {
			System.out.println("Subject retrieved : " + subject);
		} else {
			System.out.println("Subject not Found.");
		}
	}

	/**
	 * 
	 * @param bookTitle
	 */
	public void findAllSubjects() {
		
		Set<Subject> subjectSet = null;
		try {
			subjectSet = catalogueService.findAllSubjects();
		} catch (ClassNotFoundException e) {

			e.printStackTrace();
		} catch (IOException e) {

			e.printStackTrace();
		}
		if (subjectSet != null && !subjectSet.isEmpty()) {
			System.out.println("Total Subjects available : " + subjectSet.size());
			subjectSet.forEach(name -> {
				System.out.println(name.toString());
			});
		}else {
			System.out.println("No Subjects are available currently in the Library Catalogue.");
		}
	}

}
