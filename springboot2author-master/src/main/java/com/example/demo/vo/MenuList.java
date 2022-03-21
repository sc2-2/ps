package com.example.demo.vo;
/**
 * 主页菜单结构
 */
import java.util.List;

public class MenuList {
	private Menu menu;
	private List<Menu> submenus;

	public Menu getMenu() {
		return menu;
	}

	public void setMenu(Menu menu) {
		this.menu = menu;
	}

	public List<Menu> getSubmenus() {
		return submenus;
	}

	public void setSubmenus(List<Menu> submenus) {
		this.submenus = submenus;
	}

}
