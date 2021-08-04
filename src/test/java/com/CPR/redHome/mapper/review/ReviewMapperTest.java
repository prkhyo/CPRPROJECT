package com.CPR.redHome.mapper.review;

import com.CPR.redHome.dto.review.ReviewDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class ReviewMapperTest {

    @Autowired
    ReviewMapper reviewMapper;



    @Test
    @DisplayName("리뷰를 reviewId 통해 조회합니다.")
    public void selectByReviewIdTest() {

        //given
        Long reviewId = 1L;

        //when
        ReviewDto review = reviewMapper.selectReviewByReviewId(reviewId);

        //then
        System.out.println("review = " + review);


    }

    @Test
    @DisplayName("리뷰를 넣습니다.")
    public void insertTest() {

        //given
        ReviewDto reviewDto = new ReviewDto();
        reviewDto.setReviewGrade(3);
        reviewDto.setReviewImg("사진");
        reviewDto.setReviewCreatedDate(LocalDateTime.now());
        reviewDto.setReviewContents("너무나도 귀여워서 짱짱최고 짱");
        reviewDto.setMemberId(1L);
        reviewDto.setProductId(1L);
        //when

        reviewMapper.insertReview(reviewDto);
        //then
        assertThat(reviewDto.getReviewGrade()).isEqualTo(3);



    }

    @Test
    @DisplayName("리뷰를 memberID를 통해 조회합니다.")
    public void selectByMemberIdTest() {

        //given
        Long memberId = 1L;

        //when
        List<ReviewDto> review = reviewMapper.selectReviewByMemberId(memberId);

        //then

        assertThat(review.get(0).getMemberId()).isEqualTo(memberId);



    }
/*

    @Test
    @DisplayName("아이템 아이디를 통해 리뷰를 가져옵니다.")
    public void selectReviewByProductIdTest() {

        //given
        Long productId = 1L;

        //when
        List<ReviewDto> review = reviewMapper.selectReviewByProductId(productId);

         assertThat(review.size()).isEqualTo(3);
    }
*/

    @Test
    @DisplayName("리뷰를 삭제합니다.")
    public void deleteReviewTest() {

        //given
        Long reviewId = 1L;

        //when
        reviewMapper.deleteReview(reviewId);

        //then
        assertThat(reviewMapper.selectReviewByReviewId(reviewId)).isNull();


    }

    @Test
    @DisplayName("리뷰를 수정합니다.")
    public void updateTest() {

        //given
        Long reviewId = 1L;
        ReviewDto reviewDto = new ReviewDto();

        //when
        reviewDto.setReviewId(reviewId);
        reviewDto.setReviewImg("수정");
        reviewDto.setReviewGrade(2);
        reviewDto.setReviewContents("수정");

        reviewMapper.updateReview(reviewDto);
        //then
        assertThat(reviewMapper.selectReviewByReviewId(reviewId).getReviewImg()).isEqualTo("수정");

    }

    @Test
    @DisplayName("memberId에 따라 수를 셉니다.")
    public void countAllReviewTest() {

        //given
        Long memberId = 1L;

        //when
        int cnt = reviewMapper.countAllReviewByMemberId(memberId);

        //then
        assertThat(cnt).isEqualTo(5);

    }
}
