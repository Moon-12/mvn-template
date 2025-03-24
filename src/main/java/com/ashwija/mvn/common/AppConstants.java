package com.ashwija.mvn.common;

import com.ashwija.mvn.dao.*;

import java.util.*;

public class AppConstants {
    private static Menu mainMenu;

    static {
        // Parse YAML into a Map
        Map<String, Object> yamlData = Utility.yamlToMap("menu-metadata.yaml");
        Map<Character, Object> mainMenuData = (Map<Character, Object>) yamlData.get("mainMenu");

        mainMenu = MenuDao.buildMenuFromYaml((Map<String, Object>) mainMenuData.get("m"));
    }

    public static Menu getMainMenu() {
        return mainMenu;
    }
}
