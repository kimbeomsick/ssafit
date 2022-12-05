package com.ssafy.ssafit.controller;

import com.ssafy.ssafit.model.dto.Video;
import com.ssafy.ssafit.model.service.VideoService;
import com.ssafy.ssafit.model.service.YouTubeService;
import com.ssafy.ssafit.response.Message;
import com.ssafy.ssafit.response.StatusEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/api/videos")
public class VideoController {

    private static final String SUCCESS = "success";
    private static final String FAIL = "fail";

    @Autowired
    private VideoService videoService;
    @Autowired
    private YouTubeService youtubeService;

    @GetMapping("")
    public ResponseEntity<Message> DB에서_비디오_가져오기 (String videoId, String userId) {
        Message message = new Message();
        Video video = videoService.getVideo(videoId);

        if (video==null) {
            message.set(StatusEnum.BAD_REQUEST, "The video does NOT exist in DB");
            return ResponseEntity.badRequest().body(message);
        }

        int isLike = videoService.isLikedVideo(videoId, userId);
        video.setIsLike(isLike);
        message.set(StatusEnum.OK, SUCCESS, video);
        return ResponseEntity.ok().body(message);
    }

    @PostMapping("")
    public ResponseEntity<Message> DB에_비디오_저장 (@RequestBody Video video) {
        Message message = new Message();
        int exist = videoService.existsInVideo(video.getVideoId());
        if (exist==1) {
            message.set(StatusEnum.OK, "exists in DB; dont need to insert again.");
            return ResponseEntity.ok().body(message);
        }

        int result = videoService.insertVideo(video);

        if (video==null || result==0) {
            message.set(StatusEnum.BAD_REQUEST, "video is NULL or failed to insert");
            return ResponseEntity.badRequest().body(message);
        }

        message.set(StatusEnum.OK, SUCCESS, video);
        return ResponseEntity.ok().body(message);
    }

    @GetMapping("/like")
    public ResponseEntity<Message> 찜_목록 (String userId) {
        Message message = new Message();

        List<Video> list = videoService.myVideos(userId);
        for (Video video : list) {
            video.setIsLike(videoService.isLikedVideo(video.getVideoId(), userId) == 0 ? 0 : 1);
        }

        message.set(StatusEnum.OK, "returned my liked videos", list);
        return ResponseEntity.ok().body(message);
    }

    @PutMapping("/like")
    public ResponseEntity<Message> 비디오_찜_등록 (String videoId, String userId) {
        Message message = new Message();

        if (videoService.isLikedVideo(videoId, userId) > 0) {
            message.set(StatusEnum.BAD_REQUEST, "already liked video");
            return ResponseEntity.badRequest().body(message);
        }

        int result = videoService.likeVideo(videoId, userId);
        if (result == 0) {
            message.set(StatusEnum.BAD_REQUEST, "unable to insert video_like");
            return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
        }

        int result2 = videoService.increaseLikeCnt(videoId);

        HashMap<String, Integer> resp = new HashMap<>();
        try {
            resp.put("isLike", videoService.isLikedVideo(videoId, userId) > 0 ? 1 : 0);
            resp.put("likeCnt", videoService.getVideo(videoId).getLikeCnt());

            message.set(StatusEnum.OK, "updated likeCnt and isLike", resp);
        } catch (Exception e) {
            message.set(StatusEnum.BAD_REQUEST, "Error caught", e);
            return ResponseEntity.badRequest().body(message);
        }

        return ResponseEntity.ok().body(message);
    }

