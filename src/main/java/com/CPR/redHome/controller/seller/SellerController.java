package com.CPR.redHome.controller.seller;

import com.CPR.redHome.dto.member.MemberDto;
import com.CPR.redHome.dto.order.OrderedDto;
import com.CPR.redHome.dto.product.ProductViewDto;
import com.CPR.redHome.dto.question.QuestionViewDto;
import com.CPR.redHome.dto.seller.ProductRegistDto;
import com.CPR.redHome.paging.Criteria;
import com.CPR.redHome.paging.Pagination;
import com.CPR.redHome.service.product.ProductService;
import com.CPR.redHome.service.question.QuestionService;
import com.CPR.redHome.service.seller.SellerService;
import com.CPR.redHome.web.argumentresolver.Login;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;


@Log4j2
@Controller
@RequiredArgsConstructor
public class SellerController {

    private final SellerService sellerService;
    private final ProductService productService;
    private final QuestionService questionService;

    @GetMapping("/product/regist")
    public String registProduct(@Login MemberDto loginMember, Model model) {

        model.addAttribute("memberId", loginMember.getMemberId());

        return "seller/productRegistration";
    }

    @PostMapping("/product/regist")
    @ResponseStatus(HttpStatus.OK)
    public void submitRegistProduct(@RequestBody List<ProductRegistDto> productRegistDto) {

        sellerService.registProducts(productRegistDto.get(0));
        sellerService.registImage(productRegistDto);

    }



    // 판매자 페이지 이동.
    @GetMapping("/sellerStore/{memberId}")
    public String sellerStorePage(@Login MemberDto loginMember,@PathVariable Long memberId, Model model, @RequestParam(required = false, defaultValue = "new") String storeOrder, @RequestParam(required = false) String deliveryChargeOPtion,
                                  @RequestParam(required = false) String searchProductKeyword, @RequestParam(required = false) Integer productThemeNo){

        try {
            Long loginMemberId = loginMember.getMemberId();

            if (loginMemberId != null) {
                List<ProductViewDto> productList = productService.selectSellerList(storeOrder, deliveryChargeOPtion, searchProductKeyword, productThemeNo, memberId);
                model.addAttribute("productList", productList);

                model.addAttribute("storeOrder", storeOrder);
                model.addAttribute("deliveryChargeOPtion", deliveryChargeOPtion);
                model.addAttribute("productThemeNo", productThemeNo);
                model.addAttribute("searchProductKeyword", searchProductKeyword);

                return "seller/seller_store";
            } else {
                return "redirect:/login";
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return "redirect:/login";
    }

    @GetMapping("/sellerQuestion/{memberId}")
    public String sellerQuestionPage(@Login MemberDto loginMember, @PathVariable Long memberId, @ModelAttribute("criteria") Criteria criteria, Model model, @ModelAttribute("reply") String reply, @ModelAttribute("orderType") String orderType,
                                     @RequestParam(defaultValue="1") int currentPageNo){

        try {
            Long loginMemberId = loginMember.getMemberId();

            if (loginMemberId != null) {
                List<QuestionViewDto> questionList = Collections.emptyList();

                int questionTotalCnt = sellerService.countAllSellerQuestions(memberId,reply,criteria);

                criteria.setCurrentPageNo(currentPageNo);
                Pagination pagination = new Pagination(criteria, questionTotalCnt, 5, 5);

                int firstRecordIndex = pagination.getFirstRecordIndex();

                if(questionTotalCnt > 0){
                    questionList = sellerService.getQuestionList(memberId, reply, orderType, firstRecordIndex, criteria);
                }

                model.addAttribute("questionList", questionList);
                model.addAttribute("pageMaker",pagination);
                return "seller/seller_question";
            } else {
                return "redirect:/login";
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return "redirect:/login";
    }

    @GetMapping("/sellerOrder/{memberId}")
    public String sellerOrderPage(@Login MemberDto loginMember, @PathVariable Long memberId, @ModelAttribute("criteria") Criteria criteria, Model model, @ModelAttribute("reply") String reply, @ModelAttribute("orderType") String orderType,
                                     @RequestParam(defaultValue="1") int currentPageNo){

        try {
            Long loginMemberId = loginMember.getMemberId();

            if (loginMemberId != null) {

                List<OrderedDto> orderList = Collections.emptyList();

                int SellerOrderTotalCnt = sellerService.countAllSellerOrders(memberId,criteria);

                criteria.setCurrentPageNo(currentPageNo);
                Pagination pagination = new Pagination(criteria, SellerOrderTotalCnt, 5, 5);

                int firstRecordIndex = pagination.getFirstRecordIndex();

                if(SellerOrderTotalCnt > 0){
                    orderList = sellerService.getOrderList(memberId, orderType, firstRecordIndex, criteria);
                }

                model.addAttribute("orderList", orderList);
                model.addAttribute("pageMaker",pagination);

                return "seller/seller_order";
            } else {
                return "redirect:/login";
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return "redirect:/login";
    }



}
