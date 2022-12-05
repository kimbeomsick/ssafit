package com.ssafy.ssafit.model.dao;

import com.ssafy.ssafit.model.dto.CommentLike;

public interface CommentLikeDao {

    //comment 좋아요
    int insertCommentLike(int commentId, String userId);


    //comment 좋아요 취소
    int deleteCommentLike(int commentId, String userId);


    //comment 좋아요 여부
    int existsCommentLike(int commentId, String userId);

}
