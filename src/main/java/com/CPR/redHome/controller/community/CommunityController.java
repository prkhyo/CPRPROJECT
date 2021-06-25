package com.CPR.redHome.controller.community;

import com.CPR.redHome.dto.community.CommunityDto;
import com.CPR.redHome.paging.Criteria;
import com.CPR.redHome.paging.Pagination;
import com.CPR.redHome.service.community.CommunityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

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

        System.out.println("cri:"+criteria);


        HashMap<String,Object> map = new HashMap<String,Object>();
        map.put("reply", reply);

        Pagination pagination = new Pagination(criteria);
        int communityTotalCnt = communityService.countAllCommunities(map);
        pagination.setTotalRecordCount(communityTotalCnt);

        map.put("recordsPerPage",criteria.getRecordsPerPage());
        map.put("firstRecordIndex",pagination.getFirstRecordIndex());
        map.put("orderType",orderType);




      if(communityTotalCnt > 0){
          communityList = communityService.getCommunityList(map);
       }

        System.out.println(communityList);//


        model.addAttribute("communityList", communityList);
        model.addAttribute("pageMaker",pagination);



        return "community/community_list";
    }

}
