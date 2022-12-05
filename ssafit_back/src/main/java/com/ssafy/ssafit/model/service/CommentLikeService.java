package com.ssafy.ssafit.model.service;

import com.ssafy.ssafit.model.dto.CommentLike;

public interface CommentLikeService {

    //comment 좋아요 누름
    int LikeComment(int commentId, String userId);

    //comment 좋아요 취소
    int UnlikeComment(int commentId, String userId);

    //comment 에 좋아요를 눌렀는지 확인
    int Liked(int commentId, String userId);
}
