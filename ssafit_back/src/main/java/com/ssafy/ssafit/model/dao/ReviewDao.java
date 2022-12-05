package com.ssafy.ssafit.model.dao;

import com.ssafy.ssafit.model.dto.Review;

import java.util.HashMap;
import java.util.List;

public interface ReviewDao {

    //리뷰 목록 가져오기
    List<Review> selectReviewList(HashMap<String,String> params); // keyword videoId,  node=날짜순 별점순

    //리뷰 가져오기
    Review selectReview(int reviewId);

    //리뷰 추가
    void insertReview(Review review);

    //리뷰 삭제
    int deleteReview(int reviewId);

    //리뷰 수정
    int updateReview(Review review);


}
