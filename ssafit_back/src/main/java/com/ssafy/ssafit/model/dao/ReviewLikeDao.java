package com.ssafy.ssafit.model.dao;

import com.ssafy.ssafit.model.dto.ReviewLike;

public interface ReviewLikeDao {



    //리뷰 좋아요 추가
    void insertReviewLike(ReviewLike reviewLike);

    //리뷰 좋아요 삭제
    void deleteReviewLike(ReviewLike reviewLike);

    //리뷰별 좋아요 개수 가져오기
    int selectReviewLike(int reviewId);

    //user가 좋아요 눌렀는지 확인
    int isReviewLike(ReviewLike reviewLike);



}
