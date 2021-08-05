package com.CPR.redHome.service.review;

import com.CPR.redHome.dto.review.ReviewHelpDto;
import com.CPR.redHome.dto.review.ReviewViewDto;
import com.CPR.redHome.paging.Criteria;
import org.springframework.web.bind.annotation.RequestParam;
import com.CPR.redHome.dto.review.ReviewDto;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

public interface ReviewService {

    int selectReviewCnt(Long productId);

    List<ReviewViewDto> selectReviewList(Long productId, int firstRecordIndex, Criteria criteria, String reviewSort);

    int selectParticularGradeCnt(int reviewGrade, Long productId);

    List<ReviewHelpDto> selectHelpList(Long reviewId);

    void updateHelpCntIncrease(Long reviewId);

    void updateHelpCntDecrease(Long reviewId);

    void insertReviewHelp(Long reviewId, Long memberId);

    void deleteReviewHelp(Long reviewId, Long memberId);
    //review 삽입
    void insertReview(ReviewDto reviewDto, MultipartFile multipartFile, HttpServletRequest request) throws IOException;

    //review_id로 리뷰 찾기
    ReviewDto selectReviewByReviewId(Long reviewId);

    //멤버 아이디에 따른 리뷰
    int countAllReviewByMemberId(Long MemberId);

    //memberId로 리뷰 가져오기
    List<ReviewDto> selectReviewByMemberId(Long memberId);

    //리뷰 삭제
    void deleteReview(Long reviewId);

    //리뷰 수정
    void updateReview(ReviewDto reviewDto);



}
