package com.myapp.library.menu;

public class MainMenuModel {

	private int menuIndex;

	private String menuDesc;

	private String menuCriteria;

	public MainMenuModel(int menuIndex, String menuDesc) {
		super();
		this.menuIndex = menuIndex;
		this.menuDesc = menuDesc;
	}

	public MainMenuModel() {

	}

	@Override
	public String toString() {
		return "MainMenuModel [menuIndex=" + menuIndex + ", menuDesc=" + menuDesc +  ", menuCriteria=" + menuCriteria +"]";
	}

	public String getMenuCriteria() {
		return menuCriteria;
	}

	public void setMenuCriteria(String menuCriteria) {
		this.menuCriteria = menuCriteria;
	}

	public int getMenuIndex() {
		return menuIndex;
	}

	public void setMenuIndex(int menuIndex) {
		this.menuIndex = menuIndex;
	}

	public String getMenuDesc() {
		return menuDesc;
	}

	public void setMenuDesc(String menuDesc) {
		this.menuDesc = menuDesc;
	}

}
