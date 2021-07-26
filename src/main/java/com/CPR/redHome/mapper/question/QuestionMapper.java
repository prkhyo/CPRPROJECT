package com.CPR.redHome.mapper.question;

import com.CPR.redHome.dto.question.QuestionDto;
import com.CPR.redHome.dto.question.QuestionViewDto;
import com.CPR.redHome.paging.Criteria;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Mapper
public interface QuestionMapper {

    //특정 제품에 대한 문의 리스트 가져오기
    List<QuestionViewDto> selectQuestionList(@RequestParam Long productId, @RequestParam int firstRecordIndex, @RequestParam Criteria criteria);

    //특정 제품에 대한 총 문의 수 가져오기
    int selectQuestionCnt(Long productId);
    
    //문의 등록
    void insertQuestion(QuestionDto questionDto);

    //문의 삭제
    void deleteQuestion(Long questionId);


}
