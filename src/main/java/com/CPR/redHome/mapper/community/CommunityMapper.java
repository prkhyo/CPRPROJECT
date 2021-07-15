package com.CPR.redHome.mapper.community;

import com.CPR.redHome.dto.community.CommentViewDto;
import com.CPR.redHome.dto.community.CommentsDto;
import com.CPR.redHome.dto.community.CommunityDto;
import com.CPR.redHome.dto.community.CommunityViewDto;
import com.CPR.redHome.paging.Criteria;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.List;

@Mapper
public interface CommunityMapper {

    //총 커뮤니티 글 수 가져오기
   int selectCommunityTotalCnt(@RequestParam String reply, @RequestParam Criteria criteria);

   //전체 커뮤니티 글 가져오기
   List<CommunityViewDto> selectAllCommunities(@RequestParam String reply, @RequestParam String orderType,  @RequestParam int firstRecordIndex,
                                          @RequestParam Criteria criteria);

   //특정 커뮤니티 글에 대한 총 코멘트 수 가져오기
    int selectCommentsCnt(Long communityId);

    //특정 커뮤니티 글 가져오기
    CommunityViewDto selectCommunity(Long communityId);

    //특정 커뮤니티 글에 대한 전체 코멘트 가져오기
    List<CommentViewDto> selectAllComments(@RequestParam Long communityId, @RequestParam int recordsPerPage, @RequestParam int firstRecordIndex);

    //커뮤니티 글 조회수 증가
    void updateCommunityHitCnt(Long communityId);

    //특정 커뮤니티 글 삭제
    void deleteCommunity(Long communityId);
    
    //코멘트 등록
    void insertComment(List<CommentsDto> commentsDto);

    //커뮤니티 글 답변상태 완료로 변경
    void updateCommunityStateComplete(Long communityId);

    //커뮤니티 글 답변상태 대기로 변경
    void updateCommunityStateWait(Long communityId);

    //특정 코멘트 삭제
    void deleteComment(Long commentId);

    //커뮤니티 글 등록
    void insertCommunity(CommunityDto communityDto);

    //커뮤니티 글 수정
    void modifyCommunity(CommunityDto communityDto);
}
