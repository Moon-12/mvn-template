package com.ashwija.mvn.entity;

import com.ashwija.mvn.central.CentralContext;

import java.util.List;

public class OperationMenu extends Menu {
    public OperationMenu(String title) {
        super(title);
    }

    @Override
    public void performAction(List<Object> inputList) {

        // instantiate and insert entity

        //Integer rollNumber = Integer.parseInt((String)inputList);
        CentralContext.setCurrentMenu(CentralContext.getPrevMenu());
    }
}
