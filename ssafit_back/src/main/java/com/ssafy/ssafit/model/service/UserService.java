package com.ssafy.ssafit.model.service;

import com.ssafy.ssafit.model.dto.User;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;

public interface UserService {


    // 회원가입
    int join(User user);

    // 로그인
    int loginCheck(String id, String password);

    int overlappedID(String id);

    // 특정 유저 조회
    User getUser(String id);

    // 비밀번호, 이름 변경
    int updateUser(HashMap<String, Object> m);

    // 회원 탈퇴
    int quit(String id);

}
