package com.ssafy.ssafit.model.service;

import com.ssafy.ssafit.model.dao.CommentDao;
import com.ssafy.ssafit.model.dao.CommentLikeDao;
import com.ssafy.ssafit.model.dto.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentService{

    @Autowired
    private CommentDao commentDao;

    @Autowired
    private CommentLikeDao commentLikeDao;

    @Override
    public List<HashMap<String, Object>> getAllComments(int playlistId) {
        List<HashMap<String,Object>> result = new ArrayList<>();

        //부모 코맨트 먼저 가져와서
        List<Comment> parentCommentList = commentDao.selectParentComments(playlistId); // xml에서 HashMap을 사용하기 위해 map형태로 넣음

        for(Comment parent : parentCommentList){
            //부모코멘트에 종속되는 자식 코멘트 반환
            List<Comment> children = commentDao.selectChildComments(parent.getCommentId()); // xml에서 HashMap을 사용하기 위해 map형태로 넣음

            HashMap<String, Object> comment = new HashMap<>();
            comment.put("parent", parent); // 부모 comment
            comment.put("children", children); // 자식 comments

            result.add(comment);
        }
        //부모 + 자식 리시트 반환함
        return result;
    }

    @Transactional
    @Override //쓰기
    public int writeComment(int parentId, int playlistId, String userId, String content) {
        return commentDao.insertComment(parentId, playlistId, userId, content);
    }

    @Override
    public Comment getCommentById(int commentId) {
        return commentDao.selectCommentById(commentId);
    }

    @Transactional
    @Override
    public int modifyComment(int commentId, String content) {
        return commentDao.updateComment(commentId, content);
    }

    @Transactional
    @Override
    public int removeComment(int commentId) {
        return commentDao.deleteComment(commentId);
    }

    @Transactional
    @Override
    public int increaseLikeCnt(int commentId) {
        return commentDao.increaseLikeCnt(commentId);
    }

    @Transactional
    @Override
    public int decreaseLikeCnt(int commentId) {
        return commentDao.decreaseLikeCnt(commentId);
    }

    @Override
    public int isMyComment(int commentId, String userId) {
        return commentDao.isMyComment(commentId, userId);
    }

}
