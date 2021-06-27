package com.CPR.redHome.service.community;

import com.CPR.redHome.dto.community.CommunityDto;
import com.CPR.redHome.paging.Criteria;

import java.util.HashMap;
import java.util.List;

public interface CommunityService {


    int countAllCommunities(String reply, String searchType, String searchKeyword);

    List<CommunityDto> getCommunityList(String reply, String orderType, int recordsPerPage, int firstRecordIndex, String searchType, String searchKeyword);


}
