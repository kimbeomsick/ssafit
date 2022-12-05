package com.ssafy.ssafit.model.service;

import com.ssafy.ssafit.model.dao.VideoDao;
import com.ssafy.ssafit.model.dto.Video;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class VideoServiceImpl implements VideoService{

    @Autowired
    private VideoDao videoDao;


    @Override
    public int existsInVideo(String videoId) {
        return videoDao.existsInVideo(videoId);
    }

    @Override
    public int isLikedVideo(String videoId, String userId) {
        return videoDao.existsInVideoLike(videoId, userId);
    }

    @Override
    public int existsInVideoInPlaylist(String videoId, int playlistId) {
        return videoDao.existsInVideoInPlaylist(videoId, playlistId);
    }

    @Override
    public int insertVideo(Video video) {
        return videoDao.insertVideo(video);
    }

    @Override
    public Video getVideo(String videoId) {
        return videoDao.selectVideo(videoId);
    }

    @Override
    public List<Video> myVideos(String userId) {
        return videoDao.selectMyLikedVideos(userId);
    }

    @Override
    public List<Video> recommendVideos(int num) {
        return videoDao.recommendVideo(num);
    }


    @Override
    public int likeVideo(String videoId, String userId) {
        return videoDao.likeVideo(videoId, userId);
    }

    @Override
    public int unlikeVideo(String videoId, String userId) {
        return videoDao.unlikeVideo(videoId, userId);
    }


    @Override
    public int increaseLikeCnt(String videoId) {
        return videoDao.increaseLikeCnt(videoId);
    }

    @Override
    public int decreaseLikeCnt(String videoId) {
        return videoDao.decreaseLikeCnt(videoId);
    }

    @Override
    public int insertVideoIntoPlaylist(String videoId, int playlistId) {
        return videoDao.insertVideoIntoPlaylist(videoId, playlistId);
    }

    @Override
    public int deleteVideoFromPlaylist(String videoId, int playlistId) {
        return videoDao.deleteVideoFromPlaylist(videoId, playlistId);
    }
//
//    @Override
//    public int setIsLike(String videoId, String userId) {
//        int result = videoDao.existsInVideoLike(videoId, userId);
//    }
}
