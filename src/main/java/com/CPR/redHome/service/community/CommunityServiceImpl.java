package com.CPR.redHome.service.community;

import com.CPR.redHome.dto.community.CommentsDto;
import com.CPR.redHome.dto.community.CommunityDto;
import com.CPR.redHome.mapper.community.CommunityMapper;
import com.CPR.redHome.paging.Criteria;
import com.CPR.redHome.paging.Pagination;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommunityServiceImpl implements CommunityService {

    private final CommunityMapper communityMapper;


    @Override
    public int countAllCommunities(String reply, String searchType, String searchKeyword) {
        int communityCnt = communityMapper.selectCommunityTotalCnt(reply, searchType, searchKeyword);
        return communityCnt;
    }

    @Override
    public List<CommunityDto> getCommunityList(String reply, String orderType, int recordsPerPage, int firstRecordIndex, String searchType, String searchKeyword) {

        List<CommunityDto> communityList = communityMapper.selectAllCommunities(reply, orderType, recordsPerPage, firstRecordIndex, searchType, searchKeyword);


        //특정 커뮤니티 글에 대한 총 코멘트 수 가져와서 초기화
        for(int i = 0; i <communityList.size(); i++) {
            Long id = communityList.get(i).getCommunityId();
            int commentsCnt = communityMapper.selectCommentsCnt(id);
            communityList.get(i).setCommentCnt(commentsCnt);

        }

        return communityList;
    }

    @Override
    public CommunityDto selectCommunity(Long communityId) {

        CommunityDto communityDto = communityMapper.selectCommunity(communityId);

        return communityDto;
    }

    @Override
    public List<CommentsDto> selectComments(Long communityId) {

        List<CommentsDto> commentsList = communityMapper.selectComments(communityId);

        return commentsList;
    }

    @Override
    public void updateCommunityHitCnt(Long communityId) {
        communityMapper.updateCommunityHitCnt(communityId);
    }

    @Override
    public void deleteCommunity(Long communityId) {
        communityMapper.deleteCommunity(communityId);
    }


}
