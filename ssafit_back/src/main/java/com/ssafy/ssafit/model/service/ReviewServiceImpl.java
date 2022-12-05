package com.ssafy.ssafit.model.service;

import com.ssafy.ssafit.model.dao.ReviewDao;
import com.ssafy.ssafit.model.dto.Review;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService{

    @Autowired
    private ReviewDao reviewDao;

    @Transactional
    @Override
    public void writeReview(Review review) {
        reviewDao.insertReview(review);
    }

    @Override
    public List<Review> getReviewList(HashMap<String, String> params) {
        return reviewDao.selectReviewList(params);
    }

    @Override
    public Review getReview(int reviewId){
        return reviewDao.selectReview(reviewId);
    }

    @Transactional
    @Override
    public int modifyReview(Review review) {
        return reviewDao.updateReview(review);
    }

    @Transactional
    @Override
    public void removeReview(int reviewId) {
        reviewDao.deleteReview(reviewId);
    }
}
