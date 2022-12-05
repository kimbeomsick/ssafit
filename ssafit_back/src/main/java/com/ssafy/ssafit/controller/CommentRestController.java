package com.ssafy.ssafit.controller;

import com.ssafy.ssafit.model.dto.Comment;
import com.ssafy.ssafit.model.dto.CommentLike;
import com.ssafy.ssafit.model.service.CommentLikeService;
import com.ssafy.ssafit.model.service.CommentService;
import com.ssafy.ssafit.model.service.PlaylistService;
import com.ssafy.ssafit.response.Message;
import com.ssafy.ssafit.response.StatusEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/api/comments")
public class CommentRestController {
    private static final String SUCCESS = "success";
    private static final String FAIL = "fail";

    @Autowired
    private CommentService commentService;
    @Autowired
    private CommentLikeService commentLikeService;

    @Autowired
    private PlaylistService playlistService;

    //Comment 작성
    @PostMapping("")
    public ResponseEntity<Message> 코멘트_작성(String content, String userId,
                                          @RequestParam(defaultValue = "-1") Integer parentId,
                                          int playlistId){
        Message message = new Message();

        if(commentService.writeComment(parentId, playlistId, userId, content) == 0){
            message.set(StatusEnum.BAD_REQUEST, FAIL);
            return ResponseEntity.badRequest().body(message);
        }

        message.set(StatusEnum.CREATED, SUCCESS);
        return new ResponseEntity<>(message, HttpStatus.CREATED);
    }

    //Comment 수정
    @PutMapping("")
    public ResponseEntity<Message> 코멘트_수정(int commentId, String content, String userId){
        Message message = new Message();

        if (commentService.isMyComment(commentId, userId) == 0) {
            message.set(StatusEnum.BAD_REQUEST, "commentId is not mine or doesn't exist");
            return ResponseEntity.badRequest().body(message);
        }

        int result = commentService.modifyComment(commentId, content);
        if (result == 0) {
            message.set(StatusEnum.BAD_REQUEST, "edit failed", commentId);
            return ResponseEntity.badRequest().body(message);
        }

        message.set(StatusEnum.OK, SUCCESS, commentService.getCommentById(commentId));
        return ResponseEntity.ok().body(message);
    }

    //Comment 삭제
    @DeleteMapping("")
    public ResponseEntity<Message> 코멘트_삭제(int commentId, String userId) {
        Message message = new Message();

        if (commentService.isMyComment(commentId, userId) == 0) {
            message.set(StatusEnum.BAD_REQUEST, "commentId is not mine or doesn't exist");
            return ResponseEntity.badRequest().body(message);
        }

        int result = commentService.removeComment(commentId);
        if (result == 0) {
            message.set(StatusEnum.BAD_REQUEST, "delete failed");
            return ResponseEntity.badRequest().body(message);
        }

        message.set(StatusEnum.OK, SUCCESS, 1);
        return ResponseEntity.ok().body(message);
    }

    //Comment 목록 반환
    @GetMapping("") // params로 보내야 알잘딱으들어간다. data로 보낼거면 hashmap 사용해야함
    public ResponseEntity<Message> 전체_댓글_반환(int playlistId, String userId){
        Message message = new Message();

        try {
            List<HashMap<String, Object>> res = commentService.getAllComments(playlistId);

            for (HashMap<String, Object> comment : res) {
                Comment parent = (Comment) comment.get("parent");
                setIsMineAndIsLike(parent, userId);

                List<Comment> children = (List<Comment>) comment.get("children");
                for (Comment child : children) {
                    setIsMineAndIsLike(child, userId);
                }
            }

            message.set(StatusEnum.OK, SUCCESS, res);
            return ResponseEntity.ok().body(message);
        } catch (Exception e) {
            message.set(StatusEnum.BAD_REQUEST, FAIL, e);
            return ResponseEntity.badRequest().body(message);
        }
    }

//    @GetMapping("/comment/{commentId}") // 알아서 잘 들어올까..?
//    public ResponseEntity<Message> 대댓글_반환(@PathVariable int commentId, String userId){
//        Message message = new Message();
//
//        Comment result =  commentService.getComment(commentId);
//        message.set(StatusEnum.OK, SUCCESS, result);
//
//        return ResponseEntity.ok().body(message);
//    }

    //Comment 좋아요
    @PostMapping("/like")
    public ResponseEntity<Message> Comment_좋아요_등록(int commentId, String userId) {
        Message message = new Message();

        if(commentLikeService.Liked(commentId, userId) > 0){ // 이미 좋아요가 있다면
            message.set(StatusEnum.BAD_REQUEST, "already liked comment");
            return ResponseEntity.badRequest().body(message);// 실패
        }

        int result1 = commentLikeService.LikeComment(commentId, userId); // 좋아요 생성
        int result2 = commentService.increaseLikeCnt(commentId);
        if (result1 == 0 || result2 == 0) {
            message.set(StatusEnum.BAD_REQUEST, FAIL);
            return ResponseEntity.badRequest().body(message);
        }

        message.set(StatusEnum.OK, SUCCESS);
        return ResponseEntity.ok().body(message);
    }

    //Comment 좋아요 취소
    @DeleteMapping("/like")
    public ResponseEntity<Message> Comment_좋아요_취소(int commentId, String userId) {
        Message message = new Message();
        if(commentLikeService.Liked(commentId, userId) == 0){ // 좋아요가 없다면
            message.set(StatusEnum.BAD_REQUEST, "not liked comment");
            return ResponseEntity.badRequest().body(message);
        }

        // 좋아요가 있다면
        int result1 = commentLikeService.UnlikeComment(commentId, userId);
        int result2 = commentService.decreaseLikeCnt(commentId);
        if (result1 == 0 || result2 == 0) {
            message.set(StatusEnum.BAD_REQUEST, FAIL);
            return ResponseEntity.badRequest().body(message);
        }

        message.set(StatusEnum.OK, SUCCESS);
        return ResponseEntity.ok().body(message);
    }

    public void setIsMineAndIsLike(Comment comment, String userId) {
        if (commentService.isMyComment(comment.getCommentId(), userId) == 0) {
            comment.setIsMine(0);
        } else {
            comment.setIsMine(1);
        }

        if (commentLikeService.Liked(comment.getCommentId(), userId) == 0) {
            comment.setIsLike(0);
        } else {
            comment.setIsLike(1);
        }
    }
}
