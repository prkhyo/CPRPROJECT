package com.CPR.redHome.controller.community;

import com.CPR.redHome.dto.community.CommentsDto;
import com.CPR.redHome.dto.community.CommunityDto;
import com.CPR.redHome.paging.Criteria;
import com.CPR.redHome.paging.Pagination;
import com.CPR.redHome.service.community.CommunityService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
@Log4j2
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

        int communityTotalCnt = communityService.countAllCommunities(reply, searchType, searchKeyword);

        criteria.setCurrentPageNo(currentPageNo);
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
    public String communityDetailPage(@RequestParam Long communityId, Model model, @ModelAttribute("criteria")Criteria criteria, @ModelAttribute("reply") String reply, @ModelAttribute("orderType") String orderType,
                                      @RequestParam Integer commentCurrentPage){


        model.addAttribute("currentPageNo", criteria.getCurrentPageNo());

        communityService.updateCommunityHitCnt(communityId);

        CommunityDto communityDto = communityService.selectCommunity(communityId);

        int commentTotalCnt = communityService.countAllComments(communityId);
        Pagination pagination = communityService.setCommentPagingData(communityId, criteria, commentCurrentPage, commentTotalCnt);

        model.addAttribute("commentTotalCnt", commentTotalCnt);
        model.addAttribute("community", communityDto );
        model.addAttribute("commentPageMaker", pagination);
        model.addAttribute("commentCurrentPage",commentCurrentPage);



        return "community/community_detail";
    }


    @GetMapping("/community/communityDelete")
    public String communityDelete(@RequestParam Long communityId){

        communityService.deleteCommunity(communityId);

        return "redirect:/community/list";
    }



    @PostMapping("/community/commentInsert")
    @ResponseBody
    public void commentInsert(@RequestBody List<CommentsDto> commentsDto){

          try {

           communityService.insertComment(commentsDto);
           System.out.println("insert 성공");

          }catch (Exception e){
              e.printStackTrace();
              System.out.println("insert 실패");
          }



    }



    @PostMapping("/community/commentlist")
    @ResponseBody
    public List<CommentsDto> commentList(@RequestBody CommentsDto commentsDto){

        Long communityId = commentsDto.getCommunityId();
        int commentCurrentPage = commentsDto.getCommentCurrentPage();


        int commentTotalCnt = communityService.countAllComments(communityId);

        List<CommentsDto> commentlist = communityService.updateCommentPagingData(commentCurrentPage, commentTotalCnt, communityId);


        if(commentlist.size() < 1 ){
            communityService.updateCommunityStateWait(communityId);
        }else{
            communityService.updateCommunityStateComplete(communityId);
        }


        return commentlist;
    }


  @PostMapping("/community/commentDelete")
  @ResponseBody
  public void commentDelete(@RequestBody CommentsDto commentsDto){

       try {
          communityService.deleteComment(commentsDto.getCommentId());
           System.out.println("delete 성공");

       }catch (Exception e){
           e.printStackTrace();
           System.out.println("delete 실패");
       }

  }

    @GetMapping("/community/add")
    public String communityAddPage() {

        return "community/community_add";

    }

    @PostMapping("/community/communityInsert")
    public String communityInsert(@RequestParam(value="communityImg", required = false) MultipartFile file, String communityTitle, String  communityContents, HttpServletRequest request) {

        CommunityDto communityDto = new CommunityDto();
        communityDto.setCommunityTitle(communityTitle);
        communityDto.setCommunityContents(communityContents);
        communityDto.setMemberId(3L); //현재 세션에 로그인 된 아이디로 변경할 예정


        try {
        communityService.insertCommunity(communityDto, file, request);

        }catch (Exception e){
             e.printStackTrace();

        }


        return "redirect:/community/list";
    }

    @GetMapping("/community/edit")
    public String communityEditPage(Long communityId, Model model) {


        CommunityDto communityDto = communityService.selectCommunity(communityId);

        model.addAttribute("community", communityDto);

        return "community/community_edit";

    }

    @PostMapping("/community/communityModify")
    public String communityModify(@RequestParam(value="communityImg", required = false) MultipartFile file, String communityTitle, String  communityContents, Long communityId,  HttpServletRequest request,
                                  @RequestParam(value="fileAnnulation", required = false, defaultValue ="false")String fileAnnulation){

        CommunityDto communityDto = new CommunityDto();
        communityDto.setCommunityTitle(communityTitle);
        communityDto.setCommunityContents(communityContents);
        communityDto.setCommunityId(communityId);
        communityDto.setFileAnnulation(fileAnnulation);


        try {
            communityService.modifyCommunity(communityDto, file, request);

        }catch (Exception e){
            e.printStackTrace();

        }

        return "redirect:/community/list";
    }


}



