package com.CPR.redHome.service.question;

import com.CPR.redHome.dto.question.QuestionDto;
import com.CPR.redHome.dto.question.QuestionViewDto;
import com.CPR.redHome.paging.Criteria;

import java.util.List;

public interface QuestionService {

    List<QuestionViewDto> selectQuestionList(Long productId, int firstRecordIndex, Criteria criteria);

    QuestionViewDto selectQuestion(Long questionId);

    int countAllQuestions(Long productId);

    void insertQuestion(QuestionDto questionDto);

    void updateQuestionState(Long questionId);

    void questionStateAfterDelete(Long questionId);

    void deleteQuestion(Long questionId);

}
