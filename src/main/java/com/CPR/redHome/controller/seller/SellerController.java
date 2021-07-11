package com.CPR.redHome.controller.seller;

import com.CPR.redHome.dto.seller.ProductRegistDto;
import com.CPR.redHome.service.seller.SellerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Log4j2
@Controller
@RequiredArgsConstructor
public class SellerController {

    private final SellerService sellerService;

    @GetMapping("/product/regist")
    public String registProduct(Model model) {

        return "seller/productRegistration";
    }

    @PostMapping("/product/regist")
    @ResponseStatus(HttpStatus.OK)
    public void submitRegistProduct(@RequestBody List<ProductRegistDto> productRegistDto) {

//        view단에서 json보내면 이렇게 list 방법으로 받을 수 있음!!

        log.info("product regists dto check = " + productRegistDto);/*
        log.info("product regists dto check = " + productRegistDto.get(0));
        log.info("product regists dto check = " + productRegistDto.get(0).getImageUrl());*/

        sellerService.registProducts(productRegistDto.get(0));
        sellerService.registImage(productRegistDto);

    }
}
