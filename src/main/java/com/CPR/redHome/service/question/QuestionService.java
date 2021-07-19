package com.CPR.redHome.service.question;

import com.CPR.redHome.dto.question.QuestionViewDto;
import com.CPR.redHome.paging.Criteria;

import java.util.List;

public interface QuestionService {

    List<QuestionViewDto> selectQuestionList(Long productId, int firstRecordIndex, Criteria criteria);

    int countAllQuestions(Long productId);
}
