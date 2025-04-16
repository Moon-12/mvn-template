package com.ashwija.mvn.model;

import java.sql.Timestamp;

public class CommentEntity extends AppEntity {
    private final String content;
    private final String userId;
    private final Timestamp created_at;

    public CommentEntity(String content, String userId, Timestamp created_at) {
        this.content = content;
        this.userId = userId;
        this.created_at = created_at;
    }

    public String getContent() {
        return content;
    }

    public Timestamp getCreated_at() {
        return created_at;
    }

    public String getUserId() {
        return userId;
    }

    @Override
    public String getHeader() {
        return "";
    }
}
