package com.ssafy.ssafit.model.service;

import com.ssafy.ssafit.model.dto.Review;

import java.util.HashMap;
import java.util.List;

public interface ReviewService {
    //리뷰 등록
    void writeReview(Review review);

    //리뷰 목록
    List<Review> getReviewList(HashMap<String, String> params);

    //리뷰 조회
    Review getReview(int reviewId);

    //리뷰 수정
    int modifyReview(Review review);

    //리뷰 삭제
    void removeReview(int reviewId);

    //리뷰 좋아요


    //리뷰 싫어요



}
