package com.CPR.redHome.controller.product;

import com.CPR.redHome.dto.product.ProductImageDto;
import com.CPR.redHome.dto.product.ProductViewDto;
import com.CPR.redHome.service.product.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
@Log4j2
public class ProductController {

    private final ProductService productService;


    @GetMapping("/product/detail")
    public String productDetailPage(Model model, @RequestParam Long productId){


        ProductViewDto productDto = productService.selectProduct(productId);
        ProductImageDto productMainImg = productService.selectProductMainImg(productId);
        List<ProductImageDto> productImageList = productService.selectProductImgList(productId);

        model.addAttribute("productDto", productDto);

        return "product/product_detail";
    }
}
