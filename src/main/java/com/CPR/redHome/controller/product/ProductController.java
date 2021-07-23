package com.CPR.redHome.controller.product;

import com.CPR.redHome.dto.cart.CartDto;
import com.CPR.redHome.dto.member.MemberDto;
import com.CPR.redHome.dto.product.ProductImageDto;
import com.CPR.redHome.dto.product.ProductViewDto;
import com.CPR.redHome.dto.question.QuestionViewDto;
import com.CPR.redHome.paging.Criteria;
import com.CPR.redHome.paging.Pagination;
import com.CPR.redHome.service.product.ProductService;
import com.CPR.redHome.service.question.QuestionService;
import com.CPR.redHome.web.argumentresolver.Login;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collections;
import java.util.List;

@Controller
@RequiredArgsConstructor
@Log4j2
public class ProductController {

    private final ProductService productService;
    private final QuestionService questionService;

    @GetMapping("/product/detail")
    public String productDetailPage(Model model, @RequestParam Long productId, @RequestParam(defaultValue = "1") int questionCurrentPageNo,
                                    @Login MemberDto memberDto){


        ProductViewDto productDto = productService.selectProduct(productId);
        List<ProductImageDto> productImageList = productService.selectProductImgList(productId);
        model.addAttribute("productDto", productDto);
        model.addAttribute("productImageList", productImageList);

        int questionCnt = questionService.countAllQuestions(productId);
        model.addAttribute("questionCnt", questionCnt);

        //리뷰 맡은 사람도 리뷰 페이징하려면 리뷰 criteria, pagination을 따로 만들어야 할 것 같아서
        // 코드 길어지더라도 criteria를 파라미터로 안받고 밑에 코드처럼 따로 직접 생성했어요!
        Criteria questionCriteria = new Criteria();
        questionCriteria.setCurrentPageNo(questionCurrentPageNo);

        Pagination questionPagination = new Pagination(questionCriteria, questionCnt, 3, 2);

        int firstRecordIndex = questionPagination.getFirstRecordIndex();

        List<QuestionViewDto> questionList = Collections.emptyList();

        if(questionCnt > 0){
            questionList = questionService.selectQuestionList(productId, firstRecordIndex, questionCriteria);
        }
        model.addAttribute("questionList", questionList);
        model.addAttribute("questionPageMaker",questionPagination);
        model.addAttribute("member", memberDto);


        return "product/product_detail";
    }


    /*제품리스트 페이지로 이동(메인페이지 완성되면 삭제할 예정)*/
    @GetMapping("/product/list")
    public String productListPage(Model model){


        List<ProductViewDto> productList = productService.selectProductList();
        model.addAttribute("productList", productList);

        return "product/product_list_test";
    }




    @GetMapping("/product/insertTo/cart")
    public String productInsertToCart(@Login MemberDto memberDto, @RequestParam Long productId, @RequestParam Integer quantity){


        CartDto cartDto = new CartDto();
        cartDto.setMemberId(memberDto.getMemberId());
        cartDto.setProductId(productId);
        cartDto.setQuantity(quantity);


        productService.insertProductToCart(cartDto);
        System.out.println("insertProductToCart 성공");



     return "redirect:/cart";
    }


    @GetMapping("/product/moveTo/payment")
    public  String productMoveToPayment(@Login MemberDto memberDto, @RequestParam Long productId, @RequestParam Integer quantity){

        CartDto cartDto = new CartDto();
        cartDto.setMemberId(memberDto.getMemberId());
        cartDto.setProductId(productId);
        cartDto.setQuantity(quantity);

        //구매할 제품 장바구니에 넣기
        productService.insertProductToCart(cartDto);
        System.out.println("insertProductToCart 성공");

        //최근 생성한 장바구니 아이디 받아오기
        String selectNo = productService.selectShoppingCartNo(productId);


        return "redirect:/cart/payment?selectNo="+selectNo;
    }





}
