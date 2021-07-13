package com.CPR.redHome.controller.seller;

import com.CPR.redHome.dto.member.MemberDto;
import com.CPR.redHome.dto.seller.ProductRegistDto;
import com.CPR.redHome.service.seller.SellerService;
import com.CPR.redHome.web.argumentresolver.Login;
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
    public String registProduct(@Login MemberDto memberDto, Model model) {

        model.addAttribute("memberId", memberDto.getMemberId());

        return "seller/productRegistration";
    }

    @PostMapping("/product/regist")
    @ResponseStatus(HttpStatus.OK)
    public void submitRegistProduct(@RequestBody List<ProductRegistDto> productRegistDto) {

        // 제품 등록 시 이미지가 없으면 기본 내용만 등록 , else 이미지도 같이 저장
        if (productRegistDto.get(0).getImageUrl() == null) {
            log.info("no have any image");
            sellerService.registProducts(productRegistDto.get(0));
        }else{
            log.info("have image");
            sellerService.registProducts(productRegistDto.get(0));
            sellerService.registImage(productRegistDto);
        }

    }
}
