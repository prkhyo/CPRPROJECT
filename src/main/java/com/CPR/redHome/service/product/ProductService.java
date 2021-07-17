package com.CPR.redHome.service.product;

import com.CPR.redHome.dto.product.ProductImageDto;
import com.CPR.redHome.dto.product.ProductViewDto;

import java.util.List;

public interface ProductService {

    ProductViewDto selectProduct(Long productId);

    List<ProductImageDto> selectProductImgList(Long productId);

    ProductImageDto selectProductMainImg(Long productId);

}
