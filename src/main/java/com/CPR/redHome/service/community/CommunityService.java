package com.CPR.redHome.service.community;

import com.CPR.redHome.dto.community.CommunityDto;
import com.CPR.redHome.paging.Criteria;

import java.util.HashMap;
import java.util.List;

public interface CommunityService {


    int countAllCommunities(HashMap<String,Object> map);

    List<CommunityDto> getCommunityList(HashMap<String,Object> map);

}
