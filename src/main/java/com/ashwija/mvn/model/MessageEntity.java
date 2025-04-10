package com.ashwija.mvn.model;

public class MessageEntity extends AppEntity {
    private String senderID;
    private String receiverID;
    private String content;

    public MessageEntity(String senderID, String receiverID, String content) {
        this.senderID = senderID;
        this.receiverID = receiverID;
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String getHeader() {
        return "";
    }
}
