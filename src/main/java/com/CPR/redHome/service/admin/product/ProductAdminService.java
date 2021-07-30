package com.CPR.redHome.service.admin.product;

import com.CPR.redHome.dto.product.ProductDto;
import com.CPR.redHome.paging.Criteria;
import org.json.simple.JSONObject;

import java.util.List;

public interface ProductAdminService {

    int countAll(Criteria criteria);

    List<ProductDto> getProductList(int firstRecordIndex, Criteria criteria);

    // productId로 상품 조회
    ProductDto selectProductByProductId(Long productId);

    // 상품 update
    void updateProduct(ProductDto productDto);

    // 상품 delete
    void deleteProduct(Long productId);

    //@@@@@ 상품 통계 @@@@@
    // 가격대 별 상품 수
    JSONObject selectProductByPrice();

    // 카테고리 별 상품 수
    JSONObject selectProductByCategory();

    // 테마 별 상품 수
    JSONObject selectProductByTheme();
}
