package com.CPR.redHome.service.community;

import com.CPR.redHome.dto.community.CommentsDto;
import com.CPR.redHome.dto.community.CommunityDto;
import com.CPR.redHome.paging.Criteria;
import com.CPR.redHome.paging.Pagination;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public interface CommunityService {


    int countAllCommunities(String reply, String searchType, String searchKeyword);

    List<CommunityDto> getCommunityList(String reply, String orderType, int recordsPerPage, int firstRecordIndex, String searchType, String searchKeyword);

    CommunityDto selectCommunity(Long communityId);

    List<CommentsDto> selectAllComments(Long communityId, int recordsPerPage, int firstRecordIndex); //지금까지는서비스단에서 굳이 필요없음

    void updateCommunityHitCnt(Long communityId);

    void deleteCommunity(Long communityId);

    void insertComment(List<CommentsDto> commentsDto);

    void updateCommunityStateComplete(Long communityId);

    void updateCommunityStateWait(Long communityId);

    int countAllComments(Long communityId);

    Pagination setCommentPagingData(Long communityId, Criteria criteria, int commentCurrentPage, int commentTotalCnt);

    List<CommentsDto> updateCommentPagingData(int commentCurrentPage, int commentTotalCnt, Long communityId);

    void deleteComment(Long commentId);

    void insertCommunity(CommunityDto communityDto, MultipartFile file, HttpServletRequest request) throws IOException;

    void modifyCommunity(CommunityDto communityDto, MultipartFile file, HttpServletRequest request) throws IOException;

}
