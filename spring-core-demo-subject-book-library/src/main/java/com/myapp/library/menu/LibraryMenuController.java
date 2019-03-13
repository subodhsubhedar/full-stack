package com.myapp.library.menu;

import java.util.Map;
import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.myapp.library.exception.LibraryServiceException;

/**
 * 
 * @author Admin
 *
 */
@Component
public class LibraryMenuController {

	@Autowired
	private AddSubjectMenu addSubjectMenu;

	@Autowired
	private AddBookMenu addBookMenu;

	@Autowired
	private DeleteSubjectMenu deleteSubjectMenu;

	@Autowired
	private DeleteBookMenu deleteBookMenu;

	@Autowired
	private SearchSubjectMenu searchSubjectMenu;

	@Autowired
	private SearchBookMenu searchBookMenu;

	/**
	 * @throws LibraryServiceException
	 * 
	 */
	public void showMenu() throws LibraryServiceException {

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
				addSubjectMenu.menu(scanInput);

				break;
			}
			case 2: {
				addBookMenu.menu(scanInput);

				break;
			}
			case 3: {
				deleteSubjectMenu.menu(scanInput);

				break;
			}
			case 4: {
				deleteBookMenu.menu(scanInput);

				break;
			}
			case 5: {
				searchSubjectMenu.menu(scanInput);

				break;
			}
			case 6: {
				searchBookMenu.menu(scanInput);
				break;

			}
			case 7: {
				System.out.println("You have selected to list all available Books ... ");
				searchBookMenu.findAllBooks();

				break;
			}
			case 8: {
				System.out.println("You have selected to list all available Subjects ... ");
				searchSubjectMenu.findAllSubjects();

				break;
			}
			case 9: {
				System.out.println("You have selected to list all available Books Sorted by Title... ");
				searchBookMenu.findAllBooksSortByTitle();

				break;
			}
			case 10: {
				System.out.println("You have selected to list all available Subjects Sorted by Subject Title... ");
				searchSubjectMenu.findAllSubjectsSortBySubjTitle();

				break;
			}
			case 11: {
				System.out.println("You have selected to list all available Books Sorted by Published date ... ");
				searchBookMenu.findAllBooksSortByPublishedDt();

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