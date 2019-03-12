package com.myapp.library.client;

import com.myapp.library.exception.LibraryServiceException;
import com.myapp.library.menu.LibraryMenuController;

public class LibraryClient {

	public static void main(String[] args) {

		System.out.println("############## Library Catalogue App Started #############");

		LibraryMenuController controller = new LibraryMenuController();
		try {
			controller.showMenu();
		} catch (LibraryServiceException e) {

			e.printStackTrace();
		}

		System.out.println("############## Library Catalogue App Closing down #############");

	}
}
