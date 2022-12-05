package com.ssafy.ssafit.model.dao;

import com.ssafy.ssafit.model.dto.Follow;

import java.util.List;

public interface FollowDao {

    //팔로잉 ->목록 등록 취소 가능
    //팔로워 ->보기만 가능

    //팔로워 목록
    List<String> selectFollowerList(String userId);

    //팔로잉 목록
    List<String> selectFollowingList(String userId);

    //팔로잉 추가
    int insertFollow(Follow follow);
    // 팔로워 삭제
    int deleteFollow(Follow follow);

    //해당 팔로우 관계가 있는지 반환
    int isFollow(Follow follow);

}
