package com.CPR.redHome.mapper.question;

import com.CPR.redHome.dto.question.QuestionDto;
import com.CPR.redHome.dto.question.QuestionViewDto;
import com.CPR.redHome.paging.Criteria;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Mapper
public interface QuestionMapper {

    //특정 제품에 대한 문의 리스트 가져오기
    List<QuestionViewDto> selectQuestionList(@Param("productId") Long productId, @Param("firstRecordIndex") int firstRecordIndex, @Param("criteria") Criteria criteria);

    //문의 답변에서 문의 내용 가져오기
    QuestionViewDto selectQuestion(@RequestParam Long questionId);

    //특정 제품에 대한 총 문의 수 가져오기
    int selectQuestionCnt(Long productId);
    
    //문의 등록
    void insertQuestion(QuestionDto questionDto);

    //답변 달 때 상태 수정
    void updateQuestionState(Long questionId);

    //답변 삭제 시 상태 수정
    void questionStateAfterDelete(Long questionId);

    //문의 삭제
    void deleteQuestion(Long questionId);

    List<QuestionViewDto> selectQuestionByMemberId(Long MemberId);

}
