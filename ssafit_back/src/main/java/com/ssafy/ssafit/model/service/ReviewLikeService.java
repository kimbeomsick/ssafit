package com.ssafy.ssafit.model.service;

import com.ssafy.ssafit.model.dto.ReviewLike;

public interface ReviewLikeService {

    //리뷰 좋아요
    void addReviewLike(ReviewLike reviewLike);

    //리뷰 좋아요 삭제
    void removeReviewLike(ReviewLike reviewLike);

    //리뷰 좋아요 개수 가져오기
    int getReviewLike(int reviewId);

    //리뷰에 좋아요 눌렀는지 확인 0 or 1 반환
    int isReviewLike(ReviewLike reviewLike);


}
