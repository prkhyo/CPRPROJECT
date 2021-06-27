package com.CPR.redHome.mapper.community;

import com.CPR.redHome.dto.community.CommunityDto;
import com.CPR.redHome.paging.Criteria;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.HashMap;
import java.util.List;

@Mapper
public interface CommunityMapper {

    //총 커뮤니티 글 수 가져오기
  int selectCommunityTotalCnt(@RequestParam String reply);

   //전체 커뮤니티 글 가져오기
  List<CommunityDto> selectAllCommunities(@RequestParam String reply, @RequestParam String orderType, @RequestParam int recordsPerPage, @RequestParam int firstRecordIndex);

}
