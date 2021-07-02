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

        int commentTotalCnt = communityService.countAllComments(communityId);
        Pagination pagination = communityService.setCommentPagingData(communityId, criteria, commentCurrentPage, commentTotalCnt);

        model.addAttribute("community", communityDto );
        model.addAttribute("commentPageMaker", pagination);
        model.addAttribute("commentCurrentPage",commentCurrentPage);



        return "community/community_detail";
    }


    @GetMapping("/community/community_delete")
    public String communityDelete(@RequestParam Long communityId){

        communityService.deleteCommunity(communityId);

        return "redirect:/community/list";
    }



    @PostMapping("/community/commentInsert/{communityId}/{memberId}/{commentContents}")
    @ResponseBody
    public Map<String, Object> commentInsert(@PathVariable Long communityId, @PathVariable Long memberId, @PathVariable String commentContents){


        Map<String, Object> map = new HashMap<String, Object>();

          try {

              CommentsDto commentsDto = new CommentsDto();
              commentsDto.setCommunityId(communityId);
              commentsDto.setCommentContents(commentContents);
              commentsDto.setMemberId(memberId); /*여기다는 현재 세션에 로그인 된 아이디로 넣어주기*/
              communityService.insertComment(commentsDto);
              map.put("result","success");

              System.out.println("insert 성공");

          }catch (Exception e){
              e.printStackTrace();
              map.put("result","fail");

              System.out.println("insert 실패");
          }



         return  map;

    }



    @PostMapping("/community/commentlist/{communityId}")
    @ResponseBody
    public List<CommentsDto> commentList(@PathVariable Long communityId, @RequestParam int commentCurrentPage ){


        int commentTotalCnt = communityService.countAllComments(communityId);

        List<CommentsDto> commentlist = communityService.updateCommentPagingData(commentCurrentPage, commentTotalCnt, communityId);



        return commentlist;
    }








}



