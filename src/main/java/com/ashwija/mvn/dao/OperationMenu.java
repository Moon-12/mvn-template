package com.ashwija.mvn.dao;

import com.ashwija.mvn.central.CentralContext;
import com.ashwija.mvn.common.OperationType;

import java.util.List;

public class OperationMenu extends Menu {
    final AppDao appEntity;
    OperationType operationType;

    public OperationMenu(String title, AppDao myEntity, OperationType operationType) {
        super(title);
        this.appEntity = myEntity;
        this.operationType = operationType;
    }

    @Override
    public void performAction(List<Object> inputList) {
        switch (operationType) {
            case ADD:
                appEntity.save(inputList);
                break;
            case DELETE:
                // appEntity.delete(inputList.getFirst());
                break;
            case VIEW:
                appEntity.fetchAll((String) inputList.get(0));
                break;
        }
        CentralContext.setCurrentMenu(CentralContext.getPrevMenu());
    }
}
