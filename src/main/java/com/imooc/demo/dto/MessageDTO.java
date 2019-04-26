package com.imooc.demo.dto;

public class MessageDTO {
    private String messageId;
    private String userName;//留言者
    private String userImage;//留言者头像路径
    private String comment;//留言内容
    private String replayUserName;//回复谁的用户名
    private String commentCreateTime;//留言创建时间

    public String getMessageId() {
        return messageId;
    }

    public void setMessageId(String messageId) {
        this.messageId = messageId;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserImage() {
        return userImage;
    }

    public void setUserImage(String userImage) {
        this.userImage = userImage;
    }
    public String getReplayUserName() {
        return replayUserName;
    }

    public void setReplayUserName(String replayUserName) {
        this.replayUserName = replayUserName;
    }

    public String getCommentCreateTime() {
        return commentCreateTime;
    }

    public void setCommentCreateTime(String commentCreateTime) {
        this.commentCreateTime = commentCreateTime;
    }
}
