package com.CPR.redHome.service.review;

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
}
