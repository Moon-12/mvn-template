package com.ashwija.mvn.model;

import java.sql.Timestamp;
import java.util.List;

public class PostEntity extends AppEntity {
    private final String content;
    private final String userID;
    private final Timestamp created_at;
    private Integer id;
    private List<CommentEntity> commentEntityList;

    public String getContent() {
        return content;
    }

    public void setCommentEntityList(List<CommentEntity> commentEntityList) {
        this.commentEntityList = commentEntityList;
    }

    public PostEntity(Integer id, String userID, String content, Timestamp created_at) {
        this.id = id;
        this.userID = userID;
        this.content = content;
        this.created_at = created_at;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String getHeader() {
        return "";
    }


    public String detailedToString() {
        StringBuilder sb = new StringBuilder();

        // Add post details
        sb.append("Post Content: ").append(content).append("\n");
        sb.append("Created by: ").append(userID).append(" at ").append(created_at).append("\n");

        // Add comments if they exist
        if (commentEntityList != null && !commentEntityList.isEmpty()) {
            sb.append("Comments:\n");
            for (CommentEntity comment : commentEntityList) {
                sb.append("  Comment: ").append(comment.getContent()).append("\n");
                sb.append("  Created by: ").append(comment.getUserId()).append(" at ").append(comment.getCreated_at()).append("\n");
            }
        } else {
            sb.append("No comments yet\n");
        }

        return sb.toString();
    }
}
