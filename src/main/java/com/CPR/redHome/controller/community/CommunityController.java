package com.CPR.redHome.controller.community;

import com.CPR.redHome.dto.community.CommentsDto;
import com.CPR.redHome.dto.community.CommunityDto;
import com.CPR.redHome.paging.Criteria;
import com.CPR.redHome.paging.Pagination;
import com.CPR.redHome.service.community.CommunityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;

@Controller
@RequiredArgsConstructor
/*@RequestMapping("/community")*/
public class CommunityController {


    private final CommunityService communityService;


    @GetMapping("/community/list")
    public String communityListPage(@ModelAttribute("criteria")Criteria criteria, Model model, @ModelAttribute("reply") String reply, @ModelAttribute("orderType") String orderType) {
       /* @ModelAttribute를 이용하면 파라미터로 전달받은 객체를 자동으로 뷰까지 전달*/

        List<CommunityDto> communityList = Collections.emptyList();

        String searchType = criteria.getSearchType();
        String searchKeyword = criteria.getSearchKeyword();

        System.out.println("searchType:"+ searchType); //test
        System.out.println("searchKeyword:"+ searchKeyword);//test
        int communityTotalCnt = communityService.countAllCommunities(reply, searchType, searchKeyword);
         System.out.println("총 개수:"+ communityTotalCnt);//test

        Pagination pagination = new Pagination(criteria, communityTotalCnt, 10, 2);

        int firstRecordIndex = pagination.getFirstRecordIndex();
        int recordsPerPage = criteria.getRecordsPerPage();

      if(communityTotalCnt > 0){
          communityList = communityService.getCommunityList(reply, orderType, recordsPerPage, firstRecordIndex, searchType, searchKeyword);
       }




        model.addAttribute("communityList", communityList);
        model.addAttribute("pageMaker",pagination);




        return "community/community_list";
    }


    @GetMapping("/community/detail")
    public String communityDetailPage(@RequestParam Long communityId, Model model, @ModelAttribute("criteria")Criteria criteria, @ModelAttribute("reply") String reply, @ModelAttribute("orderType") String orderType ){

        communityService.updateCommunityHitCnt(communityId);

        CommunityDto communityDto = communityService.selectCommunity(communityId);
        List<CommentsDto> commentsList = communityService.selectComments(communityId);

        model.addAttribute("community", communityDto );
        model.addAttribute("commentsList", commentsList);

        return "community/community_detail";
    }

}
