package com.ashwija.mvn.dao;

import com.ashwija.mvn.central.CentralContext;

import java.util.List;
import java.util.Map;

public class NavigationMenu extends Menu {
    public NavigationMenu(String title, int padding, Map<Character, Menu> subMenu, List<String> inputLabel) {
        super(title, padding, subMenu, inputLabel);
    }

    public NavigationMenu(String title) {
        super(title);
    }

    @Override
    public void performAction(List<Object> inputList) {
        //set current menu to selected sub menu
        char menuChoice = inputList.get(0).toString().charAt(0);

        Menu currentMenu = CentralContext.getCurrentMenu();
        Menu subMenu = currentMenu.getSubMenuAt(menuChoice);
        if (subMenu != null) {
            CentralContext.setCurrentMenu(subMenu);
        }
    }
}
