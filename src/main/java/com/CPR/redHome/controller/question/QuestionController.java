package com.CPR.redHome.controller.question;

import com.CPR.redHome.dto.community.CommentsDto;
import com.CPR.redHome.paging.Criteria;
import com.CPR.redHome.paging.Pagination;
import com.CPR.redHome.service.question.QuestionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

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


}


