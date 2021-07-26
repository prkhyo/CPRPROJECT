package com.CPR.redHome.controller.question;

import com.CPR.redHome.dto.member.MemberDto;
import com.CPR.redHome.dto.question.QuestionDto;
import com.CPR.redHome.service.question.QuestionService;
import com.CPR.redHome.web.argumentresolver.Login;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@Log4j2
public class QuestionController {

    private final QuestionService questionService;


    @GetMapping("/question/add")
    public String questionAddPage(@RequestParam Long productId, Model model){

        model.addAttribute("productId", productId);

        return "question/question_add";
    }



    @PostMapping("/question/questionInsert")
    public String questionInsert(@Login MemberDto loginMember, @RequestParam String questionCategory, Long productId,
                                 String secretQuestion, String questionContents){

        QuestionDto questionDto = new QuestionDto();
        questionDto.setQuestionContents(questionContents);
        questionDto.setProductId(productId);
        questionDto.setMemberId(loginMember.getMemberId());

        if(secretQuestion != null){
            questionDto.setSecretQuestion("true");
        }

        int questionCategoryId;

        if(questionCategory.equals("delivery")){
            questionCategoryId = 1;   //배송
        }else if(questionCategory.equals("product")){
            questionCategoryId = 2;   //상품
        }else if(questionCategory.equals("return")){
            questionCategoryId = 3;   //반품
        }else if(questionCategory.equals("exchange")){
            questionCategoryId = 4;   //교환
        }else if(questionCategory.equals("refund")){
            questionCategoryId = 5;   //환불
        }else{
            questionCategoryId = 6;   //기타
        }
        questionDto.setQuestionCategoryId(questionCategoryId);

        questionService.insertQuestion(questionDto);
        System.out.println("insert 성공");


        return "redirect:/product/detail?productId="+productId+"#production-selling-question";
    }


    @GetMapping("/question/questionDelete")
    public String questionDelete(@RequestParam Long questionId, @RequestParam int questionCurrentPageNo, @RequestParam Long productId){

        questionService.deleteQuestion(questionId);
        System.out.println("delete 성공");

        return "redirect:/product/detail?questionCurrentPageNo="+ questionCurrentPageNo +"&productId="+ productId +"#production-selling-question";
    }


}


