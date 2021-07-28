package com.CPR.redHome.service.review;

import com.CPR.redHome.dto.review.ReviewHelpDto;
import com.CPR.redHome.dto.review.ReviewViewDto;
import com.CPR.redHome.mapper.review.ReviewMapper;
import com.CPR.redHome.paging.Criteria;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService{

    private final ReviewMapper reviewMapper;

    @Override
    public int selectReviewCnt(Long productId) {

        int reviewCnt = reviewMapper.selectReviewCnt(productId);

        return reviewCnt;
    }

    @Override
    public List<ReviewViewDto> selectReviewList(Long productId, int firstRecordIndex, Criteria criteria) {

        List<ReviewViewDto> reviewList = reviewMapper.selectReviewList(productId, firstRecordIndex, criteria);

        return reviewList;
    }

    @Override
    public List<Integer> selectReviewGradeList(Long productId) {

        List<Integer> reviewGradeList = reviewMapper.selectReviewGradeList(productId);

        return reviewGradeList;
    }

    @Override
    public int selectParticularGradeCnt(int reviewGrade) {

        int particularGradeCnt = reviewMapper.selectParticularGradeCnt(reviewGrade);

        return particularGradeCnt;
    }

    @Override
    public List<ReviewHelpDto> selectHelpList(Long reviewId) {

        List<ReviewHelpDto> helpList = reviewMapper.selectHelpList(reviewId);

        return helpList;
    }

    @Override
    public void updateHelpCntIncrease(Long reviewId) {

        reviewMapper.updateHelpCntIncrease(reviewId);

    }

    @Override
    public void updateHelpCntDecrease(Long reviewId) {

        reviewMapper.updateHelpCntDecrease(reviewId);

    }

    @Override
    public void insertReviewHelp(Long reviewId, Long memberId) {

        reviewMapper.insertReviewHelp(reviewId, memberId);

    }

    @Override
    public void deleteReviewHelp(Long reviewId, Long memberId) {

        reviewMapper.deleteReviewHelp(reviewId, memberId);

    }
}
