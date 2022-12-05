package com.ssafy.ssafit.model.dao;

import com.ssafy.ssafit.model.dto.Bookmark;

import java.util.List;


//like bookmark 기능을 모두 포함함
public interface BookmarkDao {

    //북마크 목록 가져오기
    List<Bookmark> selectBookmarkList(String userId);

    //북마크 추가
    void insertBookmark(Bookmark bookmark);

    //북마크 삭제
    void deleteBookmark(Bookmark bookmark); //userId랑 boomarkid 만들어서 넘김

    //좋아요
    void addLike(Bookmark bookmark);


    //좋아요 취소
    void cancel(Bookmark bookmark);
}
