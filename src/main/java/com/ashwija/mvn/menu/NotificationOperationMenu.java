package com.ashwija.mvn.menu;

import com.ashwija.mvn.central.CentralContext;
import com.ashwija.mvn.common.NotificationType;
import com.ashwija.mvn.common.OperationType;
import com.ashwija.mvn.dao.AppDao;
import com.ashwija.mvn.dao.NotificationDao;
import com.ashwija.mvn.model.NotificationEntity;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NotificationOperationMenu extends OperationMenu<NotificationEntity> {

    public NotificationOperationMenu(String title, OperationType operationType, AppDao appDao) {
        super(title, operationType, appDao);
    }

    public NotificationOperationMenu(String title, int padding, Map subMenu, List inputLabel, OperationType operationType, AppDao appDao) {
        super(title, padding, subMenu, inputLabel, operationType, appDao);
    }

    @Override
    public void performAction(List<Object> inputList) {
        AppDao appDao = super.getAppDao();
        switch (super.getOperationType()) {
            case VIEW_ALL:
                NotificationDao notificationDao = new NotificationDao();
                Map<Character, Menu> notificationListSubmenu = new HashMap<>();
                List<NotificationEntity> notificationEntityList;
                try {
                    notificationEntityList = notificationDao.getAllActiveNotifications();
                    if (!notificationEntityList.isEmpty()) {
                        char option = '1';
                        for (NotificationEntity notificationEntity : notificationEntityList) {
                            String subMenuTitle = notificationEntity.getType() == NotificationType.MESSAGE ?
                                    "New Message from: " :
                                    "New Friend Request from: ";
                            NotificationOperationMenu notificationOperationMenu = new NotificationOperationMenu(
                                    subMenuTitle.concat(notificationEntity.getSenderId()),
                                    OperationType.VIEW,
                                    notificationDao
                            );

                            notificationOperationMenu.setInputLabelList(new ArrayList<>(List.of(
                                    notificationEntity.getType() == NotificationType.MESSAGE ?
                                            new String[]{
                                                    "Do you want to reply?(Y/N)",
                                                    "Enter your reply:"
                                            } :
                                            new String[]{
                                                    "Do you want to accept?(Y/N)"
                                            }
                            )));
                            notificationListSubmenu.put(option++, notificationOperationMenu);

                            //populate the notifications in central context
                            CentralContext.getNotificationEntityMap().put(option, notificationEntity);
                        }
                        // OperationMenu operationMenu = new OperationMenu(null, 1, notificationListSubmenu, new ArrayList<>(List.of("select a notification to view: ")), OperationType.VIEW_NOTIFICATION, notificationDao);
                        NavigationMenu navigationMenu = new NavigationMenu(null, 1, notificationListSubmenu, new ArrayList<>(List.of("select a notification to view: ")));
                        CentralContext.pushNextMenu(navigationMenu);


                    } else {
                        System.out.println("No new notifications");
                    }
                } catch (SQLException e) {
                    System.out.println(appDao.getFetchFailureMessage() + " due to " + e.getMessage());
                }
                break;
        }
    }
}
