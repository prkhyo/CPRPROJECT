package com.CPR.redHome.mapper.answer;

import com.CPR.redHome.dto.question.AnswerDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AnswerMapper {

    // 문의 답변 등록
    void insertAnswer(AnswerDto answerDto);

    // 문의 답변 삭제
    void deleteAnswer(Long answerId);
}
