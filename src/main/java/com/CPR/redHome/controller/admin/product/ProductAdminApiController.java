package com.CPR.redHome.controller.admin.product;

import com.CPR.redHome.service.admin.product.ProductAdminService;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class ProductAdminApiController {

    private final ProductAdminService productAdminService;

    // 가격 별 상품 수 조회
    @Transactional(readOnly = true)
    @GetMapping("/api/admin/product/chart/byPrice")
    public ResponseEntity<JSONObject> listByPrice() {
        ResponseEntity<JSONObject> entity=null;
        JSONObject data = productAdminService.selectProductByPrice();

        try {
            entity = new ResponseEntity<JSONObject>(data, HttpStatus.OK);
        }catch (Exception e){
            System.out.println("error");
            entity = new ResponseEntity<JSONObject>(HttpStatus.BAD_REQUEST);
        }

        return entity;
    }

    // 카테고리 별 상품 수 조회
    @Transactional(readOnly = true)
    @GetMapping("/api/admin/product/chart/byCategory")
    public ResponseEntity<JSONObject> listByCategory() {
        ResponseEntity<JSONObject> entity=null;
        JSONObject data = productAdminService.selectProductByCategory();

        try {
            entity = new ResponseEntity<JSONObject>(data, HttpStatus.OK);
        }catch (Exception e){
            System.out.println("error");
            entity = new ResponseEntity<JSONObject>(HttpStatus.BAD_REQUEST);
        }

        return entity;
    }

    // 테마 별 상품 수 조회
    @Transactional(readOnly = true)
    @GetMapping("/api/admin/product/chart/byTheme")
    public ResponseEntity<JSONObject> listByTheme() {
        ResponseEntity<JSONObject> entity=null;
        JSONObject data = productAdminService.selectProductByTheme();

        try {
            entity = new ResponseEntity<JSONObject>(data, HttpStatus.OK);
        }catch (Exception e){
            System.out.println("error");
            entity = new ResponseEntity<JSONObject>(HttpStatus.BAD_REQUEST);
        }

        return entity;
    }
}
