package com.ssafy.ssafit.controller;

import com.ssafy.ssafit.model.dto.Comment;
import com.ssafy.ssafit.model.dto.Playlist;
import com.ssafy.ssafit.model.dto.Video;
import com.ssafy.ssafit.model.service.CommentService;
import com.ssafy.ssafit.model.service.PlaylistService;
import com.ssafy.ssafit.response.Message;
import com.ssafy.ssafit.response.StatusEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/playlists")
public class PlaylistController {
    private static final String SUCCESS = "success";
    private static final String FAIL = "fail";

    @Autowired
    private PlaylistService playlistService;

    @Autowired
    private CommentService commentService;

    @GetMapping("/my")
    public ResponseEntity<Message> 내_플레이리스트들 (String userId) {
        Message message = new Message();

        List<Playlist> list = playlistService.showMyPlaylists(userId);
        for (Playlist playlist : list) {
            playlistService.setIsMineAndLike(playlist, userId);
        }

        message.set(StatusEnum.OK, SUCCESS, list);
        return ResponseEntity.ok().body(message);
    }

    @PostMapping("/my")
    public ResponseEntity<Message> 새_플레이리스트_생성 (@RequestBody Playlist playlist) {
        Message message = new Message();
        System.out.println(playlist);
        int result = playlistService.newPlaylist(playlist);
        if (result==0) {
            message.set(StatusEnum.BAD_REQUEST, FAIL);
            return ResponseEntity.badRequest().body(message);
        }

        message.set(StatusEnum.OK, SUCCESS);
        return ResponseEntity.ok().body(message);
    }

    @PutMapping("/my")
    public ResponseEntity<Message> 내_플레이리스트_메타데이터_수정 (int playlistId, String playlistTitle, String content, String userId) {
        Message message = new Message();
        if (playlistService.isMyPlaylist(playlistId, userId) == 0) {
            message.set(StatusEnum.BAD_REQUEST, "The playlist does NOT exist or not yours; cannot edit");
            return ResponseEntity.badRequest().body(message);
        }

        int result = playlistService.modifyPlaylist(playlistId, playlistTitle, content);
        if (result==0) {
            message.set(StatusEnum.BAD_REQUEST, FAIL);
            return ResponseEntity.badRequest().body(message);
        }

        message.set(StatusEnum.OK, SUCCESS);
        return ResponseEntity.ok().body(message);
    }

    @DeleteMapping("/my")
    public ResponseEntity<Message> 내_플레이리스트_삭제 (int playlistId, String userId) {
        Message message = new Message();
        if (playlistService.isMyPlaylist(playlistId, userId) == 0) {
            message.set(StatusEnum.BAD_REQUEST, "The playlist does NOT exist or not yours; cannot remove");
            return ResponseEntity.badRequest().body(message);
        }

        int result = playlistService.deletePlaylist(playlistId);
        if (result==0) {
            message.set(StatusEnum.BAD_REQUEST, FAIL);
            return ResponseEntity.badRequest().body(message);
        }

        message.set(StatusEnum.OK, SUCCESS);
        return ResponseEntity.ok().body(message);
    }

    @GetMapping("/{playlistId}")
    public ResponseEntity<Message> 특정_플레이리스트_보기 (@PathVariable int playlistId, String userId) {
        Message message = new Message();

        Playlist playlist = playlistService.showPlaylist(playlistId);
        playlistService.setIsMineAndLike(playlist, userId);

        if (playlist==null) {
            message.set(StatusEnum.BAD_REQUEST, "playlistId does not exist");
            return ResponseEntity.badRequest().body(message);
        }

        Map<String, Object> map = new HashMap<>();
        map.put("playlist_info", playlist);

        List<Video> list = new ArrayList<>();
        list = playlistService.showVideosOfPlaylist(playlistId);
        map.put("Videos", list);


        message.set(StatusEnum.OK, SUCCESS, map);
        return ResponseEntity.ok().body(message);
    }

    @PutMapping("/like/{playlistId}")
    public ResponseEntity<Message> 플레이리스트_찜_등록 (@PathVariable int playlistId, String userId) {
        Message message = new Message();

        Playlist playlist = playlistService.showPlaylist(playlistId);
        // 내 플레이리스트거나 이미 찜한 플레이리스트면 오류
        if (playlistService.isMyPlaylist(playlistId, userId) == 1
                || playlistService.isLikedPlaylist(playlistId, userId) == 1) {
            message.set(StatusEnum.BAD_REQUEST, "my playlist or already liked playlist");
            return ResponseEntity.badRequest().body(message);
        }

        int result1 = playlistService.likePlaylist(playlistId, userId);
        int result2 = playlistService.increaseLikeCnt(playlistId);
        if (result1 == 0 || result2 == 0) {
            message.set(StatusEnum.BAD_REQUEST, FAIL);
            return ResponseEntity.badRequest().body(message);
        }
        message.set(StatusEnum.OK, SUCCESS, playlist);
        return ResponseEntity.ok().body(message);
    }

    @DeleteMapping("/like/{playlistId}")
    public ResponseEntity<Message> 플레이리스트_찜_해제 (@PathVariable int playlistId, String userId) {
        Message message = new Message();

        Playlist playlist = playlistService.showPlaylist(playlistId);
        // 내 플레이리스트거나 찜하지 않은 플레이리스트면 오류
        if (playlistService.isMyPlaylist(playlistId, userId) == 1
                || playlistService.isLikedPlaylist(playlistId, userId) == 0) {
            message.set(StatusEnum.BAD_REQUEST, "my playlist or non-liked playlist");
            return ResponseEntity.badRequest().body(message);
        }

        int result1 = playlistService.unlikePlaylist(playlistId, userId);
        int result2 = playlistService.decreaseLikeCnt(playlistId);
        if (result1 == 0 || result2 == 0) {
            message.set(StatusEnum.BAD_REQUEST, FAIL);
            return ResponseEntity.badRequest().body(message);
        }
        message.set(StatusEnum.OK, SUCCESS, playlist);
        return ResponseEntity.ok().body(message);
    }

    @GetMapping("/search/hot")
    public ResponseEntity<Message> 추천_플레이리스트 (String userId, @RequestParam(
                                                                required = false
                                                                ) int num) {
        Message message = new Message();

        List<Playlist> list = playlistService.hotPlaylist(userId, num);
        message.set(StatusEnum.OK, SUCCESS, list);
        return ResponseEntity.ok().body(message);
    }

    @GetMapping("/search/{query}")
    public ResponseEntity<Message> 플레이리스트_검색결과 (@PathVariable String query, String userId, @RequestParam(required = false) int num) {
        Message message = new Message();
        List<Playlist> list = playlistService.searchPlaylist(query, userId, num);
        message.set(StatusEnum.OK, SUCCESS, list);
        return ResponseEntity.ok().body(message);
    }
}
