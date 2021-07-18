package com.CPR.redHome.service.question;

import com.CPR.redHome.dto.question.QuestionViewDto;

import java.util.List;

public interface QuestionService {

    List<QuestionViewDto> selectQuestionList(Long productId);

    int countAllQuestions(Long productId);
}
