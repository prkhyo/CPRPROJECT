package com.CPR.redHome.service.review;


import com.CPR.redHome.dto.review.ReviewDto;
import com.CPR.redHome.mapper.review.ReviewMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService{

    private final ReviewMapper reviewMapper;

    @Override
    public void insertReview(ReviewDto reviewDto) {
        reviewMapper.insertReview(reviewDto);
    }

    @Override
    public ReviewDto selectReviewByReviewId(Long reviewId) {
        return reviewMapper.selectReviewByReviewId(reviewId);
    }

    @Override
    public int countAllReviewByMemberId(Long memberId) {
        return reviewMapper.countAllReviewByMemberId(memberId);
    }

    @Override
    public List<ReviewDto> selectReviewByMemberId(Long memberId) {
        return reviewMapper.selectReviewByMemberId(memberId);
    }

    @Override
    public int countAllReviewByProductId(Long productId) {
        return reviewMapper.countAllReviewByProductId(productId);
    }

    @Override
    public List<ReviewDto> selectReviewByProductId(Long productId) {
        return null;
    }

    @Override
    public void deleteReview(Long reviewId) {

    }

    @Override
    public void updateReview(ReviewDto reviewDto) {

    }
}
