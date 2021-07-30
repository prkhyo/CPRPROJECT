package com.CPR.redHome.service.admin.product;

import com.CPR.redHome.dto.product.ProductDto;
import com.CPR.redHome.mapper.admin.product.ProductAdminMapper;
import com.CPR.redHome.paging.Criteria;
import com.CPR.redHome.service.admin.util.MakeJsonForChartServcie;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedHashMap;
import java.util.List;

@Log4j2
@Service
@RequiredArgsConstructor
public class ProductAdminServiceImpl implements ProductAdminService{

    private final ProductAdminMapper productAdminMapper;
    private final MakeJsonForChartServcie makeJsonForChartServcie;

    // totalCnt
    @Override
    @Transactional(readOnly = true)
    public int countAll(Criteria criteria) {
        int cnt = productAdminMapper.selectTotalCnt(criteria);
        return cnt;
    }

    // 상품 조회
    @Override
    @Transactional(readOnly = true)
    public List<ProductDto> getProductList(int firstRecordIndex, Criteria criteria) {
        List<ProductDto> productDtos = productAdminMapper.selectProducts(firstRecordIndex, criteria);

        return productDtos;
    }

    // productId로 상품 정보 조회
    @Override
    @Transactional(readOnly = true)
    public ProductDto selectProductByProductId(Long productId) {
        ProductDto productDto = productAdminMapper.selectProductByProductId(productId);
        return productDto;
    }

    // 상품 정보 update
    @Override
    @Transactional
    public void updateProduct(ProductDto productDto) {
        productAdminMapper.updateProduct(productDto);
    }

    // 상품 delete
    @Override
    @Transactional
    public void deleteProduct(Long productId) {
        productAdminMapper.deleteProduct(productId);
    }

    // 가격대 별 상품 수 조회
    @Override
    @Transactional(readOnly = true)
    public JSONObject selectProductByPrice() {

        // 가격대 별 상품 수 DB에서 받아옴.
        LinkedHashMap<String, Integer> map = productAdminMapper.selectProductByPrice();

        // 구글차트에서 인식하는 JSON data로 가공.
        JSONObject data = makeJsonForChartServcie.makeJsonForChart(map, "가격대", "상품수");

        return data;
    }

    // 카테고리 별 상품 수
    @Override
    @Transactional(readOnly = true)
    public JSONObject selectProductByCategory() {
        // 카테고리 별 상품 수 DB에서 받아옴.
        LinkedHashMap<String, Integer> map = productAdminMapper.selectProductByCategory();

        // 구글차트에서 인식하는 JSON data로 가공.
        JSONObject data = makeJsonForChartServcie.makeJsonForChart(map, "카테고리", "상품수");

        return data;
    }

    // 재고수량 별 상품 수 조회
    @Override
    @Transactional(readOnly = true)
    public JSONObject selectProductByTheme() {
        // 테마 별 상품 수 DB에서 받아옴.
        LinkedHashMap<String, Integer> map = productAdminMapper.selectProductByTheme();

        // 구글차트에서 인식하는 JSON data로 가공.
        JSONObject data = makeJsonForChartServcie.makeJsonForChart(map, "테마", "상품수");

        return data;
    }
}
