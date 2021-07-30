package com.CPR.redHome.service.product;

import com.CPR.redHome.dto.cart.CartDto;
import com.CPR.redHome.dto.product.ProductImageDto;
import com.CPR.redHome.dto.product.ProductViewDto;
import com.CPR.redHome.mapper.product.ProductMapper;
import com.CPR.redHome.mapper.review.ReviewMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductMapper productMapper;
    private final ReviewMapper reviewMapper;

    @Override
    public ProductViewDto selectProduct(Long productId) {

        ProductViewDto productDto = productMapper.selectProduct(productId);
        //이미지 가져오는 mapper도 추가
        
        return productDto;
    }

    @Override
    public List<ProductImageDto> selectProductImgList(Long productId) {

        List<ProductImageDto> productImageList = productMapper.selectProductImgList(productId);

        return productImageList;
    }




    @Override
    public List<ProductViewDto> selectProductList(String storeOrder, String deliveryChargeOPtion, String searchProductKeyword, Integer productThemeNo) {

        List<ProductViewDto> productList = productMapper.selectProductList(storeOrder, deliveryChargeOPtion, searchProductKeyword, productThemeNo);

        int totalReviewCnt = 0;
        int reviewGradeSum = 0;
        double reviewGradeAvg = 0;

        for(int i = 0; i < productList.size(); i++) {

            //전체 상품리스트에 대해 각각의 상품마다 총 리뷰수 구하기
            totalReviewCnt = reviewMapper.selectReviewCnt(productList.get(i).getProductId());
            productList.get(i).setTotalReviewCnt(totalReviewCnt);

            //전체 상품리스트에 대해 각각의 상품마다 평균 별점 계산
            if (totalReviewCnt > 0) {
                List<Integer> reviewGradeList = reviewMapper.selectReviewGradeList(productList.get(i).getProductId());

                for (int j = 0; j < reviewGradeList.size(); j++) {
                    reviewGradeSum += reviewGradeList.get(j);
                }
                reviewGradeAvg = reviewGradeSum / totalReviewCnt;
                productList.get(i).setReviewGradeAvg(reviewGradeAvg);
            }

        }

            return productList;
    }

    @Override
    public void insertProductToCart(CartDto cartDto) {
        productMapper.insertProductToCart(cartDto);
    }

    @Override
    public String selectShoppingCartNo(Long productId) {

        String shoppingCartNo = String.valueOf(productMapper.selectShoppingCartNo(productId));

        return shoppingCartNo;
    }


}
