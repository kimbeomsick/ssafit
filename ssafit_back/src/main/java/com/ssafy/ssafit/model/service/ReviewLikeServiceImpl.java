package com.ssafy.ssafit.model.service;


import com.ssafy.ssafit.model.dao.ReviewLikeDao;
import com.ssafy.ssafit.model.dto.ReviewLike;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ReviewLikeServiceImpl implements ReviewLikeService{

    @Autowired
    private ReviewLikeDao reviewLikeDao;

    @Transactional
    @Override
    public void addReviewLike(ReviewLike reviewLike) {
        reviewLikeDao.insertReviewLike(reviewLike);
    }

    @Transactional
    @Override
    public void removeReviewLike(ReviewLike reviewLike) {
        reviewLikeDao.deleteReviewLike(reviewLike);
    }

    @Override
    public int getReviewLike(int reviewId) {
        int result = reviewLikeDao.selectReviewLike(reviewId);
        return result;
    }

    @Override
    public int isReviewLike(ReviewLike reviewLike) {

        return reviewLikeDao.isReviewLike(reviewLike);

    }
}
