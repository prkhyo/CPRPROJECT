package com.CPR.redHome.service.product;

import com.CPR.redHome.dto.cart.CartDto;
import com.CPR.redHome.dto.product.ProductImageDto;
import com.CPR.redHome.dto.product.ProductViewDto;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface ProductService {

    ProductViewDto selectProduct(Long productId);

    List<ProductImageDto> selectProductImgList(Long productId);

    List<ProductViewDto> selectProductList(String storeOrder, String deliveryChargeOPtion,
                                           String searchProductKeyword, Integer productThemeNo);

    List<ProductViewDto> selectSellerList(String storeOrder, String deliveryChargeOPtion,
                                           String searchProductKeyword, Integer productThemeNo, Long memberId);


    void insertProductToCart(CartDto cartDto);

    String selectShoppingCartNo(Long productId);

}
