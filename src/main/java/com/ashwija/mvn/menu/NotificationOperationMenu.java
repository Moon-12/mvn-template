package com.ashwija.mvn.menu;

import com.ashwija.mvn.central.CentralContext;
import com.ashwija.mvn.common.*;
import com.ashwija.mvn.dao.AppDao;
import com.ashwija.mvn.dao.MessageDao;
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


    @Override
    public void performAction(List<Object> inputList) {
        AppDao appDao = super.getAppDao();
        NotificationDao notificationDao = new NotificationDao();
        switch (super.getOperationType()) {
            case VIEW_ALL:

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
                            notificationListSubmenu.put(option, notificationOperationMenu);

                            //populate the notifications in central context
                            CentralContext.putIntoNotificationEntityMap(option, notificationEntity);

                            option++;
                        }
                        // OperationMenu operationMenu = new OperationMenu(null, 1, notificationListSubmenu, new ArrayList<>(List.of("select a notification to view: ")), OperationType.VIEW_NOTIFICATION, notificationDao);
                        NavigationMenu navigationMenu = new NavigationMenu(null, 1, notificationListSubmenu, new ArrayList<>(List.of("select a notification to view: ")));
                        CentralContext.pushNextMenu(navigationMenu);

                    } else {
                        System.out.println("No new notifications");
                        CentralContext.resetToRootMenu();
                    }
                } catch (SQLException e) {
                    System.out.println(appDao.getFetchFailureMessage() + " due to " + e.getMessage());
                }
                break;
            case VIEW:
                // get previous user selection
                String displayFriendRequestResultMsg = "";
                List<Object> previousInput = CentralContext.popPreviousInputs();
                NotificationEntity notificationEntity = CentralContext.getNotificationEntityByKey(previousInput.get(0).toString().charAt(0));
//
//                if (notificationEntity.getContent() != null) {
//                    System.out.println(notificationEntity.getContent());
//                }

                List<Object> attributes = new ArrayList<>();
                attributes.add(notificationEntity.getType()); // Add notification type


                if (notificationEntity.getType() == NotificationType.FRIEND) {
                    attributes.add(inputList.isEmpty() ? FriendRequestStatus.REJECTED : FriendRequestStatus.ACCEPTED);
                    displayFriendRequestResultMsg = inputList.isEmpty() ? "Friend request rejected successfully!" : "Friend request accepted successfully!";
                } else { // NotificationType.MESSAGE
                    attributes.add(MessageStatus.READ);
                }

                attributes.add(notificationEntity.getObjectID());
                try {
                    int rowsAffected = notificationDao.update(attributes);
                    if (rowsAffected > 0) {
                        if (notificationEntity.getType() == NotificationType.FRIEND) {
                            System.out.println(displayFriendRequestResultMsg);
                        }
                        //size 2 means that reply msg is added
                        else if (notificationEntity.getType() == NotificationType.MESSAGE && inputList.size() == 2) {
                            MessageDao messageDao = new MessageDao();
                            rowsAffected = messageDao.save(List.of(notificationEntity.getSenderId(),
                                    inputList.get(1).toString(),
                                    CentralContext.getLoggedInUserID(),
                                    DateAndTime.getCurrentTimestamp()));
                            if (rowsAffected > 0) {
                                System.out.println(messageDao.getSaveSuccessMessage());
                            } else {
                                System.out.println(messageDao.getSaveFailureMessage());
                            }
                        }
                    }
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
                CentralContext.resetToRootMenu();
                break;

        }

    }
}
