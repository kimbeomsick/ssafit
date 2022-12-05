package com.ssafy.ssafit.model.dto;

public class Video extends YouTubeDto {
    private String videoId;
    private String title;
    private String thumbnailPath;
    private int likeCnt;
    private int isLike;

    public Video() {

    }
    public Video(String videoId, String title, String thumbnailPath) {
        this.videoId = videoId;
        this.title = title;
        this.thumbnailPath = thumbnailPath;
        this.likeCnt = 0;
    }


    public String getVideoId() {
        return videoId;
    }

    public void setVideoId(String videoId) {
        this.videoId = videoId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getThumbnailPath() {
        return thumbnailPath;
    }

    public void setThumbnailPath(String thumbnailPath) {
        this.thumbnailPath = thumbnailPath;
    }

    public int getLikeCnt() {
        return likeCnt;
    }

    public void setLikeCnt(int likeCnt) {
        this.likeCnt = likeCnt;
    }

    public int getIsLike() {
        return isLike;
    }

    public void setIsLike(int isLike) {
        this.isLike = isLike;
    }

    @Override
    public String toString() {
        return "Video{" +
                "videoId='" + videoId + '\'' +
                ", title='" + title + '\'' +
                ", thumbnailPath='" + thumbnailPath + '\'' +
                ", likeCnt='" + likeCnt + '\'' +
                ", isLike=" + isLike +
                '}';
    }
}

