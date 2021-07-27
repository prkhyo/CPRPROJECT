package com.CPR.redHome.service.review;

import com.CPR.redHome.dto.review.ReviewViewDto;
import com.CPR.redHome.paging.Criteria;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface ReviewService {

    int selectReviewCnt(Long productId);

    List<ReviewViewDto> selectReviewList(Long productId, int firstRecordIndex, Criteria criteria);


}
