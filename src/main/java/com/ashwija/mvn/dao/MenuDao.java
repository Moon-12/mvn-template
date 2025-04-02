package com.ashwija.mvn.dao;

import com.ashwija.mvn.common.OperationType;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MenuDao {
    public static Menu buildMenuFromYaml(Map<String, Object> menuData) {

        String title = (String) menuData.get("title");
        Integer padding = (Integer) menuData.getOrDefault("padding", 0);
        List<String> inputLabels = (List<String>) menuData.getOrDefault("inputLabels", List.of());
        Map<String, Object> items = (Map<String, Object>) menuData.get("subMenu");

        // If no items, it's a leaf node (OperationMenu)
        if (items == null || items.isEmpty()) {
            String operationTypeStr = (String) menuData.get("operationType");
            OperationType operationType = operationTypeStr != null ? OperationType.valueOf(operationTypeStr) : null;
            String daoStr = (String) menuData.get("dao");
            AppDao dao = daoStr != null ? getDaoObj(daoStr) : null; // Adjust DAO based on context
            OperationMenu<?> operationMenu = new OperationMenu<>(title, operationType, dao);
            operationMenu.setInputLabelList(inputLabels);
            return operationMenu;
        }

        // Otherwise, it's a navigation node (NavigationMenu)
        Map<Character, Menu> subMenu = new HashMap<>();
        for (Map.Entry<String, Object> entry : items.entrySet()) {
            char key = entry.getKey().charAt(0);
            Map<String, Object> subMenuData = (Map<String, Object>) entry.getValue();
            subMenu.put(key, buildMenuFromYaml(subMenuData));
        }

        NavigationMenu navigationMenu = new NavigationMenu(title, padding, subMenu, inputLabels);
        return navigationMenu;
    }

    public static AppDao getDaoObj(String dao) {
        switch (dao) {
            case "SampleEntityDao":
                return new SampleEntityDao();
            default:
                return null;
        }


    }
}
