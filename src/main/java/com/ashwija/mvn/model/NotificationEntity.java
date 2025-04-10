package com.ashwija.mvn.model;

import com.ashwija.mvn.common.NotificationType;

public class NotificationEntity extends AppEntity {
    private final Integer objectID;
    private final NotificationType type;

    private final String senderId;

    public NotificationEntity(String senderId, String type, Integer objectID) {
        this.senderId = senderId;
        this.type = NotificationType.valueOf(type);
        this.objectID = objectID;
    }

    public String getSenderId() {
        return senderId;
    }

    public NotificationType getType() {
        return type;
    }

    @Override
    public String getHeader() {
        return "";
    }
}
