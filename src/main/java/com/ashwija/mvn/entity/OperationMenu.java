package com.ashwija.mvn.entity;

import com.ashwija.mvn.central.CentralContext;
import com.ashwija.mvn.common.OperationType;

import java.util.List;

public class OperationMenu extends Menu {
    final AppEntity appEntity;
    OperationType operationType;

    public OperationMenu(String title, AppEntity myEntity, OperationType operationType) {
        super(title);
        this.appEntity = myEntity;
        this.operationType = operationType;
    }

    @Override
    public void performAction(List<Object> inputList) {
        AppEntity appEntityObj = appEntity.performObjectFactoryFromList(inputList);
        switch (operationType) {
            case ADD:
                appEntityObj.save(appEntityObj);
                break;
            case DELETE:
                appEntityObj.delete(appEntityObj);
                break;
            case VIEW:
                appEntityObj.fetch(appEntityObj);
                break;
        }
        CentralContext.setCurrentMenu(CentralContext.getPrevMenu());
    }
}
