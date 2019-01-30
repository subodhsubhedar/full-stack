package com.myapp.library.menu;

import java.util.Map;
import java.util.Scanner;

/**
 * 
 * @author Admin
 *
 */
public class LibraryMenuController {

	/**
	 * 
	 */
	public void showMenu() {

		while (true) {
			System.out.println("\n##################################\n");

			System.out.println("Main menu - please select one of the option below : ");
			Scanner scanInput = new Scanner(System.in);

			Map<Integer, String> mainMenuItems = MainMenu.getMenuItems();

			mainMenuItems.forEach((key, val) -> System.out.println(key + " : " + val));

			int userSelection = -1;

			if (scanInput.hasNextInt()) {
				userSelection = scanInput.nextInt();
			}

			switch (userSelection) {
			case 1: {
				AddSubjectMenu addSubjectMenu = new AddSubjectMenu();
				addSubjectMenu.menu(scanInput);

				break;
			}
			case 2: {
				AddBookMenu addBookMenu = new AddBookMenu();
				addBookMenu.menu(scanInput);

				break;
			}
			case 3: {
				DeleteSubjectMenu deleteSubjectMenu = new DeleteSubjectMenu();
				deleteSubjectMenu.menu(scanInput);

				break;
			}
			case 4: {

				DeleteBookMenu deleteBookMenu = new DeleteBookMenu();
				deleteBookMenu.menu(scanInput);

				break;
			}
			case 5: {

				SearchSubjectMenu searchSubjectMenu = new SearchSubjectMenu();
				searchSubjectMenu.menu(scanInput);

				break;
			}
			case 6: {
				SearchBookMenu searchBookMenu = new SearchBookMenu();
				searchBookMenu.menu(scanInput);
				break;

			}
			case 7: {
				System.out.println("You have selected to list all available Books ... ");
				SearchBookMenu searchBookMenu = new SearchBookMenu();
				searchBookMenu.findAllBooks();

				break;
			}
			case 8: {
				System.out.println("You have selected to list all available Subjects ... ");
				SearchSubjectMenu searchSubjectMenu = new SearchSubjectMenu();
				searchSubjectMenu.findAllSubjects();

				break;
			}
			case 0:
				System.out.println("You have chosen to exit....Bye !");
				System.out.println("############## Library Catalogue App Closing down #############");

				System.exit(0);
			default:
				System.out.println("Invalid input : " + userSelection + " Please enter again.");
				break;
			}

		}
	}

}