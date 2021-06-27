package com.CPR.redHome.service.community;

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
        int CommunityCnt = communityMapper.selectCommunityTotalCnt(reply, searchType, searchKeyword);
        return CommunityCnt;
    }

    @Override
    public List<CommunityDto> getCommunityList(String reply, String orderType, int recordsPerPage, int firstRecordIndex, String searchType, String searchKeyword) {

        List<CommunityDto> communityList = communityMapper.selectAllCommunities(reply, orderType, recordsPerPage, firstRecordIndex, searchType, searchKeyword);


        return communityList;
    }


}
