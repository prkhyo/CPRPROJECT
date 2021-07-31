package com.CPR.redHome.mapper.review;

import com.CPR.redHome.dto.review.ReviewDto;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ReviewMapper {

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

    //제품의 리뷰 다 가져오기
    List<ReviewDto> selectReviewByProductId(Long productId);

    //리뷰 삭제
    void deleteReview(Long reviewId);

    //리뷰 수정
    void updateReview(ReviewDto reviewDto);


}
