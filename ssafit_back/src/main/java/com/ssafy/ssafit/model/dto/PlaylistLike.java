package com.ssafy.ssafit.model.dto;

import java.sql.Timestamp;

public class PlaylistLike {
    int playlistId;
    String userId;
    Timestamp likedAt;

    public int getPlaylistId() {
        return playlistId;
    }

    public void setPlaylistId(int playlistId) {
        this.playlistId = playlistId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Timestamp getLikedAt() {
        return likedAt;
    }

    public void setLikedAt(Timestamp likedAt) {
        this.likedAt = likedAt;
    }

    @Override
    public String toString() {
        return "PlaylistLike{" +
                "playlistId=" + playlistId +
                ", userId='" + userId + '\'' +
                ", likedAt=" + likedAt +
                '}';
    }
}
