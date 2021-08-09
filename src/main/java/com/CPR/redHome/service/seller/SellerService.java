package com.CPR.redHome.service.seller;

import com.CPR.redHome.dto.question.QuestionViewDto;
import com.CPR.redHome.dto.seller.ImageDto;
import com.CPR.redHome.dto.seller.ProductRegistDto;
import com.CPR.redHome.paging.Criteria;

import java.util.List;

public interface SellerService {

    int countAllSellerQuestions(Long memberId, String reply, Criteria criteria);

    List<QuestionViewDto> getQuestionList(Long memberId, String reply, String orderType, int firstRecordIndex, Criteria criteria);



    //상품 등록
    int registProducts(ProductRegistDto productRegistDto);

    // 상품 이미지 등록
    int registImage(List<ProductRegistDto> productRegistDto);

}
