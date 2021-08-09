package com.CPR.redHome.mapper.seller;

import com.CPR.redHome.dto.question.QuestionViewDto;
import com.CPR.redHome.dto.seller.ImageDto;
import com.CPR.redHome.dto.seller.ProductRegistDto;
import com.CPR.redHome.paging.Criteria;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface SellerMapper {

    int selectSellerQuestionTotalCnt(Long memberId, @Param("reply") String reply, @Param("criteria")Criteria criteria);

    List<QuestionViewDto> selectSellerQuestion(Long memberId, @Param("reply") String reply, @Param("orderType") String orderType,  @Param("firstRecordIndex") int firstRecordIndex,
                                               @Param("criteria") Criteria criteria);

    //상품 등록
    int registProducts(ProductRegistDto productRegistDto);

    // 상품 이미지 등록
    int registImage(List<ProductRegistDto> productRegistDto);

}
