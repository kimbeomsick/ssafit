package com.ssafy.ssafit.model.service;

import com.ssafy.ssafit.model.dto.Playlist;
import com.ssafy.ssafit.model.dto.Video;

import java.util.List;

public interface PlaylistService {

    List<Playlist> showMyPlaylists(String userId);
    int newPlaylist(Playlist playlist);
    int modifyPlaylist(int playlistId, String playlistTitle, String content);
    int deletePlaylist(int playlistId);
    Playlist showPlaylist(int playlistId);
    List<Video> showVideosOfPlaylist(int playlistId);
    int likePlaylist(int playlistId, String userId);
    int unlikePlaylist(int playlistId, String userId);
    List<Playlist> hotPlaylist(String userId, int num);
    List<Playlist> searchPlaylist(String query, String userId, int num);
    int increaseLikeCnt(int playlistId);
    int decreaseLikeCnt(int playlistId);

    void setIsMineAndLike(Playlist playlist, String userId);

    int isMyPlaylist(int playlistId, String userId);
    int isLikedPlaylist(int playlistId, String userId);
}
