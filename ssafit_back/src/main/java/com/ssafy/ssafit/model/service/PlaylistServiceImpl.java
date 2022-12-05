package com.ssafy.ssafit.model.service;

import com.ssafy.ssafit.model.dao.PlaylistDao;
import com.ssafy.ssafit.model.dto.Playlist;
import com.ssafy.ssafit.model.dto.Video;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Service
public class PlaylistServiceImpl implements PlaylistService{

    @Autowired
    PlaylistDao playlistDao;

    @Override
    public List<Playlist> showMyPlaylists(String userId) {
        List<Playlist> list = playlistDao.getMyOwnPlaylists(userId);
        List<Integer> list2 = playlistDao.getMyLikedPlaylists(userId);
        for (int i : list2) {
            list.add(playlistDao.getPlaylistOne(i));
        }
        // 순서?
        return list;
    }

    @Override
    public int newPlaylist(Playlist playlist) {
        return playlistDao.insertPlaylist(playlist);
    }

    @Override
    public int modifyPlaylist(int playlistId, String playlistTitle, String content) {
        return playlistDao.updatePlaylist(playlistId, playlistTitle, content);
    }

    @Override
    public int deletePlaylist(int playlistId) {
        return playlistDao.deletePlaylist(playlistId);
    }

    @Override
    public Playlist showPlaylist(int playlistId) {
        return playlistDao.getPlaylistOne(playlistId);
    }

    @Override
    public List<Video> showVideosOfPlaylist(int playlistId) {
        return playlistDao.selectVideosOfPlaylist(playlistId);
    }

    @Override
    public int likePlaylist(int playlistId, String userId) {
        return playlistDao.likePlaylist(playlistId, userId);
    }

    @Override
    public int unlikePlaylist(int playlistId, String userId) {
        return playlistDao.unlikePlaylist(playlistId, userId);
    }

    @Override
    public List<Playlist> hotPlaylist(String userId, int num) {
        return playlistDao.hotPlaylist(userId, num);
    }

    @Override
    public List<Playlist> searchPlaylist(String query, String userId, int num) {
        return playlistDao.searchPlaylist(query, userId, num);
    }

    @Override
    public int increaseLikeCnt(int playlistId) {
        return playlistDao.increaseLikeCnt(playlistId);
    }

    @Override
    public int decreaseLikeCnt(int playlistId) {
        return playlistDao.decreaseLikeCnt(playlistId);
    }

    @Override
    public void setIsMineAndLike(Playlist playlist, String userId) {
        int mine = playlistDao.isMyPlaylist(playlist.getPlaylistId(), userId);
        if (mine==1) {
            playlist.setIsMine(1);
            playlist.setIsLiked(0);
            return;
        }

        playlist.setIsMine(0);
        int liked = playlistDao.isLikedPlaylist(playlist.getPlaylistId(), userId);
        if (liked==0) {
            playlist.setIsLiked(0);
        } else {
            playlist.setIsLiked(1);
        }
    }

    @Override
    public int isMyPlaylist(int playlistId, String userId) {
        return playlistDao.isMyPlaylist(playlistId, userId);
    }

    @Override
    public int isLikedPlaylist(int playlistId, String userId) {
        return playlistDao.isLikedPlaylist(playlistId, userId);
    }


}
