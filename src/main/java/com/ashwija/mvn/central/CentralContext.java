package com.ashwija.mvn.central;

import com.ashwija.mvn.common.AppConstants;
import com.ashwija.mvn.dao.Menu;

import java.util.Stack;

public class CentralContext {
    private static Stack<Menu> menuStack = new Stack<>();

    static {
        menuStack.push(AppConstants.getMainMenu());
    }

    public static void setCurrentMenu(Menu currentMenu) {
        menuStack.push(currentMenu);
    }

    public static void resetToMainMenu() {
        menuStack.clear();
        menuStack.push(AppConstants.getMainMenu());
    }

    public static void setPrevMenu() {
        if (!menuStack.isEmpty()) {
            menuStack.pop();
        }
    }

    public static Menu getCurrentMenu() {
        if (menuStack.isEmpty()) {
            return null;
        }
        return menuStack.peek();
    }

    public static Menu getPrevMenu() {
        if (menuStack.size() < 2) {
            return null; // No previous menu if stack has 0 or 1 item
        }
        // Create a temporary stack to peek at the second-to-top element
        Stack<Menu> tempStack = new Stack<>();
        tempStack.push(menuStack.pop()); // Remove current
        Menu prevMenu = menuStack.peek(); // Previous is now at top
        menuStack.push(tempStack.pop()); // Restore current
        return prevMenu;
    }


//    private static Menu currentMenu;
//    private static Menu prevMenu;
//
//    public static void setCurrentMenu(Menu currentMenu) {
//        prevMenu = CentralContext.currentMenu;
//        CentralContext.currentMenu = currentMenu;
//    }
//
//    public static void setPrevMenuMenu(Menu prevMenu) {
//        CentralContext.prevMenu = prevMenu;
//    }
//
//    public static Menu getCurrentMenu() {
//        return currentMenu;
//    }
//
//    public static Menu getPrevMenu() {
//        return prevMenu;
//    }
}
