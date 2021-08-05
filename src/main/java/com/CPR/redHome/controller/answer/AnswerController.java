package com.CPR.redHome.controller.answer;

import com.CPR.redHome.dto.member.MemberDto;
import com.CPR.redHome.dto.product.ProductViewDto;
import com.CPR.redHome.dto.question.AnswerDto;
import com.CPR.redHome.dto.question.QuestionViewDto;
import com.CPR.redHome.service.answer.AnswerService;
import com.CPR.redHome.service.question.QuestionService;
import com.CPR.redHome.web.argumentresolver.Login;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
@Log4j2
public class AnswerController {

    private final AnswerService answerService;
    private final QuestionService questionService;

    @GetMapping("/answer/add")
    public String answerAddPage(@RequestParam Long productId, @RequestParam Long questionId, Model model){

        QuestionViewDto questionViewDto = questionService.selectQuestion(questionId);

        model.addAttribute("questionViewDto", questionViewDto);
        model.addAttribute("productId",productId);
        model.addAttribute("questionId",questionId);

        return "answer/answer_add";
    }

    @PostMapping("/answer/answerInsert")
    public String answerInsert(@Login MemberDto memberDto, Long productId,
                               Long questionId, String answerContents){
        AnswerDto answerDto = new AnswerDto();
        answerDto.setMemberId(memberDto.getMemberId());
        answerDto.setAnswerContents(answerContents);
        answerDto.setQuestionId(questionId);

        answerService.insertAnswer(answerDto);
        questionService.updateQuestionState(questionId);


        return "redirect:/product/detail?productId="+productId+"#production-selling-question";
    }

    @GetMapping("/answer/answerDelete")
    public String answerDelete(@RequestParam Long answerId, @RequestParam int questionCurrentPageNo, @RequestParam Long productId, @RequestParam Long questionId){

        answerService.deleteAnswer(answerId);
        questionService.questionStateAfterDelete(questionId);

        return "redirect:/product/detail?questionCurrentPageNo="+questionCurrentPageNo+"&productId="+productId+"#production-selling-question";
    }
}
