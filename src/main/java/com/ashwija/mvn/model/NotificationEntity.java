package com.ashwija.mvn.model;

import com.ashwija.mvn.common.NotificationType;

public class NotificationEntity extends AppEntity {
    private final NotificationType type;
    private final String content;
    private final String senderId;

    public NotificationEntity(String senderId, String content, String type) {
        this.senderId = senderId;
        this.content = content;
        this.type = NotificationType.valueOf(type);
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
