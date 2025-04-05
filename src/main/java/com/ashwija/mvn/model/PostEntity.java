package com.ashwija.mvn.model;

public class PostEntity extends AppEntity {
    private String content;
    private String userID;
    private String hashTag;

    public PostEntity(String userID, String content, String hashTag) {
        this.userID = userID;
        this.content = content;
        this.hashTag = hashTag;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getHashTag() {
        return hashTag;
    }

    public void setHashTag(String hashTag) {
        this.hashTag = hashTag;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    @Override
    public String getHeader() {
        return "";
    }
}
