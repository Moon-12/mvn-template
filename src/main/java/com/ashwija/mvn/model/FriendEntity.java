package com.ashwija.mvn.model;

public class FriendEntity extends AppEntity {
    private String senderId;
    private String receiverId;

    public FriendEntity(String receiverId, String senderId) {
        this.receiverId = receiverId;
        this.senderId = senderId;
    }

    public String getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(String receiverId) {
        this.receiverId = receiverId;
    }

    public String getSenderId() {
        return senderId;
    }

    public void setSenderId(String senderId) {
        this.senderId = senderId;
    }

    @Override
    public String getHeader() {
        return "";
    }
}
