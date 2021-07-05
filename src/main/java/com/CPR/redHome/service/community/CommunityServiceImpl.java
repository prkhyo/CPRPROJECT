package com.CPR.redHome.service.community;

import com.CPR.redHome.dto.community.CommentsDto;
import com.CPR.redHome.dto.community.CommunityDto;
import com.CPR.redHome.mapper.community.CommunityMapper;
import com.CPR.redHome.paging.Criteria;
import com.CPR.redHome.paging.Pagination;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CommunityServiceImpl implements CommunityService {

    private final CommunityMapper communityMapper;
    Criteria criteria;
    Pagination pagination;


    @Override
    public int countAllCommunities(String reply, String searchType, String searchKeyword) {
        int communityCnt = communityMapper.selectCommunityTotalCnt(reply, searchType, searchKeyword);
        return communityCnt;
    }

    @Override
    public List<CommunityDto> getCommunityList(String reply, String orderType, int recordsPerPage, int firstRecordIndex, String searchType, String searchKeyword) {

        List<CommunityDto> communityList = communityMapper.selectAllCommunities(reply, orderType, recordsPerPage, firstRecordIndex, searchType, searchKeyword);


        //특정 커뮤니티 글에 대한 총 코멘트 수 가져와서 초기화
        for (int i = 0; i < communityList.size(); i++) {
            Long id = communityList.get(i).getCommunityId();
            int commentsCnt = communityMapper.selectCommentsCnt(id);
            communityList.get(i).setCommentCnt(commentsCnt);

        }

        return communityList;
    }

    @Override
    public CommunityDto selectCommunity(Long communityId) {

        CommunityDto communityDto = communityMapper.selectCommunity(communityId);

        return communityDto;
    }

    @Override
    public List<CommentsDto> selectAllComments(Long communityId, int recordsPerPage, int firstRecordIndex) {

        List<CommentsDto> commentsList = communityMapper.selectAllComments(communityId, recordsPerPage, firstRecordIndex);

        if (commentsList == null) {
            communityMapper.updateCommunityStateWait(communityId);
        } else {
            communityMapper.updateCommunityStateComplete(communityId);
        }

        return commentsList;
    }

    @Override
    public void updateCommunityHitCnt(Long communityId) {
        communityMapper.updateCommunityHitCnt(communityId);
    }

    @Override
    public void deleteCommunity(Long communityId) {
        communityMapper.deleteCommunity(communityId);
    }

    @Override
    public void insertComment(CommentsDto commentsDto) {
        communityMapper.insertComment(commentsDto);
    }

    @Override //
    public int countAllComments(Long communityId) {
        int commentsCnt = communityMapper.selectCommentsCnt(communityId);
        return commentsCnt;
    }


    public Pagination setCommentPagingData(Long communityId, Criteria criteria, int commentCurrentPage, int commentTotalCnt) {

        this.criteria = criteria;

        criteria.setCurrentPageNo(commentCurrentPage);
        Pagination pagination = new Pagination(criteria, commentTotalCnt, 1, 2);

        this.pagination = pagination;


        return pagination;
    }


    public List<CommentsDto> updateCommentPagingData(int commentCurrentPage, int commentTotalCnt, Long communityId) {

        List<CommentsDto> commentlist = Collections.emptyList();

        criteria.setCurrentPageNo(commentCurrentPage);
        Pagination pagination = new Pagination(criteria, commentTotalCnt, 1, 2);
        this.pagination = pagination;

        int recordsPerPage = criteria.getRecordsPerPage();
        int firstRecordIndex = pagination.getFirstRecordIndex();

        if (commentTotalCnt > 0) {
            commentlist = communityMapper.selectAllComments(communityId, recordsPerPage, firstRecordIndex);
        }
        return commentlist;

    }

    @Override
    public void deleteComment(Long commentId) {
        communityMapper.deleteComment(commentId);

    }

    @Override
    public void insertCommunity(CommunityDto communityDto, MultipartFile file, HttpServletRequest request) throws IllegalStateException, IOException {


        String filename=null;
        if( !file.isEmpty() ) {

            String originalFileName = file.getOriginalFilename();
            String ext = FilenameUtils.getExtension(originalFileName); //확장자

            UUID uuid = UUID.randomUUID(); //UUID 구하기
            filename = uuid+"."+ext;

            file.transferTo( new File( request.getSession().getServletContext().getRealPath("/")+"fileUpload\\community\\uploadCommunityImg\\" +filename) );  // 저장할 경로를 설정

            communityDto.setCommunityImg(filename);
        }

        System.out.println(communityDto); //test

        communityMapper.insertCommunity(communityDto);



    }
}