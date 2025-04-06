package com.ashwija.mvn.dao;

import com.ashwija.mvn.central.CentralContext;
import com.ashwija.mvn.common.AppConstants;
import com.ashwija.mvn.common.DateAndTime;
import com.ashwija.mvn.common.LoginStatus;
import com.ashwija.mvn.common.OperationType;
import com.ashwija.mvn.model.AppEntity;
import com.ashwija.mvn.model.PopularHashTagEntity;


import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class OperationMenu<T extends AppEntity> extends Menu {
    private OperationType operationType;
    private AppDao appDao;

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
        boolean isNextMenuSet = false;

        switch (operationType) {
            case ADD:
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
                    inputList.add(AppConstants.getLoggedInUserID());
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
                break;
            case DELETE:
                try {
                    int rowsAffected = appDao.delete(Integer.parseInt(inputList.get(0).toString()));
                    System.out.println((rowsAffected > 0) ?
                            this.appDao.getDeleteSuccessMessage()
                            : this.appDao.getDeleteFailureMessage()
                    );
                } catch (SQLException e) {
                    System.out.println(this.appDao.getDeleteFailureMessage() + " due to " + e.getMessage());
                }
                break;
            case VIEW_ALL:
                List<T> entityList = appDao.fetchAll();
                //print selected entity header row
                if (!entityList.isEmpty()) {
                    System.out.println(entityList.get(0).getHeader());
                } else {
                    System.out.println("(0) rows found.");
                }
                entityList.forEach(System.out::println);
                break;
            case VIEW:
                Optional<T> entity = appDao.fetch(Integer.parseInt((String) inputList.get(0)));
                if (entity.isPresent()) {
                    T entityObj = entity.get();
                    System.out.println(entityObj.getHeader());
                    System.out.println(entityObj);
                } else {
                    System.out.println("Not found");
                }
                break;
            case LOGIN:
                UserProfileDao userProfileDao = (UserProfileDao) appDao;
                try {
                    int checksumTotal = userProfileDao.login(inputList);
                    System.out.println(LoginStatus.fromCode(checksumTotal).getMessage());
                    if (checksumTotal == LoginStatus.SUCCESS.getCode()) {
                        CentralContext.setNextMenu(AppConstants.getSecureMenu());
                        //set logged in userID in central context
                        AppConstants.setLoggedInUserID(inputList.get(0).toString());
                        isNextMenuSet = true;
                    }
                } catch (SQLException e) {
                    System.out.println(this.appDao.getSaveFailureMessage() + " due to " + e.getMessage());
                }
                break;
            case HASHTAG:
                PostDao postDao = (PostDao) appDao;
                try {
                    List<PopularHashTagEntity> popularHashTagEntityList = postDao.getPopularHashTags();
                    if (!popularHashTagEntityList.isEmpty()) {
                        System.out.println(popularHashTagEntityList.get(0).getHeader());
                        popularHashTagEntityList.stream().forEach(obj -> System.out.println(obj));
                    } else {
                        System.out.println("No HashTags found!");
                    }
                } catch (SQLException e) {
                    System.out.println(this.appDao.getSaveFailureMessage() + " due to " + e.getMessage());
                }
        }

        // Set prevMenu only if nextMenu wasn’t set
        if (!isNextMenuSet) {
            CentralContext.setPrevMenu();
        }
    }
}
