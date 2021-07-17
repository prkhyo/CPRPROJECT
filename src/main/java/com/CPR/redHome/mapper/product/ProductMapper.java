package com.CPR.redHome.mapper.product;

import com.CPR.redHome.dto.product.ProductImageDto;
import com.CPR.redHome.dto.product.ProductViewDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ProductMapper {

    //특정 제품 정보 가져오기
    ProductViewDto selectProduct(Long productId);

    //특정 제품 이미지 가져오기
    List<ProductImageDto>selectProductImgList(Long productId);

    //특정 제품 메인이미지 가져오기
    ProductImageDto selectProductMainImg(Long productId);

}
