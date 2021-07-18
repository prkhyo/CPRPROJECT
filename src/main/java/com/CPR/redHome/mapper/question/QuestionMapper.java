package com.CPR.redHome.mapper.question;

import com.CPR.redHome.dto.question.QuestionViewDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface QuestionMapper {

    //특정 제품에 대한 문의 리스트 가져오기
    List<QuestionViewDto> selectQuestionList(Long productId);

    //특정 제품에 대한 총 문의 수 가져오기
    int selectQuestionCnt(Long productId);


}
