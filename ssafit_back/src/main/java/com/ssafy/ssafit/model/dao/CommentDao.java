package com.ssafy.ssafit.model.dao;

import com.ssafy.ssafit.model.dto.Comment;

import java.util.HashMap;
import java.util.List;

public interface CommentDao {

    //부모 comment 반환 (where parent_id = -1 )
    //String userId, int parentId
    List<Comment> selectParentComments(int playlistId);


    //자식 Comment 반환
    //int parentId, String userId
    List<Comment> selectChildComments(int parentId);

    // Create
    int insertComment(int parentId, int playlistId, String userId, String content);

    // Read
    Comment selectCommentById(int commentId);

    // Update
    int updateComment(int commentId, String content);

    // Delete
    int deleteComment(int commentId);
    int increaseLikeCnt(int commentId);
    int decreaseLikeCnt(int commentId);

    int isMyComment(int commentId, String userId);
    int isLikedComment(int commentId, String userId);
}