    @DeleteMapping("/like")
    public ResponseEntity<Message> 비디오_찜_해제 (String videoId, String userId) {
        Message message = new Message();

        // DB에 존재하지 않으면 에러
        if (videoService.isLikedVideo(videoId, userId) == 0) {
            message.set(StatusEnum.BAD_REQUEST, "non-existing or non-liked video");
            return ResponseEntity.badRequest().body(message);
        }

        int result = videoService.unlikeVideo(videoId, userId);
        if (result == 0) {
            message.set(StatusEnum.BAD_REQUEST, "FAIL: delete from video_like", videoId);
            return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
        }

        //video.setIsLike(0);

        int result2 = videoService.decreaseLikeCnt(videoId);

        HashMap<String, Integer> resp = new HashMap<>();
        try {
            resp.put("isLike", videoService.isLikedVideo(videoId, userId) > 0 ? 1 : 0);
            resp.put("likeCnt", videoService.getVideo(videoId).getLikeCnt());

            message.set(StatusEnum.OK, "updated likeCnt and isLike", resp);
        } catch (Exception e) {
            message.set(StatusEnum.BAD_REQUEST, "Error caught", e);
            return ResponseEntity.badRequest().body(message);
        }

        return ResponseEntity.ok().body(message);
    }


    @GetMapping("/search/{query}")
    public ResponseEntity<Message> 유튜브_검색결과(@PathVariable String query, int num, String userId) {
        Message message = new Message();
        try {
            List<Video> list = youtubeService.getYouTubeList(query, num);
            for (Video video : list) {
                video.setIsLike(videoService.isLikedVideo(video.getVideoId(), userId));
            }

            message.set(StatusEnum.OK, SUCCESS, list);
            return ResponseEntity.ok().body(message);
        } catch (Exception e) {
            message.set(StatusEnum.BAD_REQUEST, FAIL, e);
            return ResponseEntity.badRequest().body(message);
        }
    }

    @GetMapping("/search/hot")
    public ResponseEntity<Message> 인기_비디오(@RequestParam(required = false,
            defaultValue = "4") Integer num,
                                          String userId) {
        Message message = new Message();
        List<Video> list = videoService.recommendVideos(num);
        for (Video video : list) {
            video.setIsLike(videoService.isLikedVideo(video.getVideoId(), userId));
        }

        message.set(StatusEnum.OK, SUCCESS, list);
        return ResponseEntity.ok().body(message);
    }

    @PutMapping("/videos/playlist")
    public ResponseEntity<Message> 플레이리스트에_비디오_삽입( String videoId, int playlistId) {
        System.out.println("videoId : "+videoId);
        System.out.println("playlistId : "+playlistId);
        Message message = new Message();

        if (videoService.existsInVideo(videoId) == 0) {
            message.set(StatusEnum.BAD_REQUEST, "Non-existing video");
            return ResponseEntity.badRequest().body(message);
        }

        if (videoService.existsInVideoInPlaylist(videoId, playlistId) == 1) {
            message.set(StatusEnum.BAD_REQUEST, "The video is already in the playlist");
            return ResponseEntity.badRequest().body(message);
        }

        int result = videoService.insertVideoIntoPlaylist(videoId, playlistId);
        if (result == 0) {
            message.set(StatusEnum.BAD_REQUEST, FAIL, videoId);
            return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
        }

        message.set(StatusEnum.OK, SUCCESS, videoId);
        return ResponseEntity.ok().body(message);
    }

    @DeleteMapping("/playlist")
    public ResponseEntity<Message> 플레이리스트에서_비디오_삭제(String videoId, int playlistId) {
        Message message = new Message();

        if (videoService.existsInVideo(videoId) == 0) {
            message.set(StatusEnum.BAD_REQUEST, "Non-existing video");
            return ResponseEntity.badRequest().body(message);
        }

        if(videoService.existsInVideoInPlaylist(videoId, playlistId) == 0) {
            message.set(StatusEnum.BAD_REQUEST, "The video is NOT in the playlist");
            return ResponseEntity.badRequest().body(message);
        }

        int result = videoService.deleteVideoFromPlaylist(videoId, playlistId);
        if (result == 0) {
            message.set(StatusEnum.BAD_REQUEST, FAIL);
            return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
        }

        message.set(StatusEnum.OK, SUCCESS);
        return ResponseEntity.ok().body(message);
    }
}
