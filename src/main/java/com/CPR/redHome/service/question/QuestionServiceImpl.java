package com.CPR.redHome.service.question;

import com.CPR.redHome.dto.question.QuestionDto;
import com.CPR.redHome.dto.question.QuestionSmallViewDto;
import com.CPR.redHome.dto.question.QuestionViewDto;
import com.CPR.redHome.mapper.question.QuestionMapper;
import com.CPR.redHome.paging.Criteria;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class QuestionServiceImpl implements QuestionService {

    private final QuestionMapper questionMapper;


    @Override
    public List<QuestionViewDto> selectQuestionList(Long productId, int firstRecordIndex, Criteria criteria) {

        List<QuestionViewDto> questionList = questionMapper.selectQuestionList(productId, firstRecordIndex, criteria);

        return questionList;
    }

    @Override
    public QuestionViewDto selectQuestion(Long questionId) {

        QuestionViewDto questionViewDto = questionMapper.selectQuestion(questionId);

        return questionViewDto;
    }

    @Override
    public int countAllQuestions(Long productId) {

        int questionCnt = questionMapper.selectQuestionCnt(productId);

        return questionCnt;
    }

    @Override
    public void insertQuestion(QuestionDto questionDto) {

        questionMapper.insertQuestion(questionDto);

    }

    @Override
    public void updateQuestionState(Long questionId) {
        questionMapper.updateQuestionState(questionId);
    }

    @Override
    public void questionStateAfterDelete(Long questionId) {
        questionMapper.questionStateAfterDelete(questionId);
    }

    @Override
    public void deleteQuestion(Long questionId) {

        questionMapper.deleteQuestion(questionId);

    }

    @Override
    public List<QuestionSmallViewDto> selectQuestionByMemberId(Long memberId) {
        return questionMapper.selectQuestionByMemberId(memberId);
    }
}
