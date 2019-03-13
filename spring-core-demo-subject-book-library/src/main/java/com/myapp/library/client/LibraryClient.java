package com.myapp.library.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.myapp.library.exception.LibraryServiceException;
import com.myapp.library.menu.LibraryMenuController;

@Component
public class LibraryClient {

	@Autowired
	private LibraryMenuController controller;

	public void initClient() {

		System.out.println("############## Library Catalogue App Started #############");

		try {
			controller.showMenu();
		} catch (LibraryServiceException e) {

			e.printStackTrace();
		}

		System.out.println("############## Library Catalogue App Closing down #############");

	}
}
