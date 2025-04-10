package com.ashwija.mvn.central;

import com.ashwija.mvn.common.AppConstants;
import com.ashwija.mvn.menu.Menu;
import com.ashwija.mvn.model.NotificationEntity;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class CentralContext {
    private static final Stack<Menu> menuStack = new Stack<>();
    public static boolean skipNextInput = false;
    private static String loggedInUserID;

    private static Map<Character, NotificationEntity> notificationEntityMap = new HashMap<>();

    public static Map<Character, NotificationEntity> getNotificationEntityMap() {
        return notificationEntityMap;
    }

    public static void setNotificationEntityMap(Map<Character, NotificationEntity> notificationEntityMap) {
        CentralContext.notificationEntityMap = notificationEntityMap;
    }

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

    public static void pushToCurrentMenuStack(Menu currentMenu) {
        menuStack.push(currentMenu);
    }

    public static void resetToRootMenu() {
        CentralContext.menuStack.clear();
        CentralContext.menuStack.push(CentralContext.getCurrentRootMenu());
    }

    public static void logOut() {
        CentralContext.setLoggedInUserID(null);
        CentralContext.menuStack.clear();
        CentralContext.menuStack.push(CentralContext.getCurrentRootMenu());
    }

    public static Menu getCurrentRootMenu() {
        return CentralContext.getLoggedInUserID() != null ? AppConstants.getSecureMenu() : AppConstants.getMainMenu();
    }

    public static void setPrevMenu() {
        if (!CentralContext.menuStack.isEmpty()) {
            CentralContext.menuStack.pop();
        }
    }

    public static void pushNextMenu(Menu menu) {
        menuStack.push(menu);
    }

    public static Menu peekCurrentMenuStack() {
        if (CentralContext.menuStack.isEmpty()) {
            return null;
        }
        return CentralContext.menuStack.peek();
    }

    public static Menu getPrevMenu() {
        if (menuStack.size() < 2) {
            return null; // No previous menu if stack has 0 or 1 item
        }
        // Create a temporary stack to peek at the second-to-top element
        Stack<Menu> tempStack = new Stack<>();
        tempStack.push(CentralContext.menuStack.pop()); // Remove current
        Menu prevMenu = CentralContext.menuStack.peek(); // Previous is now at top
        CentralContext.menuStack.push(tempStack.pop()); // Restore current
        return prevMenu;
    }
}
