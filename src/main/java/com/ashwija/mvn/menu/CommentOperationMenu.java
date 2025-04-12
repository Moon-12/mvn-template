package com.ashwija.mvn.menu;

import com.ashwija.mvn.central.CentralContext;
import com.ashwija.mvn.common.DateAndTime;
import com.ashwija.mvn.common.OperationType;
import com.ashwija.mvn.dao.AppDao;
import com.ashwija.mvn.model.AppEntity;
import com.ashwija.mvn.model.CommentEntity;


import java.sql.SQLException;
import java.util.List;


public class CommentOperationMenu extends OperationMenu<CommentEntity> {
    public CommentOperationMenu(String title, OperationType operationType, AppDao<? extends AppEntity> appDao) {
        super(title, operationType, appDao);
    }

    @Override
    public void performAction(List<Object> inputList) {
        AppDao<? extends AppEntity> appDao = super.getAppDao();
        switch (super.getOperationType()) {
            case ADD:
                if (inputList.size() == 3) {
                    if (!CentralContext.getPostEntityMap().isEmpty()) {
                        Integer selectedPostID = CentralContext.getPostEntityByKey(inputList.get(0).toString().charAt(0)).getId();
                        String content = inputList.get(2).toString();
                        try {
                            int rowsAffected = appDao.save(List.of(content, selectedPostID, CentralContext.getLoggedInUserID(), DateAndTime.getCurrentTimestamp()));
                            System.out.println(rowsAffected > 0 ? appDao.getSaveSuccessMessage() : appDao.getSaveFailureMessage());

                        } catch (SQLException e) {
                            System.out.println(appDao.getSaveFailureMessage() + " due to " + e.getMessage());
                        }
                    }
                }
                CentralContext.resetToRootMenu();
        }
    }

}
