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
    public int countAllCommunities(HashMap<String,Object> map) {
        int CommunityCnt = communityMapper.selectCommunityTotalCnt(map);
        return CommunityCnt;
    }

    @Override
    public List<CommunityDto> getCommunityList(HashMap<String,Object> map) {

        List<CommunityDto> communityList = communityMapper.selectAllCommunities(map);

       /* String orderType = (String) map.get("orderType");
        if (orderType.equals("newAnswer")) {
           // communityList = boardRepository.getBoardPagingNewAnswer(map);
            System.out.println("최근 답변순 정렬");
        }*/

        return communityList;
    }
}
