package com.ashwija.mvn.menu;

import com.ashwija.mvn.central.CentralContext;
import com.ashwija.mvn.common.LoginStatus;
import com.ashwija.mvn.common.OperationType;
import com.ashwija.mvn.dao.AppDao;
import com.ashwija.mvn.dao.PostDao;
import com.ashwija.mvn.dao.UserProfileDao;
import com.ashwija.mvn.model.PostEntity;
import com.ashwija.mvn.model.UserProfileEntity;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class LoginOperationMenu extends OperationMenu<UserProfileEntity> {
    public LoginOperationMenu(String title, OperationType operationType, AppDao appDao) {
        super(title, operationType, appDao);
    }

    public LoginOperationMenu(String title, int padding, Map<Character, Menu> subMenu, List<String> inputLabel, OperationType operationType, AppDao appDao) {
        super(title, padding, subMenu, inputLabel, operationType, appDao);
    }

    @Override
    public void performAction(List<Object> inputList) {
        AppDao appDao = super.getAppDao();
        switch (super.getOperationType()) {
            case VIEW:
                UserProfileDao userProfileDao = (UserProfileDao) appDao;
                try {
                    int checksumTotal = userProfileDao.login(inputList);
                    System.out.println(LoginStatus.fromCode(checksumTotal).getMessage());
                    if (checksumTotal == LoginStatus.SUCCESS.getCode()) {
                        //set logged in userID in central context
                        CentralContext.setLoggedInUserID(inputList.get(0).toString());

                        //fetch 2 posts from friends
                        PostDao postDao = new PostDao();
                        List<PostEntity> postEntityList = postDao.get2LatestPostsFromFriends();
                        if (!postEntityList.isEmpty()) {
                            for (PostEntity postEntity : postEntityList) {
                                System.out.println(postEntity.detailedToString());
                            }
                        } else {
                            System.out.println("No posts to display");
                        }
                        CentralContext.resetToRootMenu();
                    }
                } catch (SQLException e) {
                    System.out.println(appDao.getSaveFailureMessage() + " due to " + e.getMessage());
                }
        }
    }
}
