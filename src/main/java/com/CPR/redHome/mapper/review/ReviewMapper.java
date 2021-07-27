package com.CPR.redHome.mapper.review;

import com.CPR.redHome.dto.review.ReviewViewDto;
import com.CPR.redHome.paging.Criteria;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Mapper
public interface ReviewMapper {
    
    
    //특정 제품에 대한 총 리뷰 수 가져오기
    int selectReviewCnt(Long productId);

    //특정 제품에 대한 리뷰 리스트 가져오기
    List<ReviewViewDto> selectReviewList(@RequestParam Long productId, @RequestParam int firstRecordIndex, @RequestParam Criteria criteria);

}
