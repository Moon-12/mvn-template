package com.ashwija.mvn.common;

import com.ashwija.mvn.dao.*;

import java.util.*;

public class AppConstants {
    private final static Menu mainMenu;
    private final static Menu secureMenu;

    static {
        // Parse YAML into a Map
        Map<String, Object> yamlData = Utility.yamlToMap("menu-metadata.yaml");
        Map<Character, Object> mainMenuData = (Map<Character, Object>) yamlData.get("mainMenu");

        mainMenu = MenuDao.buildMenuFromYaml((Map<String, Object>) mainMenuData.get("m"));

        //load secure menu
        Map<String, Object> secureYamlData = Utility.yamlToMap("secure-menu-metadata.yaml");
        Map<String, Object> secureMenuData = (Map<String, Object>) secureYamlData.get("secureMenu");
        secureMenu = MenuDao.buildMenuFromYaml(secureMenuData);
    }

    public static Menu getMainMenu() {
        return mainMenu;
    }

    public static Menu getSecureMenu() {
        return secureMenu;
    }
}
