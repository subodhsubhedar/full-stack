package com.myapp.menu;

import java.util.List;

public class MainMenuModel {

	private int menuIndex;

	private String menuDesc;

	private String menuCriteria;

	private List<String> allowedRoles;

	public MainMenuModel(int menuIndex, String menuDesc, List<String> allowdRoles) {
		super();
		this.menuIndex = menuIndex;
		this.menuDesc = menuDesc;
		this.allowedRoles = allowdRoles;
	}

	public MainMenuModel() {

	}

	@Override
	public String toString() {
		return "MainMenuModel [menuIndex=" + menuIndex + ", menuDesc=" + menuDesc + ", menuCriteria=" + menuCriteria
				+ "]";
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

	public List<String> getAllowedRoles() {
		return allowedRoles;
	}

	public void setAllowedRoles(List<String> allowedRoles) {
		this.allowedRoles = allowedRoles;
	}
}
