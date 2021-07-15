package com.CPR.redHome.controller.product;

import com.CPR.redHome.service.product.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
@Log4j2
public class ProductController {

    private final ProductService productService;


    @GetMapping("/product/detail")
    public String productDetailPage(){

        return "product/product_detail";
    }
}
