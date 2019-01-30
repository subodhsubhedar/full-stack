package com.myapp.library.client;

import com.myapp.library.menu.LibraryMenuController;

public class LibraryClient {

	public static void main(String[] args) {

		System.out.println("############## Library Catalogue App Started #############");

		LibraryMenuController controller = new LibraryMenuController();
		controller.showMenu();

		System.out.println("############## Library Catalogue App Closing down #############");

	}
}
