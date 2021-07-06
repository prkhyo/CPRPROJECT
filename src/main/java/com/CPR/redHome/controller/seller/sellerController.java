package com.CPR.redHome.controller.seller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class sellerController {

    @GetMapping("/product/regist")
    public String registItem(Model model) {

        return "seller/productRegistration";

    }

    @GetMapping("/uploadEx")
    public void uploadEx() {

    }
}
