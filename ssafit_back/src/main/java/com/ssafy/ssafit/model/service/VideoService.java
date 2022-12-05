package com.ssafy.ssafit.model.service;

import com.ssafy.ssafit.model.dto.Video;

import java.util.HashMap;
import java.util.List;

public interface VideoService {

    int existsInVideo(String videoId);
    int isLikedVideo(String videoId, String userId);
    int existsInVideoInPlaylist(String videoId, int playlistId);
    int insertVideo(Video video);
    Video getVideo(String videoId);
    List<Video> myVideos(String userId);
    List<Video> recommendVideos(int num);

    int likeVideo(String videoId, String userId);
    int unlikeVideo(String videoId, String userId);

    int increaseLikeCnt(String videoId);
    int decreaseLikeCnt(String videoId);

    int insertVideoIntoPlaylist(String videoId, int playlistId);
    int deleteVideoFromPlaylist(String videoId, int playlistId);
}
