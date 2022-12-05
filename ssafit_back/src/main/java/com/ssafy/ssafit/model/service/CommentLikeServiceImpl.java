package com.ssafy.ssafit.model.service;

import com.ssafy.ssafit.model.dao.CommentLikeDao;
import com.ssafy.ssafit.model.dto.CommentLike;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentLikeServiceImpl implements CommentLikeService{

    @Autowired
    private CommentLikeDao commentLikeDao;

    @Override
    public int LikeComment(int commentId, String userId) {
        return commentLikeDao.insertCommentLike(commentId, userId);
    }

    @Override
    public int UnlikeComment(int commentId, String userId) {
        return commentLikeDao.deleteCommentLike(commentId, userId);
    }

    @Override
    public int Liked(int commentId, String userId) {
        return commentLikeDao.existsCommentLike(commentId, userId);
    }

}
