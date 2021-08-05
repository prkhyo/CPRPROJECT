package com.CPR.redHome.controller.product;

import com.CPR.redHome.dto.cart.CartDto;
import com.CPR.redHome.dto.member.MemberDto;
import com.CPR.redHome.dto.product.ProductImageDto;
import com.CPR.redHome.dto.product.ProductViewDto;
import com.CPR.redHome.dto.question.QuestionViewDto;
import com.CPR.redHome.dto.review.ReviewHelpDto;
import com.CPR.redHome.dto.review.ReviewViewDto;
import com.CPR.redHome.paging.Criteria;
import com.CPR.redHome.paging.Pagination;
import com.CPR.redHome.service.product.ProductService;
import com.CPR.redHome.service.question.QuestionService;
import com.CPR.redHome.service.review.ReviewService;
import com.CPR.redHome.web.argumentresolver.Login;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
@Log4j2
public class ProductController {

    private final ProductService productService;
    private final QuestionService questionService;
    private final ReviewService reviewService;

    @GetMapping("/product/detail")
    public String productDetailPage(Model model, @RequestParam Long productId, @Login MemberDto loginMember, @RequestParam(required = false) String reviewSort,
                                    @RequestParam(defaultValue = "1") int questionCurrentPageNo, @RequestParam(defaultValue = "1") int reviewCurrentPageNo){


        model.addAttribute("reviewSort", reviewSort);

        ProductViewDto productDto = productService.selectProduct(productId);
        List<ProductImageDto> productImageList = productService.selectProductImgList(productId);
        model.addAttribute("productImageList", productImageList);

        //문의 페이징
        int questionCnt = questionService.countAllQuestions(productId);
        model.addAttribute("questionCnt", questionCnt);

        Criteria questionCriteria = new Criteria();
        questionCriteria.setCurrentPageNo(questionCurrentPageNo);

        Pagination questionPagination = new Pagination(questionCriteria, questionCnt, 8, 5);

        int firstRecordIndex = questionPagination.getFirstRecordIndex();

        List<QuestionViewDto> questionList = Collections.emptyList();

        if(questionCnt > 0){
            questionList = questionService.selectQuestionList(productId, firstRecordIndex, questionCriteria);
        }
        model.addAttribute("questionList", questionList);
        model.addAttribute("questionPageMaker",questionPagination);


        //리뷰 페이징
        int reviewCnt = reviewService.selectReviewCnt(productId);

        Criteria reviewCriteria = new Criteria();
        reviewCriteria.setCurrentPageNo(reviewCurrentPageNo);

        Pagination reviewPagination = new Pagination(reviewCriteria, reviewCnt, 8, 5);

        int reviewFirstRecordIndex = reviewPagination.getFirstRecordIndex();

        List<ReviewViewDto> reviewList = Collections.emptyList();

        if(reviewCnt > 0){
            reviewList = reviewService.selectReviewList(productId, reviewFirstRecordIndex, reviewCriteria, reviewSort);
        }

        //특정 리뷰가 현재 로그인한 사용자에게 도움된 리뷰인지 아닌지 체크
        if(loginMember != null) {
            for (int i = 0; i < reviewList.size(); i++) {
                List<ReviewHelpDto> helpList = reviewService.selectHelpList(reviewList.get(i).getReviewId());
                if (helpList.size() > 0) {
                    for (int j = 0; j < helpList.size(); j++) {
                        if (helpList.get(j).getMemberId() == loginMember.getMemberId()) {
                            for (int k = 0; k < reviewList.size(); k++) {
                                if (helpList.get(j).getReviewId() == reviewList.get(k).getReviewId()) {
                                    reviewList.get(k).setHelpState("helpful");

                                }//if
                            }//for
                        }//if
                    }//for
                }//if
            }//for
        }


        model.addAttribute("reviewList", reviewList);
        model.addAttribute("reviewPageMaker",reviewPagination);


        if(reviewCnt > 0){

            //각 별점의 수, 백분율 값 가져오기
            Map<String, Integer> reviewGradeCntList = new HashMap<String, Integer>();
            Map<String, Double> reviewGradePerList = new HashMap<String, Double>();

            for(int i = 1; i < 6; i++){
                reviewGradeCntList.put("grade"+i, reviewService.selectParticularGradeCnt(i, productId));
                reviewGradePerList.put("gradePercent"+i, (double) reviewService.selectParticularGradeCnt(i, productId)/reviewCnt*100);
            }
            model.addAttribute("reviewGradeCntList",reviewGradeCntList);
            model.addAttribute("reviewGradePerList",reviewGradePerList);
        }

        model.addAttribute("productDto", productDto);

        return "product/product_detail";
    }



    @GetMapping("/product/insertTo/cart")
    public String productInsertToCart(@Login MemberDto loginMember, @RequestParam Long productId, @RequestParam Integer quantity){


        CartDto cartDto = new CartDto();
        cartDto.setMemberId(loginMember.getMemberId());
        cartDto.setProductId(productId);
        cartDto.setQuantity(quantity);


        productService.insertProductToCart(cartDto);
        System.out.println("insertProductToCart 성공");



     return "redirect:/cart";
    }


    @GetMapping("/product/moveTo/payment")
    public  String productMoveToPayment(@Login MemberDto loginMember, @RequestParam Long productId, @RequestParam Integer quantity){

        CartDto cartDto = new CartDto();
        cartDto.setMemberId(loginMember.getMemberId());
        cartDto.setProductId(productId);
        cartDto.setQuantity(quantity);

        //구매할 제품 장바구니에 넣기
        productService.insertProductToCart(cartDto);
        System.out.println("insertProductToCart 성공");

        //최근 생성한 장바구니 아이디 받아오기
        String selectNo = productService.selectShoppingCartNo(productId);


        return "redirect:/cart/payment?selectNo="+selectNo;
    }


    @GetMapping("/store")
    public String storePage(Model model, @RequestParam(required = false, defaultValue = "new") String storeOrder, @RequestParam(required = false) String deliveryChargeOPtion,
                            @RequestParam(required = false) String searchProductKeyword, @RequestParam(required = false) Integer productThemeNo){


        List<ProductViewDto> productList = productService.selectProductList(storeOrder, deliveryChargeOPtion, searchProductKeyword, productThemeNo);
        model.addAttribute("productList", productList);

        model.addAttribute("storeOrder", storeOrder);
        model.addAttribute("deliveryChargeOPtion", deliveryChargeOPtion);
        model.addAttribute("productThemeNo", productThemeNo);
        model.addAttribute("searchProductKeyword", searchProductKeyword);

        return "product/store";
    }

    // 판매자 페이지 이동.
    @GetMapping("/sellerStore/{memberId}")
    public String sellerStorePage(@PathVariable Long memberId, Model model, @RequestParam(required = false, defaultValue = "new") String storeOrder, @RequestParam(required = false) String deliveryChargeOPtion,
                               @RequestParam(required = false) String searchProductKeyword, @RequestParam(required = false) Integer productThemeNo){

        List<ProductViewDto> productList = productService.selectSellerList(storeOrder, deliveryChargeOPtion, searchProductKeyword, productThemeNo, memberId);
        model.addAttribute("productList", productList);

        model.addAttribute("storeOrder", storeOrder);
        model.addAttribute("deliveryChargeOPtion", deliveryChargeOPtion);
        model.addAttribute("productThemeNo", productThemeNo);
        model.addAttribute("searchProductKeyword", searchProductKeyword);

        return "seller/seller_store";
    }


}
