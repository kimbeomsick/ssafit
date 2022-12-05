package com.ssafy.ssafit.model.dto;

public class YouTubeDto {

    private String title; // 동영상 제목
    private String thumbnailPath; //동영상 썸네일 경로
    private String videoId; // 동영상 식별 ID

    public YouTubeDto() {}

    public YouTubeDto(String title, String thumbnailPath, String videoId) {
        this.title = title;
        this.thumbnailPath = thumbnailPath;
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

    public String getVideoId() {
        return videoId;
    }

    public void setVideoId(String videoId) {
        this.videoId = videoId;
    }
}
