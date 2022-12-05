package com.ssafy.ssafit.model.dto;

import java.sql.Timestamp;

public class Playlist {
    int playlistId;
    String playlistTitle;
    String content;
    String userId;
    int likeCnt;
    Timestamp createdAt;
    Timestamp editedAt;
    int open;
    int isMine;
    int isLiked;

    public Playlist() {
    }

    public Playlist(String playlistTitle, String content, String userId) {
        this.playlistTitle = playlistTitle;
        this.content = content;
        this.userId = userId;
    }

    public int getPlaylistId() {
        return playlistId;
    }

    public void setPlaylistId(int playlistId) {
        this.playlistId = playlistId;
    }

    public String getPlaylistTitle() {
        return playlistTitle;
    }

    public void setPlaylistTitle(String playlistTitle) {
        this.playlistTitle = playlistTitle;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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

    public int getOpen() {
        return open;
    }

    public void setOpen(int open) {
        this.open = open;
    }

    public Timestamp getEditedAt() {
        return editedAt;
    }

    public void setEditedAt(Timestamp editedAt) {
        this.editedAt = editedAt;
    }

    public int getIsMine() {
        return isMine;
    }

    public void setIsMine(int isMine) {
        this.isMine = isMine;
    }

    public int getIsLiked() {
        return isLiked;
    }

    public void setIsLiked(int isLiked) {
        this.isLiked = isLiked;
    }


    @Override
    public String toString() {
        return "Playlist{" +
                "playlistId=" + playlistId +
                ", playlistTitle='" + playlistTitle + '\'' +
                ", content='" + content + '\'' +
                ", userId='" + userId + '\'' +
                ", likeCnt=" + likeCnt +
                ", createdAt=" + createdAt +
                ", editedAt=" + editedAt +
                ", open=" + open +
                ", isMine=" + isMine +
                ", isLiked=" + isLiked +
                '}';
    }
}
