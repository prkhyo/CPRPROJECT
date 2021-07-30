package com.CPR.redHome.controller.admin.product;

import com.CPR.redHome.dto.product.ProductDto;
import com.CPR.redHome.paging.Criteria;
import com.CPR.redHome.paging.Pagination;
import com.CPR.redHome.service.admin.product.ProductAdminService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@Controller
@RequiredArgsConstructor
@Log4j2
public class ProductAdminContorller {

    private final ProductAdminService productAdminService;

    // 상품페이지 조회
    @GetMapping("/admin/product")
    public String adminProduct(@ModelAttribute Criteria criteria, Model model,
                               @RequestParam(defaultValue = "1") int currentPageNo){

        List<ProductDto> productDtos = Collections.emptyList();
        int totalCnt = productAdminService.countAll(criteria);

        criteria.setCurrentPageNo(currentPageNo);
        Pagination pagination = new Pagination(criteria, totalCnt, 5,5);

        int firstRecordIndex = pagination.getFirstRecordIndex();

        if(totalCnt>0){
            productDtos = productAdminService.getProductList(firstRecordIndex, criteria);
        }

        model.addAttribute("pageMaker", pagination);
        model.addAttribute("productDtos", productDtos);
        return "admin/adminProduct";
    }

    // 상품 수정 페이지 조회
    @GetMapping("/admin/product/update/{productId}")
    public String adminProductModify(@PathVariable Long productId, Model model){
        model.addAttribute("productDetails", productAdminService.selectProductByProductId(productId));
        return "/admin/product/productUpdate";
    }

    // 상품 수정 내용 update
    @PostMapping("/admin/product/update")
    public String adminProductUpdate(ProductDto productDto){
        productAdminService.updateProduct(productDto);
        return "redirect:/admin/product";
    }

    // 상품 삭제
    @GetMapping(value = "/admin/product/delete/{productId}")
    public String adminProductDelete(@PathVariable Long productId) {
        productAdminService.deleteProduct(productId);
        return "redirect:/admin/product";
    }

    // 상품페이지 통계
    @GetMapping("admin/product/chart")
    public String adminProductChart() {
        return "admin/chart/productChart";
    }


}
