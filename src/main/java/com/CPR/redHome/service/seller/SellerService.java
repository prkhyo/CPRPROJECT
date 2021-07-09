package com.CPR.redHome.service.seller;

import com.CPR.redHome.dto.seller.ProductRegistDto;

import java.util.List;

public interface SellerService {

    //상품 등록
    int registProducts(List<ProductRegistDto> productRegistDto);

    // 상품 이미지 등록
    int registImage(List<ProductRegistDto> productRegistDto);

}
