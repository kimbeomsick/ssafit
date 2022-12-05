package com.ssafy.ssafit.model.dao;

import com.ssafy.ssafit.model.dto.Playlist;
import com.ssafy.ssafit.model.dto.Video;

import java.util.List;

public interface PlaylistDao {

    List<Playlist> getMyOwnPlaylists(String userId);
    List<Integer> getMyLikedPlaylists(String userId);
    int insertPlaylist(Playlist playlist);
    int updatePlaylist(int playlistId, String playlistTitle, String content);
    int deletePlaylist(int playlistId);
    Playlist getPlaylistOne(int playlistId);
    List<Video> selectVideosOfPlaylist(int playlistId);
    int likePlaylist(int playlistId, String userId);
    int unlikePlaylist(int playlistId, String userId);
    List<Playlist> hotPlaylist(String userId, int num);
    List<Playlist> searchPlaylist(String query, String userId, int num);
    int increaseLikeCnt(int playlistId);
    int decreaseLikeCnt(int playlistId);

    int isMyPlaylist(int playlistId, String userId);
    int isLikedPlaylist(int playlistId, String userId);
}
