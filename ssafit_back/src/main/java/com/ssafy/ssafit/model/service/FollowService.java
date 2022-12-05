package com.ssafy.ssafit.model.service;

import com.ssafy.ssafit.model.dto.Follow;

import java.util.List;

public interface FollowService {

    //팔로워 목록(from)
    List<String> getFollowerList(String userId);

    //팔로우 목록(to)
    List<String> getFollowingList(String userId);

    //팔로우 관계 추가
    int addFollow(Follow follow);

    //팔로우 관계 삭제
    int removeFollow(Follow follow);

    //팔로우 관계가 있는지 확인
    int isFollow(Follow follow);

}
