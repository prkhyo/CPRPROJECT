package com.CPR.redHome.service.product;

import com.CPR.redHome.dto.cart.CartDto;
import com.CPR.redHome.dto.product.ProductImageDto;
import com.CPR.redHome.dto.product.ProductViewDto;
import com.CPR.redHome.mapper.product.ProductMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductMapper productMapper;

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
