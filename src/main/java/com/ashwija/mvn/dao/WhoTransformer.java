package com.ashwija.mvn.dao;

import com.ashwija.mvn.central.CentralContext;
import com.ashwija.mvn.common.DateAndTime;

import java.util.List;

public interface WhoTransformer {
    //if sending message or creating new post add sender_id and current timestamp to input list
    default List<Object> whoTransform(List<Object> inputList) {
        inputList.add(CentralContext.getLoggedInUserID());
        inputList.add(DateAndTime.getCurrentTimestamp());
        return inputList;
    }
}
