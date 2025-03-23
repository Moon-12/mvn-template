package com.ashwija.mvn.dao;

import com.ashwija.mvn.central.CentralContext;
import com.ashwija.mvn.common.OperationType;
import com.ashwija.mvn.model.Student;

import java.util.List;

public class OperationMenu<> extends Menu {
    OperationType operationType;


    public OperationMenu(String title, AppDao myEntity, OperationType operationType) {
        super(title);
        this.appDao = myEntity;
        this.operationType = operationType;
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
            case VIEW:
                if(AppDao is class of ("Student"))
                    List<Student> entityList =  appDao.fetchAll();
                    System.out.println();
                break;
        }
        CentralContext.setPrevMenu();
    }
}
