package com.ashwija.mvn.model;

public class FriendEntity extends AppEntity {
    private final String receiverId;

    public FriendEntity(String receiverId) {
        this.receiverId = receiverId;
    }

    public String getReceiverId() {
        return receiverId;
    }


    @Override
    public String getHeader() {
        return "";
    }
}
