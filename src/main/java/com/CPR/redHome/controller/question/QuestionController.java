package com.CPR.redHome.controller.question;

import com.CPR.redHome.service.question.QuestionService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;

@Controller
@RequiredArgsConstructor
@Log4j2
public class QuestionController {

    private final QuestionService questionService;



}
