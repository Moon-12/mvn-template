package com.ashwija.mvn.central;

import com.ashwija.mvn.entity.Menu;

public class CentralContext {
    private static Menu currentMenu;
    private static Menu prevMenu;

    public static void setCurrentMenu(Menu currentMenu) {
        prevMenu = CentralContext.currentMenu;
        CentralContext.currentMenu = currentMenu;
    }

    public static void setPrevMenuMenu(Menu prevMenu) {
        CentralContext.prevMenu = prevMenu;
    }

    public static Menu getCurrentMenu() {
        return currentMenu;
    }

    public static Menu getPrevMenu() {
        return prevMenu;
    }
}
