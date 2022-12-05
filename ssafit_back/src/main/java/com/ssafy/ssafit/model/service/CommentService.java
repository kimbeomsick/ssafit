package com.ssafy.ssafit.model.service;

import com.ssafy.ssafit.model.dto.Comment;

import java.util.HashMap;
import java.util.List;

public interface CommentService {

    //comment 등록
    int writeComment(int parentId, int playlistId, String userId, String content);

    //comment 목록 반환 //부모와 자식 코맨트 반환함
    List<HashMap<String, Object>> getAllComments(int playlistId);

    //comment 하나 반환 -> 수정을 위해서
    Comment getCommentById(int commentId);

    //comment 수정
    int modifyComment(int commentId, String content);

    //comment 삭제
    int removeComment(int commentId);

    int increaseLikeCnt(int commentId);
    int decreaseLikeCnt(int commentId);

    int isMyComment(int commentId, String userId);

}
