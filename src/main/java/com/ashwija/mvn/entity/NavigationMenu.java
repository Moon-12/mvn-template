package com.ashwija.mvn.entity;

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
        Object menuChoice = inputList.get(0);
        Menu currentMenu = CentralContext.getCurrentMenu();
        Menu subMenu = currentMenu.getSubMenuAt(menuChoice.toString().charAt(0));
        if (subMenu != null) {
            CentralContext.setCurrentMenu(subMenu);
        }
    }
}
