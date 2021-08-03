package com.CPR.redHome.service.answer;

import com.CPR.redHome.dto.question.AnswerDto;
import com.CPR.redHome.mapper.answer.AnswerMapper;
import com.CPR.redHome.mapper.question.QuestionMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AnswerServiceImpl implements AnswerService {

    private final AnswerMapper answerMapper;

    @Override
    public void insertAnswer(AnswerDto answerDto) {
        answerMapper.insertAnswer(answerDto);
    }

    @Override
    public void deleteAnswer(Long answerId) {
        answerMapper.deleteAnswer(answerId);
    }


}
