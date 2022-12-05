package com.ssafy.ssafit.model.dto;

import java.sql.Timestamp;

public class Comment {
    private int commentId;
    private String parentId;
    private String playlistId;
    private String userId;
    private String content;
    private int likeCnt;
    private Timestamp createdAt;
    private Timestamp editedAt;
    private int isLike; // 로그인 한 유저의 입장에서 이걸 좋아요 눌렀는지 아닌지 확인한다.
    private int isMine;

    public int getCommentId() {
        return commentId;
    }

    public void setCommentId(int commentId) {
        this.commentId = commentId;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getPlaylistId() {
        return playlistId;
    }

    public void setPlaylistId(String playlistId) {
        this.playlistId = playlistId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getLikeCnt() {
        return likeCnt;
    }

    public void setLikeCnt(int likeCnt) {
        this.likeCnt = likeCnt;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    public Timestamp getEditedAt() {
        return editedAt;
    }

    public void setEditedAt(Timestamp editedAt) {
        this.editedAt = editedAt;
    }

    public int getIsLike() {
        return isLike;
    }

    public void setIsLike(int isLike) {
        this.isLike = isLike;
    }

    public int getIsMine() {
        return isMine;
    }

    public void setIsMine(int isMine) {
        this.isMine = isMine;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "commentId=" + commentId +
                ", parentId='" + parentId + '\'' +
                ", playlistId='" + playlistId + '\'' +
                ", userId='" + userId + '\'' +
                ", content='" + content + '\'' +
                ", likeCnt=" + likeCnt +
                ", createdAt=" + createdAt +
                ", editedAt=" + editedAt +
                ", isLike=" + isLike +
                ", isMine=" + isMine +
                '}';
    }
}
