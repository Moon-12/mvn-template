package com.ashwija.mvn.entity;

import java.util.List;
import java.util.Map;

public class NavigationMenu extends Menu{
    public NavigationMenu(String title, int padding, Map<Character, Menu> subMenu, List<String> inputLabel) {
        super(title, padding, subMenu, inputLabel);
    }

    @Override
    public void performAction(List<Object> inputList) {
        //set current menu to selected sub menu
        currentMenu = currentMenu.getSubMenuAt(menuChoice);
    }
}
