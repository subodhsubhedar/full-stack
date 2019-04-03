package com.myapp.menu;

import java.util.Arrays;
import java.util.List;

/**
 * 
 * @author Admin
 *
 */

public enum MainMenu {

	ADD_NEW_SUBJECT(1, "Add new Subject"),

	ADD_NEW_BOOK(2, "Add new Book"),

	DELETE_SUBJECT(3, "Delete Subject"),

	DELETE_BOOK(4, "Delete Book"),

	SEARCH_SUBJECT(5, "Search Subject"),

	SEARCH_BOOK(6, "Search Book"),

	LIST_ALL_BOOKS(7, "List all Books"), LIST_ALL_SUBJECTS(8, "List all Subjects"), QUIT(0, "Quit");

	private final int key;
	private final String value;

	private final List<String> authorizedRoles;

	MainMenu(int key, String value) {
		this.key = key;
		this.value = value;

		switch (key) {
		case 1: {
			this.authorizedRoles = Arrays.asList("ROLE_PRINCIPAL", "ROLE_ADMIN");
			break;
		}
		case 2: {
			this.authorizedRoles = Arrays.asList("ROLE_LIBRARIAN", "ROLE_ADMIN");
			break;
		}
		case 3: {
			this.authorizedRoles = Arrays.asList("ROLE_PRINCIPAL", "ROLE_ADMIN");
			break;
		}
		case 4: {
			this.authorizedRoles = Arrays.asList("ROLE_LIBRARIAN", "ROLE_ADMIN");
			break;
		}
		case 5: {
			this.authorizedRoles = Arrays.asList("ROLE_PRINCIPAL", "ROLE_ADMIN");
			break;
		}
		case 6: {
			this.authorizedRoles = Arrays.asList("ROLE_LIBRARIAN", "ROLE_ADMIN");
			break;
		}
		case 7: {
			this.authorizedRoles = Arrays.asList("ROLE_LIBRARIAN", "ROLE_ADMIN");
			break;
		}
		case 8: {

			this.authorizedRoles = Arrays.asList("ROLE_PRINCIPAL", "ROLE_ADMIN");
			break;
		}
		case 0: {

			this.authorizedRoles = Arrays.asList("ROLE_PRINCIPAL", "ROLE_ADMIN", "ROLE_LIBRARIAN");
			break;
		}

		default: {
			this.authorizedRoles = Arrays.asList("ROLE_ADMIN");
			break;
		}
		}

	}

	public int getKey() {
		return key;
	}

	public String getValue() {
		return value;
	}

	public List<String> getAuthorizedRoles() {
		return authorizedRoles;
	}

	@Override
	public String toString() {
		return "[key=" + key + " value=" + value + "]";
	}

}
