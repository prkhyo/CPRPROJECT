package com.CPR.redHome.service.question;

import com.CPR.redHome.dto.question.QuestionViewDto;
import com.CPR.redHome.mapper.question.QuestionMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class QuestionServiceImpl implements QuestionService {

    private final QuestionMapper questionMapper;


    @Override
    public List<QuestionViewDto> selectQuestionList(Long productId) {

        List<QuestionViewDto> questionList = questionMapper.selectQuestionList(productId);

        return questionList;
    }

    @Override
    public int countAllQuestions(Long productId) {

        int questionCnt = questionMapper.selectQuestionCnt(productId);

        return questionCnt;
    }
}
