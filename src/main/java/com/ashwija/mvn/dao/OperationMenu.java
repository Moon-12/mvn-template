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
                // appEntity.delete(inputList.getFirst());
                break;
            case VIEWALL:
                List<T> entityList = appDao.fetchAll();
                //print selected entity header row
                if (entityList.size() > 0) {
                    System.out.println(entityList.get(0).getHeader());
                } else {
                    System.out.println("(0) rows found.");
                }
                entityList.stream().forEach(System.out::println);
                break;
        }
        CentralContext.setPrevMenu();
    }
}
