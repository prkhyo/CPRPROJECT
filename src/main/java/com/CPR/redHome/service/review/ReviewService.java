package com.CPR.redHome.service.review;

import com.CPR.redHome.dto.review.ReviewDto;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

public interface ReviewService {

    //review 삽입
    void insertReview(ReviewDto reviewDto, MultipartFile multipartFile, HttpServletRequest request) throws IOException;

    //review_id로 리뷰 찾기
    ReviewDto selectReviewByReviewId(Long reviewId);

    //멤버 아이디에 따른 리뷰
    int countAllReviewByMemberId(Long MemberId);

    //memberId로 리뷰 가져오기
    List<ReviewDto> selectReviewByMemberId(Long memberId);

    /*<----------------효진이가 구현한 부분이라 제거 하기----------------->

    //productId의 리뷰 세기
    int countAllReviewByProductId(Long productId);

    //제품의 리뷰 다 가져오기
    List<ReviewDto> selectReviewByProductId(Long productId);
*/

    //리뷰 삭제
    void deleteReview(Long reviewId);

    //리뷰 수정
    void updateReview(ReviewDto reviewDto);



}
