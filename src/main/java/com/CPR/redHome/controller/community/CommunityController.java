package com.CPR.redHome.controller.community;

import com.CPR.redHome.dto.community.CommentsDto;
import com.CPR.redHome.dto.community.CommunityDto;
import com.CPR.redHome.paging.Criteria;
import com.CPR.redHome.paging.Pagination;
import com.CPR.redHome.service.community.CommunityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
/*@RequestMapping("/community")*/
public class CommunityController {


    private final CommunityService communityService;


    @GetMapping("/community/list")
    public String communityListPage(@ModelAttribute("criteria")Criteria criteria, Model model, @ModelAttribute("reply") String reply, @ModelAttribute("orderType") String orderType,
                                    @RequestParam(defaultValue="1") int currentPageNo) {
       /* @ModelAttribute를 이용하면 파라미터로 전달받은 객체를 자동으로 뷰까지 전달*/

        List<CommunityDto> communityList = Collections.emptyList();

        String searchType = criteria.getSearchType();
        String searchKeyword = criteria.getSearchKeyword();

        System.out.println("searchType:"+ searchType); //test
        System.out.println("searchKeyword:"+ searchKeyword);//test
        int communityTotalCnt = communityService.countAllCommunities(reply, searchType, searchKeyword);
         System.out.println("총 개수:"+ communityTotalCnt);//test

        criteria.setCurrentPageNo(currentPageNo);
        Pagination pagination = new Pagination(criteria, communityTotalCnt, 10, 2);

        int firstRecordIndex = pagination.getFirstRecordIndex();
        int recordsPerPage = criteria.getRecordsPerPage();

      if(communityTotalCnt > 0){
          communityList = communityService.getCommunityList(reply, orderType, recordsPerPage, firstRecordIndex, searchType, searchKeyword);
       }

        System.out.println(communityList);//test


        model.addAttribute("communityList", communityList);
        model.addAttribute("pageMaker",pagination);



        return "community/community_list";
    }


    @GetMapping("/community/detail")
    public String communityDetailPage(@RequestParam Long communityId, Model model, @ModelAttribute("criteria")Criteria criteria, @ModelAttribute("reply") String reply, @ModelAttribute("orderType") String orderType,
                                      @RequestParam Integer commentCurrentPage){


        model.addAttribute("currentPageNo", criteria.getCurrentPageNo());

        communityService.updateCommunityHitCnt(communityId);

        CommunityDto communityDto = communityService.selectCommunity(communityId);

        List<CommentsDto> commentsList = Collections.emptyList();

        int commentTotalCnt = communityService.countAllComments(communityId);

        criteria.setCurrentPageNo(commentCurrentPage);
        Pagination pagination = new Pagination(criteria, commentTotalCnt, 1, 2);


        int firstRecordIndex = pagination.getFirstRecordIndex();
        int recordsPerPage = criteria.getRecordsPerPage();

        if(commentTotalCnt > 0){
            commentsList =  communityService.selectAllComments(communityId,recordsPerPage, firstRecordIndex);
        }

        model.addAttribute("community", communityDto );
        model.addAttribute("commentsList", commentsList);
        model.addAttribute("commentPageMaker", pagination);
        model.addAttribute("commentCurrentPage",commentCurrentPage);




        return "community/community_detail";
    }


    @GetMapping("/community/community_delete")
    public String communityDelete(@RequestParam Long communityId){

        communityService.deleteCommunity(communityId);

        return "redirect:/community/list";
    }



    @PostMapping("/community/commentInsert")
    @ResponseBody
    public Map<String, Object> commentInsert(@RequestParam Map<String, Object> param ){

        System.out.println(param);

         Long communityId = Long.parseLong(String.valueOf(param.get("communityId")));
         String commentContents = (String) param.get("commentContents");


        CommentsDto commentsDto = new CommentsDto();
         commentsDto.setCommunityId(communityId);
         commentsDto.setCommentContents(commentContents);
         commentsDto.setMemberId(3L); /*여기다는 현재 세션에 로그인 된 아이디로 넣어주기*/


          try {
              communityService.insertComment(commentsDto);
              param.put("result","success");
              System.out.println("insert 성공");
          }catch (Exception e){
              e.printStackTrace();
              param.put("result","fail");
              System.out.println("insert 실패");
          }

         return  param;

    }

}
