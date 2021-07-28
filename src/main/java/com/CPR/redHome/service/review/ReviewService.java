package com.CPR.redHome.service.review;

import com.CPR.redHome.dto.review.ReviewHelpDto;
import com.CPR.redHome.dto.review.ReviewViewDto;
import com.CPR.redHome.paging.Criteria;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface ReviewService {

    int selectReviewCnt(Long productId);

    List<ReviewViewDto> selectReviewList(Long productId, int firstRecordIndex, Criteria criteria);

    List<Integer> selectReviewGradeList(Long productId);

    int selectParticularGradeCnt(int reviewGrade);

    List<ReviewHelpDto> selectHelpList(Long reviewId);

    void updateHelpCntIncrease(Long reviewId);

    void updateHelpCntDecrease(Long reviewId);

    void insertReviewHelp(Long reviewId, Long memberId);

    void deleteReviewHelp(Long reviewId, Long memberId);

}
