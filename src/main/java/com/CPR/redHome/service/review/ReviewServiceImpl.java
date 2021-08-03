package com.CPR.redHome.service.review;


import com.CPR.redHome.dto.review.ReviewDto;
import com.CPR.redHome.mapper.review.ReviewMapper;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService{

    private final ReviewMapper reviewMapper;

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
