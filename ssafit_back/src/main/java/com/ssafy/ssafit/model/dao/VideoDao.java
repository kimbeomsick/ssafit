package com.ssafy.ssafit.model.dao;

import com.ssafy.ssafit.model.dto.Video;
import com.ssafy.ssafit.model.dto.VideoLike;

import java.util.HashMap;
import java.util.List;


public interface VideoDao {

    int existsInVideo(String videoId);
    int existsInVideoLike(String videoId, String userId);
    int existsInVideoInPlaylist(String videoId, int playlistId);
    int insertVideo(Video video);
    //비디오 하나 가져오기
    Video selectVideo(String videoId);

    List<Video> selectMyLikedVideos(String userId);
    // 추천 비디오
    List<Video> recommendVideo(int num);

    int likeVideo(String videoId, String userId);
    int unlikeVideo(String videoId, String userId);

    int increaseLikeCnt(String videoId);
    int decreaseLikeCnt(String videoId);

    int insertVideoIntoPlaylist(String videoId, int playlistId);
    int deleteVideoFromPlaylist(String videoId, int playlistId);
}