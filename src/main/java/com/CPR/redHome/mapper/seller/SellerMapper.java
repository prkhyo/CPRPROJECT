package com.CPR.redHome.mapper.seller;

import com.CPR.redHome.dto.seller.ImageDto;
import com.CPR.redHome.dto.seller.ProductRegistDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SellerMapper {

    //상품 등록
    int registProducts(ProductRegistDto productRegistDto);

    // 상품 이미지 등록
    int registImage(List<ProductRegistDto> productRegistDto);

}
