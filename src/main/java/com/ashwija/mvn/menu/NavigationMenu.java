package com.ashwija.mvn.menu;

import com.ashwija.mvn.central.CentralContext;

import java.util.List;
import java.util.Map;

public class NavigationMenu extends Menu {
    public NavigationMenu(String title, int padding, Map<Character, Menu> subMenu, List<String> inputLabel) {
        super(title, padding, subMenu, inputLabel);
    }

   
    @Override
    public void performAction(List<Object> inputList) {
        //set current menu to selected sub menu
        char menuChoice = inputList.get(0).toString().charAt(0);

        Menu currentMenu = CentralContext.peekCurrentMenuStack();
        Menu subMenu = currentMenu.getSubMenuAt(menuChoice);


        //if subMenu is NotificationOperationMenu then we need to store previous Input
        if (subMenu instanceof NotificationOperationMenu) {
            CentralContext.pushPreviousInputs(inputList);
        }


        if (subMenu != null) {
            CentralContext.pushToCurrentMenuStack(subMenu);
        }
    }
}
