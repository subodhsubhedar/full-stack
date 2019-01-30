package com.myapp.library.menu;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 
 * @author Admin
 *
 */
public class MainMenu {

	private static Map<Integer, String> mainMenuMap = new LinkedHashMap<Integer, String>();

	static {

		mainMenuMap.put(1, "Add new Subject");
		mainMenuMap.put(2, "Add new Book");
		mainMenuMap.put(3, "Delete Subject");
		mainMenuMap.put(4, "Delete Book");
		mainMenuMap.put(5, "Search Subject");
		mainMenuMap.put(6, "Search Book");
		mainMenuMap.put(7, "List all Books");
		mainMenuMap.put(8, "List all Subjects");
		mainMenuMap.put(0, "Quit");

	}

	public static Map<Integer, String> getMenuItems() {
		return mainMenuMap;
	}

}
