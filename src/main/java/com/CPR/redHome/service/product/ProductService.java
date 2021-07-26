package com.CPR.redHome.service.product;

import com.CPR.redHome.dto.cart.CartDto;
import com.CPR.redHome.dto.product.ProductImageDto;
import com.CPR.redHome.dto.product.ProductViewDto;

import java.util.List;

public interface ProductService {

    ProductViewDto selectProduct(Long productId);

    List<ProductImageDto> selectProductImgList(Long productId);

    //제품리스트 가져오기 (테스트용)
    List<ProductViewDto> selectProductList();

    void insertProductToCart(CartDto cartDto);

    String selectShoppingCartNo(Long productId);

}
