package com.ashwija.mvn.common;

import com.ashwija.mvn.dao.*;
import com.ashwija.mvn.model.StudentEntity;

import java.util.*;

public class AppConstants {
    private static Menu mainMenu;

    static {
        Map<String, Object> yamlData = Utility.yamlToMap("metadata.yaml");
        mainMenu = buildMainMenu((Map<String, Object>) yamlData.get("mainMenu"));
        Map<Character, Menu> studentMenuItems = buildStudentMenu((Map<String, Object>) yamlData.get("studentMenu"));

        //set student menu items in main menu at position 1
        NavigationMenu studentSubMenu = (NavigationMenu) mainMenu.getSubMenuAt('1');
        studentSubMenu.setSubMenu(studentMenuItems);
    }

    private static NavigationMenu buildMainMenu(Map<String, Object> mainMenuData) {
        Map<Character, Menu> mainMenuItems = new HashMap<>();
        List<String> mainMenuLabels = new ArrayList<>();
        List<Map<String, String>> items = (List<Map<String, String>>) mainMenuData.get("items");

        for (Map<String, String> item : items) {
            char key = item.get("key").charAt(0);
            String label = item.get("label");
            mainMenuItems.put(key, key == '1'
                    ? new NavigationMenu(label, 1, new HashMap<>(), new ArrayList<>())
                    : new NavigationMenu(label));
        }

        mainMenuLabels.add(((String) mainMenuData.get("prompt")).replace("{size}", String.valueOf(mainMenuItems.size())));
        return new NavigationMenu((String) mainMenuData.get("title"), 0, mainMenuItems, mainMenuLabels);
    }

    private static Map<Character, Menu> buildStudentMenu(Map<String, Object> studentMenuData) {
        Map<Character, Menu> studentMenuItems = new HashMap<>();
        AppDao studentDao = new StudentDao();
        List<Map<String, Object>> items = (List<Map<String, Object>>) studentMenuData.get("items");
        List<String> studentMenuLabels = new ArrayList<>();

        for (Map<String, Object> item : items) {
            char key = ((String) item.get("key")).charAt(0);
            String label = (String) item.get("label");
            OperationMenu<StudentEntity> menu = new OperationMenu<>(label,
                    OperationType.valueOf((String) item.get("operation")), studentDao);
            menu.setInputLabelList((List<String>) item.get("inputs"));
            studentMenuItems.put(key, menu);
        }

        String prompt = ((String) studentMenuData.get("prompt")).replace("{size}", String.valueOf(studentMenuItems.size()));
        studentMenuLabels.add(prompt);
        NavigationMenu studentSubMenu = (NavigationMenu) mainMenu.getSubMenuAt('1');
        studentSubMenu.setInputLabelList(studentMenuLabels);

        return studentMenuItems;
    }

    public static Menu getMainMenu() {
        return mainMenu;
    }
}
