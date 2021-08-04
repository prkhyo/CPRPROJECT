package com.CPR.redHome.mapper.review;

import com.CPR.redHome.dto.review.ReviewHelpDto;
import com.CPR.redHome.dto.review.ReviewViewDto;
import com.CPR.redHome.paging.Criteria;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.web.bind.annotation.RequestParam;
import com.CPR.redHome.dto.review.ReviewDto;


import java.util.List;

@Mapper
public interface ReviewMapper {
    
    
    //특정 제품에 대한 총 리뷰 수 가져오기
    int selectReviewCnt(Long productId);

    //특정 제품에 대한 리뷰 리스트 가져오기
    List<ReviewViewDto> selectReviewList(@RequestParam Long productId, @RequestParam int firstRecordIndex,
                                         @RequestParam Criteria criteria, @RequestParam String reviewSort);


    //특정 제품에 대한 특정 리뷰 점수의 수 가져오기
    int selectParticularGradeCnt(int reviewGrade, Long productId);

    //특정 리뷰에 대해 로그인유저가 좋아요(=도움됨)한 리스트 가져오기
    List<ReviewHelpDto> selectHelpList(Long reviewId);

    //특정 리뷰에 대한 좋아요 증가
    void updateHelpCntIncrease(Long reviewId);

    //특정 리뷰에 대한 좋아요 생성
    void insertReviewHelp(@RequestParam Long reviewId, @RequestParam Long memberId);


    //특정 리뷰에 대한 좋아요 감소
    void updateHelpCntDecrease(Long reviewId);

    //특정 리뷰에 대한 좋아요 삭제
    void deleteReviewHelp(@RequestParam Long reviewId, @RequestParam Long memberId);


    //review 삽입
    void insertReview(ReviewDto reviewDto);

    //review_id로 리뷰 찾기
    ReviewDto selectReviewByReviewId(Long reviewId);

    //멤버 아이디에 따른 리뷰
    int countAllReviewByMemberId(Long MemberId);

    //memberId로 리뷰 가져오기
    List<ReviewDto> selectReviewByMemberId(Long memberId);

    //productId의 리뷰 세기
    int countAllReviewByProductId(Long productId);

    //리뷰 삭제
    void deleteReview(Long reviewId);

    //리뷰 수정
    void updateReview(ReviewDto reviewDto);



}
