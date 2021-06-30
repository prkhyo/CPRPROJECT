package com.CPR.redHome.service.community;

import com.CPR.redHome.dto.community.CommentsDto;
import com.CPR.redHome.dto.community.CommunityDto;
import com.CPR.redHome.paging.Criteria;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.List;

public interface CommunityService {


    int countAllCommunities(String reply, String searchType, String searchKeyword);

    List<CommunityDto> getCommunityList(String reply, String orderType, int recordsPerPage, int firstRecordIndex, String searchType, String searchKeyword);

    CommunityDto selectCommunity(Long communityId);

    List<CommentsDto> selectAllComments(Long communityId, int recordsPerPage, int firstRecordIndex);

    void updateCommunityHitCnt(Long communityId);

    void deleteCommunity(Long communityId);

    void insertComment(CommentsDto commentsDto);

    int countAllComments(Long communityId);




}
