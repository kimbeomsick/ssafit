package com.ssafy.ssafit.model.service;

import com.ssafy.ssafit.model.dto.Bookmark;

import java.util.List;

public interface BookmarkService {
    //북마크 목록
    List<Bookmark> getBookmarkList(String userId);

    //북마크 추가
    void addBookmark(Bookmark bookmark);

    //북마크 제거
    void removeBookmark(Bookmark bookmark);


}
