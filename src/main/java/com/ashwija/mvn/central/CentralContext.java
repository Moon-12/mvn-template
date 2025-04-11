package com.ashwija.mvn.central;

import com.ashwija.mvn.common.AppConstants;
import com.ashwija.mvn.menu.Menu;
import com.ashwija.mvn.model.NotificationEntity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Stack;

public class CentralContext {
    private static final Stack<Menu> menuStack = new Stack<>();
    public static boolean skipNextInput = false;
    private static String loggedInUserID;
    private static final Stack<List<Object>> previousInputs = new Stack<>();

    private static Map<Character, NotificationEntity> notificationEntityMap = new HashMap<>();

    public static NotificationEntity getNotificationEntityByKey(Character key) {
        return notificationEntityMap.get(key);
    }

    public static void putIntoNotificationEntityMap(Character key, NotificationEntity notificationEntityValue) {
        CentralContext.notificationEntityMap.put(key, notificationEntityValue);
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

    public static void pushPreviousInputs(List<Object> inputList) {
        previousInputs.push(inputList);
    }

    public static List<Object> popPreviousInputs() {
        return previousInputs.pop();
    }
}
