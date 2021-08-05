package com.CPR.redHome.service.answer;

import com.CPR.redHome.dto.question.AnswerDto;

public interface AnswerService {

    void insertAnswer(AnswerDto answerDto);

    void deleteAnswer(Long answerId);


}
