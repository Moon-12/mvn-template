package com.ashwija.mvn.central;

import com.ashwija.mvn.entity.Menu;

public class CentralContext {
    private static Menu currentMenu;

    public static void setCurrentMenu(Menu currentMenu) {
        CentralContext.currentMenu = currentMenu;
    }

    public static Menu getCurrentMenu() {
        return currentMenu;
    }
}
