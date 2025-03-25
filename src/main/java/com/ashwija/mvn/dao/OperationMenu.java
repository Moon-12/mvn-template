package com.ashwija.mvn.dao;

import com.ashwija.mvn.central.CentralContext;
import com.ashwija.mvn.common.OperationType;
import com.ashwija.mvn.model.AppEntity;

import java.util.List;
import java.util.Map;

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
        switch (operationType) {
            case ADD:
                appDao.save(inputList);
                break;
            case DELETE:
                appDao.delete(inputList.get(0).toString());
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
                T entity = (T) appDao.fetch(inputList.get(0).toString());
                if (entity != null) {
                    System.out.println(entity.getHeader());
                    System.out.println(entity);
                } else {
                    System.out.println("Not found");
                }
                break;
            case ASSIGN_TEACHER:
                CourseDao courseDao = (CourseDao) appDao;
                courseDao.assignTeacher(inputList);
                break;
            case ASSIGN_STUDENT:
                appDao.save(inputList);
        }
        CentralContext.setPrevMenu();
    }
}
