package com.ssafy.ssafit.controller;

import com.ssafy.ssafit.model.dto.User;
import com.ssafy.ssafit.model.service.UserService;
import com.ssafy.ssafit.response.Message;
import com.ssafy.ssafit.response.StatusEnum;
import com.ssafy.ssafit.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private static final String SUCCESS = "success";
    private static final String FAIL = "fail";
    private static final String HEADER_AUTH = "access-token";

    @Autowired
    private UserService userService;
    @Autowired
    private JwtUtil jwtUtil;


    // 로그인 요청
    @PostMapping("/login") //user
    public ResponseEntity<?> 로그인(String userId, String userPassword) throws UnsupportedEncodingException {
        HashMap<String, Object> body = new HashMap<>();

        Message message = new Message();

        if (userService.loginCheck(userId, userPassword) == 0) {
            message.set(StatusEnum.BAD_REQUEST, "invalid ID or password");
            body.put("message", message);
            return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
        }

        message.set(StatusEnum.OK, SUCCESS, userService.getUser(userId));
        body.put("message", message);
        body.put(HEADER_AUTH, jwtUtil.createToken("id", userId));

        return new ResponseEntity<>(body, HttpStatus.OK);
    }

    // 아이디 중복체크
    // 중복체크 버튼을 누를 때 1번, 회원가입 버튼을 누를 때도 1번 실행 되어야 함 (중복체크 버튼에서 성공해도 회원가입 버튼을 누르기 전에 다른 사용자가 같은 아이디로 가입할 가능성)
    @GetMapping("/auth/{userId}")
    public ResponseEntity<Message> 아이디_중복체크(@PathVariable String userId) {
        Message message = new Message();

        if (userService.overlappedID(userId) == 1) {
            message.set(StatusEnum.CONFLICT, "The ID already exists", userId);
            return new ResponseEntity<>(message, HttpStatus.CONFLICT);
        }

        message.set(StatusEnum.OK, "available ID", userId);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    // 회원가입
    @PostMapping("/auth")
    public ResponseEntity<Message> 회원가입(@RequestBody User user) {
        Message message = new Message();

        if (userService.overlappedID(user.getUserId()) == 1) {
            message.set(StatusEnum.CONFLICT, "Please check your ID", user.getUserId());
            return new ResponseEntity<>(message, HttpStatus.CONFLICT);
        }

        userService.join(user);
        message.set(StatusEnum.CREATED, SUCCESS, user);
        return new ResponseEntity<>(message, HttpStatus.CREATED);
    }

    // 특정 유저 조회
    @GetMapping("/{userId}")
    public ResponseEntity<Message> 아이디로_유저_조회(@PathVariable String userId) {
        Message message = new Message();

        User user = userService.getUser(userId);
        if (user == null) {
            message.set(StatusEnum.BAD_REQUEST, "id does NOT exist");
            return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
        }

        message.set(StatusEnum.OK, SUCCESS, user);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    // 특정 유저 정보 수정
    @PutMapping("/{userId}")
    public ResponseEntity<Message> 개인정보_수정(@PathVariable String userId, String newName, String newPassword) {
        Message message = new Message();

        HashMap<String, Object> m = new HashMap<>();
        m.put("userId", userId);
        m.put("newName", newName);
        m.put("newPassword", newPassword);

        int result = userService.updateUser(m);
        if (result == 0) {
            message.set(StatusEnum.BAD_REQUEST, FAIL);
            return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
        }

        message.set(StatusEnum.OK, SUCCESS, userService.getUser(userId));
        return new ResponseEntity<>(message, HttpStatus.OK);
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Message> 회원탈퇴(@PathVariable String userId) {
        Message message = new Message();

        int result = userService.quit(userId);
        if (result == 0) {
            message.set(StatusEnum.BAD_REQUEST, FAIL);
            return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
        }

        message.set(StatusEnum.OK, SUCCESS);
        return new ResponseEntity<>(message, HttpStatus.OK);
    }
}
