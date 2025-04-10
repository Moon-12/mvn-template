package com.ashwija.mvn.menu;

import com.ashwija.mvn.central.CentralContext;
import com.ashwija.mvn.common.DateAndTime;
import com.ashwija.mvn.common.LoginStatus;
import com.ashwija.mvn.common.NotificationType;
import com.ashwija.mvn.common.OperationType;
import com.ashwija.mvn.dao.*;
import com.ashwija.mvn.model.*;


import java.sql.SQLException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class OperationMenu<T extends AppEntity> extends Menu {
    private final OperationType operationType;
    private AppDao appDao = null;

    public OperationMenu(String title, OperationType operationType, AppDao appDao) {
        super(title);
        this.operationType = operationType;
        this.appDao = appDao;
    }

    public OperationMenu(String title, int padding, Map<Character, Menu> subMenu, List<String> inputLabel, OperationType operationType, AppDao appDao) {
        super(title, padding, subMenu, inputLabel);
        this.operationType = operationType;
        this.appDao = appDao;
    }

    @Override
    public void performAction(List<Object> inputList) {
        boolean isNextMenuAlreadySet = false;
        switch (operationType) {

            case ADD:
                addOperation(inputList);
                break;
            case DELETE:
                deleteOperation(inputList);
                break;
            case VIEW_ALL:
                viewAllOperation();
                break;
            case VIEW:
                viewOperation(inputList);
                break;
            case LOGIN:
                isNextMenuAlreadySet = loginOperation(inputList);
                break;
            case POPULAR_HASHTAG:
                isNextMenuAlreadySet = getPopularHashtag();
                break;
            case VIEW_HASHTAG_POST:
                isNextMenuAlreadySet = viewPopularHashtag(inputList);
                break;
            case VIEW_FRIEND_LIST:
                isNextMenuAlreadySet = getViewFriendList();
                break;
            case VIEW_USER_PROFILE:
                isNextMenuAlreadySet = getViewUserProfile(inputList);
                break;
            case VIEW_ALL_ACTIVE_NOTIFICATION:
                isNextMenuAlreadySet = getViewAllActiveNotification();
                break;
            case VIEW_NOTIFICATION:
        }

        // Set prevMenu only if nextMenu wasn’t set
        if (!isNextMenuAlreadySet) {
            CentralContext.setPrevMenu();
        }
    }

    private boolean getViewAllActiveNotification() {
        boolean isNextMenuAlreadySet = false;
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
                    OperationMenu notificationEntityOperationMenu = new OperationMenu<NotificationEntity>(
                            subMenuTitle.concat(notificationEntity.getSenderId()),
                            OperationType.VIEW_NOTIFICATION,
                            notificationDao
                    );

                    notificationEntityOperationMenu.setInputLabelList(new ArrayList<>(List.of(
                            notificationEntity.getType() == NotificationType.MESSAGE ?
                                    new String[]{
                                            "Do you want to reply?(Y/N)",
                                            "Enter your reply:"
                                    } :
                                    new String[]{
                                            "Do you want to accept?(Y/N)"
                                    }
                    )));
                    notificationListSubmenu.put(option++, notificationEntityOperationMenu);

                    //populate the notifications in central context
                    CentralContext.getNotificationEntityMap().put(option, notificationEntity);
                }
                // OperationMenu operationMenu = new OperationMenu(null, 1, notificationListSubmenu, new ArrayList<>(List.of("select a notification to view: ")), OperationType.VIEW_NOTIFICATION, notificationDao);
                NavigationMenu navigationMenu = new NavigationMenu(null, 1, notificationListSubmenu, new ArrayList<>(List.of("select a notification to view: ")));
                CentralContext.pushNextMenu(navigationMenu);
                isNextMenuAlreadySet = true;

            } else {
                System.out.println("No new notifications");
            }
        } catch (SQLException e) {
            System.out.println(this.appDao.getFetchFailureMessage() + " due to " + e.getMessage());
        } finally {
            return isNextMenuAlreadySet;
        }
    }

    private boolean getViewUserProfile(List<Object> inputList) {
        Optional<T> optionalEntity = Optional.empty();
        String selectedUserID = CentralContext.peekCurrentMenuStack().getSubMenuAt(inputList.get(0).toString().charAt(0)).getTitle();
        try {
            optionalEntity = appDao.fetch(selectedUserID);
        } catch (SQLException e) {
            System.out.println(this.appDao.getSaveFailureMessage() + " due to " + e.getMessage());
        } finally {
            if (optionalEntity.isPresent()) {
                T entityObj = optionalEntity.get();
                System.out.println(entityObj.getHeader());
                System.out.println(entityObj);
            } else {
                System.out.println("User not found");
            }
            CentralContext.resetToRootMenu();
            return true;
        }
    }

    private boolean getViewFriendList() {
        boolean isNextMenuAlreadySet = false;
        UserProfileDao userProfileDaoObj = new UserProfileDao();
        Map<Character, Menu> friendListSubmenu = new HashMap<>();
        FriendDao friendDao = (FriendDao) appDao;
        try {
            List<FriendEntity> friendEntityList = friendDao.getListOfFriends();
            if (!friendEntityList.isEmpty()) {
                char option = '1';
                for (FriendEntity friendEntity : friendEntityList) {
                    friendListSubmenu.put(option++, new OperationMenu<FriendEntity>(
                            friendEntity.getReceiverId(),
                            OperationType.VIEW_USER_PROFILE,
                            userProfileDaoObj
                    ));
                }
                OperationMenu operationMenu = new OperationMenu(null, 1, friendListSubmenu, new ArrayList<>(List.of("select a friend: ")), OperationType.VIEW_USER_PROFILE, userProfileDaoObj);
                CentralContext.pushNextMenu(operationMenu);
                isNextMenuAlreadySet = true;
            } else {
                System.out.println("No Friends found!");
            }
        } catch (SQLException e) {
            System.out.println(this.appDao.getSaveFailureMessage() + " due to " + e.getMessage());
        } finally {
            return isNextMenuAlreadySet;
        }
    }

    private boolean viewPopularHashtag(List<Object> inputList) {
        boolean isNextMenuAlreadySet = false;
        List<PostEntity> postEntityList = new ArrayList<>();
        String selectedHashtag = CentralContext.peekCurrentMenuStack().getSubMenuAt(inputList.get(0).toString().charAt(0)).getTitle();
        PostDao postDaoObj = (PostDao) appDao;

        try {
            postEntityList = postDaoObj.getPostsByHashTags(selectedHashtag);
            if (!postEntityList.isEmpty()) {
                for (PostEntity postEntity : postEntityList) {
                    System.out.println(postEntity.detailedToString());
                }
            } else {
                System.out.println("No Posts Yet !!");
            }
        } catch (SQLException e) {
            System.out.println(this.appDao.getSaveFailureMessage() + " due to " + e.getMessage());
        } finally {
            isNextMenuAlreadySet = true;
            CentralContext.resetToRootMenu();
            return isNextMenuAlreadySet;
        }
    }

    private boolean getPopularHashtag() {
        boolean isNextMenuAlreadySet = false;
        PostDao postDao = (PostDao) appDao;
        Map<Character, Menu> popularHashTagSubMenu = new HashMap<>();
        try {
            List<PopularHashTagEntity> popularHashTagEntityList = postDao.getPopularHashTags();
            if (!popularHashTagEntityList.isEmpty()) {
                char option = '1';
                for (PopularHashTagEntity tag : popularHashTagEntityList) {
                    popularHashTagSubMenu.put(option++, new OperationMenu<PostEntity>(
                            tag.getName(),
                            OperationType.VIEW_HASHTAG_POST,
                            postDao
                    ));
                }
                OperationMenu operationMenu = new OperationMenu(null, 1, popularHashTagSubMenu, new ArrayList<>(List.of("select a hashtag: ")), OperationType.VIEW_HASHTAG_POST, postDao);
                CentralContext.pushNextMenu(operationMenu);
                isNextMenuAlreadySet = true;

            } else {
                System.out.println("No HashTags found!");
            }
        } catch (SQLException e) {
            System.out.println(this.appDao.getSaveFailureMessage() + " due to " + e.getMessage());
        } finally {
            return isNextMenuAlreadySet;
        }
    }

    private boolean loginOperation(List<Object> inputList) {
        UserProfileDao userProfileDao = (UserProfileDao) appDao;
        boolean isNextMenuAlreadySet = false;
        try {
            int checksumTotal = userProfileDao.login(inputList);
            System.out.println(LoginStatus.fromCode(checksumTotal).getMessage());
            if (checksumTotal == LoginStatus.SUCCESS.getCode()) {
                //set logged in userID in central context
                CentralContext.setLoggedInUserID(inputList.get(0).toString());

                //fetch 2 posts from friends
                PostDao postDao = new PostDao();
                List<PostEntity> postEntityList = postDao.get2PostsFromFriends();
                if (!postEntityList.isEmpty()) {
                    for (PostEntity postEntity : postEntityList) {
                        System.out.println(postEntity.detailedToString());
                    }
                } else {
                    System.out.println("No posts to display");
                }
                CentralContext.resetToRootMenu();
                isNextMenuAlreadySet = true;
            }
        } catch (SQLException e) {
            System.out.println(this.appDao.getSaveFailureMessage() + " due to " + e.getMessage());
        } finally {
            return isNextMenuAlreadySet;
        }
    }

    private void viewOperation(List<Object> inputList) {
        Optional<T> entity = null;
        try {
            entity = appDao.fetch(Integer.parseInt((String) inputList.get(0)));
        } catch (SQLException e) {
            System.out.println(this.appDao.getSaveFailureMessage() + " due to " + e.getMessage());
        }
        if (entity.isPresent()) {
            T entityObj = entity.get();
            System.out.println(entityObj.getHeader());
            System.out.println(entityObj);
        } else {
            System.out.println("Not found");
        }
    }

    private void viewAllOperation() {
        List<T> entityList = appDao.fetchAll();
        //print selected entity header row
        if (!entityList.isEmpty()) {
            System.out.println(entityList.get(0).getHeader());
        } else {
            System.out.println("(0) rows found.");
        }
        entityList.forEach(System.out::println);
    }

    private void deleteOperation(List<Object> inputList) {
        try {
            int rowsAffected = appDao.delete(Integer.parseInt(inputList.get(0).toString()));
            System.out.println((rowsAffected > 0) ?
                    this.appDao.getDeleteSuccessMessage()
                    : this.appDao.getDeleteFailureMessage()
            );
        } catch (SQLException e) {
            System.out.println(this.appDao.getDeleteFailureMessage() + " due to " + e.getMessage());
        }
    }

    private void addOperation(List<Object> inputList) {
        if (appDao instanceof PostDao) {
            String hashtagRegex = "#\\w+";
            Pattern pattern = Pattern.compile(hashtagRegex);
            Matcher matcher = pattern.matcher(inputList.get(0).toString());
            if (matcher.find()) {
                inputList.add(matcher.group());
            }
        }
        //if sending message or creating new post add sender_id and current timestamp to input list
        if (appDao instanceof MessageDao || appDao instanceof PostDao || appDao instanceof FriendDao) {
            inputList.add(CentralContext.getLoggedInUserID());
            inputList.add(DateAndTime.getCurrentTimestamp());
        }
        if (this.appDao.validateInput(inputList)) {
            try {
                int rowsAffected = this.appDao.save(inputList);
                System.out.println((rowsAffected > 0) ?
                        this.appDao.getSaveSuccessMessage()
                        : this.appDao.getSaveFailureMessage()
                );
            } catch (SQLException e) {
                System.out.println(this.appDao.getSaveFailureMessage() + " due to " + e.getMessage());
            }
        } else {
            System.out.println(this.appDao.getValidationFailureMessage());
        }
    }
}
