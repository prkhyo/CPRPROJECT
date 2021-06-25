package com.CPR.redHome.mapper.community;

import com.CPR.redHome.dto.community.CommunityDto;
import com.CPR.redHome.paging.Criteria;
import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;
import java.util.List;

@Mapper
public interface CommunityMapper {

    //총 커뮤니티 글 수 가져오기
  int selectCommunityTotalCnt(HashMap<String,Object> map);

   //전체 커뮤니티 글 가져오기
  List<CommunityDto> selectAllCommunities(HashMap<String,Object> map);

}
