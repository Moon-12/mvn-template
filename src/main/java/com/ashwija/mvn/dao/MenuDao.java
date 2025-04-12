package com.ashwija.mvn.dao;

import com.ashwija.mvn.common.OperationType;
import com.ashwija.mvn.menu.*;
import com.ashwija.mvn.model.AppEntity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MenuDao {
    public static Menu buildMenuFromYaml(Map<String, Object> menuData) {

        String title = (String) menuData.get("title");
        Integer padding = (Integer) menuData.getOrDefault("padding", 0);
        List<String> inputLabels = (List<String>) menuData.getOrDefault("inputLabels", List.of());
        Map<String, Object> items = (Map<String, Object>) menuData.get("subMenu");

        String overrideOperationMenu = (String) menuData.getOrDefault("overrideOperationMenu", "OperationMenu");
        // If no items, it's a leaf node (OperationMenu)
        if (items == null || items.isEmpty()) {
            String operationTypeStr = (String) menuData.get("operationType");
            OperationType operationType = operationTypeStr != null ? OperationType.valueOf(operationTypeStr) : null;
            String daoStr = (String) menuData.get("dao");
            AppDao dao = daoStr != null ? getDaoObj(daoStr) : null; // Adjust DAO based on context
            OperationMenu<? extends AppEntity> operationMenu = getOperationMenuObj(overrideOperationMenu, title, operationType, dao);

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

        return new NavigationMenu(title, padding, subMenu, inputLabels);
    }

    public static OperationMenu<? extends AppEntity> getOperationMenuObj(String overrideOperationMenu, String title, OperationType operationType, AppDao dao) {
        switch (overrideOperationMenu) {
            case "LoginOperationMenu":
                return new LoginOperationMenu(title, operationType, dao);
            case "NotificationOperationMenu":
                return new NotificationOperationMenu(title, operationType, dao);
            case "CommentOperationMenu":
                return new CommentOperationMenu(title, operationType, dao);
            default:
                return new OperationMenu(title, operationType, dao);

        }
    }

    public static AppDao<? extends AppEntity> getDaoObj(String dao) {
        switch (dao) {
            case "UserProfileDao":
                return new UserProfileDao();
            case "MessageDao":
                return new MessageDao();
            case "PostDao":
                return new PostDao();
            case "FriendDao":
                return new FriendDao();
            case "NotificationDao":
                return new NotificationDao();
            case "CommentDao":
                return new CommentDao();
            default:
                return null;
        }


    }
}
