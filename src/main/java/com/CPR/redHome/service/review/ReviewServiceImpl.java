package com.CPR.redHome.service.review;

import com.CPR.redHome.dto.review.ReviewHelpDto;
import com.CPR.redHome.dto.review.ReviewViewDto;
import com.CPR.redHome.mapper.review.ReviewMapper;
import com.CPR.redHome.paging.Criteria;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import com.CPR.redHome.dto.review.ReviewDto;

import org.apache.commons.io.FilenameUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.UUID;

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
    public List<ReviewViewDto> selectReviewList(Long productId, int firstRecordIndex, Criteria criteria, String reviewSort) {

        List<ReviewViewDto> reviewList = reviewMapper.selectReviewList(productId, firstRecordIndex, criteria, reviewSort);

        return reviewList;

    }

    @Override
    public void insertReview(ReviewDto reviewDto, MultipartFile file, HttpServletRequest request) throws IOException {
        System.out.println("==============================서비스단에 들어갔다===================================");
        reviewDto.setReviewCreatedDate(LocalDateTime.now());


        if( !file.isEmpty() ) {

            String filename = fileUpload(file, request);
            reviewDto.setReviewImg(filename);

        }

        reviewMapper.insertReview(reviewDto);

    }

    @Override
    public int selectParticularGradeCnt(int reviewGrade, Long productId) {

        int particularGradeCnt = reviewMapper.selectParticularGradeCnt(reviewGrade, productId);

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
    public void deleteReview(Long reviewId) {

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

    @Override
    public void updateReview(ReviewDto reviewDto) {

    }

    private String fileUpload(MultipartFile file, HttpServletRequest request) throws IOException {
        System.out.println("==============================파일업로드 중이다 일어났따===================================");
        String filename=null;

        String originalFileName = file.getOriginalFilename();
        String ext = FilenameUtils.getExtension(originalFileName); //확장자

        UUID uuid = UUID.randomUUID(); //UUID 구하기
        filename = uuid+"."+ext;


        file.transferTo( new File( request.getSession().getServletContext().getRealPath("/")+"fileUpload\\review\\reviewProduct\\"+filename) );  // 저장할 경로를 설정


        return filename;
    }
}
