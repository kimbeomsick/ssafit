package com.ssafy.ssafit.controller;

import com.ssafy.ssafit.model.dto.Follow;
import com.ssafy.ssafit.model.service.FollowService;
import com.ssafy.ssafit.response.Message;
import com.ssafy.ssafit.response.StatusEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/follows")
public class FollowRestController {

    private static final String SUCCESS = "success";
    private static final String FAIL = "fail";

    @Autowired
    private FollowService followService;

    //팔로우 등록
    @PostMapping("")
    public ResponseEntity<Message> 팔로우_추가(@RequestBody Follow follow){
        Message message = new Message();

        if(followService.isFollow(follow) == 1){ //이미 팔로우 관계에 있다면 설정함
            message.set(StatusEnum.BAD_REQUEST, "already following");
            return new ResponseEntity<>(message, HttpStatus.OK);
        }

        int result = followService.addFollow(follow);
        message.setData(follow);
        return safeReturn(result, message);
    }

    //팔로우 취소
    @DeleteMapping("")
    public ResponseEntity<Message> 팔로우_취소(@RequestBody Follow follow){
        Message message = new Message();

        if(followService.isFollow(follow) == 0){
            message.set(StatusEnum.BAD_REQUEST, "not following currently");
            return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
        }

        int result = followService.removeFollow(follow);
        message.setData(follow);
        return safeReturn(result, message);
    }


    //팔로워 목록 from
    @GetMapping("/follower")
    public ResponseEntity<Message> 팔로워_목록(String userId){
        Message message = new Message();
        List<String> list = followService.getFollowerList(userId);
        message.set(StatusEnum.OK, SUCCESS, list);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    //팔로우 목록 to
    @GetMapping("/following")
    public ResponseEntity<Message> 팔로잉_목록(String userId){
        Message message = new Message();
        List<String> list = followService.getFollowingList(userId);
        message.set(StatusEnum.OK, SUCCESS, list);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    public ResponseEntity<Message> safeReturn(int result, Message message) {
        if (result == 0) {
            message.set(StatusEnum.BAD_REQUEST, FAIL);
            return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
        }

        message.set(StatusEnum.OK, SUCCESS);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
}
