package com.ashwija.mvn.menu;

import com.ashwija.mvn.central.CentralContext;
import com.ashwija.mvn.common.LoginStatus;
import com.ashwija.mvn.common.OperationType;
import com.ashwija.mvn.dao.AppDao;
import com.ashwija.mvn.dao.PostDao;
import com.ashwija.mvn.dao.UserProfileDao;
import com.ashwija.mvn.model.AppEntity;
import com.ashwija.mvn.model.PostEntity;
import com.ashwija.mvn.model.UserProfileEntity;

import java.sql.SQLException;
import java.util.List;

public class LoginOperationMenu extends OperationMenu<UserProfileEntity> {
    public LoginOperationMenu(String title, OperationType operationType, AppDao<? extends AppEntity> appDao) {
        super(title, operationType, appDao);
    }


    @Override
    public void performAction(List<Object> inputList) {
        AppDao<? extends AppEntity> appDao = super.getAppDao();
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
                            char option = '1';
                            for (PostEntity postEntity : postEntityList) {
                                System.out.println(option + ". " + postEntity.detailedToString());
                                CentralContext.putIntoPostEntityMap(option, postEntity);
                                option++;
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
