package com.ashwija.mvn.central;

import com.ashwija.mvn.common.AppConstants;
import com.ashwija.mvn.dao.Menu;

import java.util.Stack;

public class CentralContext {
    private static Stack<Menu> menuStack = new Stack<>();
    private static String loggedInUserID;

    public static String getLoggedInUserID() {
        return CentralContext.loggedInUserID;
    }

    public static void setLoggedInUserID(String loggedInUserID) {
        CentralContext.loggedInUserID = loggedInUserID;
    }


    //main tain menu stack

    //go back to root
    //root can be Login Menu/Secure-Menu depending on the current value of user profile

    static {
        CentralContext.menuStack.push(AppConstants.getMainMenu());
    }

    public static void setCurrentMenu(Menu currentMenu) {
        menuStack.push(currentMenu);
    }

    public static void resetToMainMenu() {
        this.menuStack.clear();
        menuStack.push(AppConstants.getMainMenu());
    }

    public static void setPrevMenu() {
        if (!menuStack.isEmpty()) {
            menuStack.pop();
        }
    }

    public static void setNextMenu(Menu menu) {
        menuStack.push(menu);
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
}
