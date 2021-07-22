package com.CPR.redHome.mapper.product;

import com.CPR.redHome.dto.cart.CartDto;
import com.CPR.redHome.dto.product.ProductImageDto;
import com.CPR.redHome.dto.product.ProductViewDto;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Mapper
public interface ProductMapper {

    //특정 제품 정보 가져오기
    ProductViewDto selectProduct(Long productId);

    //특정 제품 이미지 가져오기
    List<ProductImageDto> selectProductImgList(Long productId);

    //제품리스트 가져오기 (테스트용)
    List<ProductViewDto> selectProductList();

    //제품 장바구니에 넣기
    void insertProductToCart(CartDto cartDto);

}
