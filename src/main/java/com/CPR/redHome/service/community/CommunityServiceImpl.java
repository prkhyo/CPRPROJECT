package com.CPR.redHome.service.community;

import com.CPR.redHome.dto.community.CommentViewDto;
import com.CPR.redHome.dto.community.CommentsDto;
import com.CPR.redHome.dto.community.CommunityDto;
import com.CPR.redHome.dto.community.CommunityViewDto;
import com.CPR.redHome.mapper.community.CommunityMapper;
import com.CPR.redHome.paging.Criteria;
import com.CPR.redHome.paging.Pagination;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
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
    public int countAllCommunities(String reply, Criteria criteria) {
        int communityCnt = communityMapper.selectCommunityTotalCnt(reply, criteria);
        return communityCnt;
    }

    @Override
    public List<CommunityViewDto> getCommunityList(String reply, String orderType, int firstRecordIndex, Criteria criteria) {

        List<CommunityViewDto> communityList = communityMapper.selectAllCommunities(reply, orderType, firstRecordIndex, criteria);



        //특정 커뮤니티 글에 대한 총 코멘트 수 가져와서 초기화
        for (int i = 0; i < communityList.size(); i++) {
            Long id = communityList.get(i).getCommunityId();
            int commentsCnt = communityMapper.selectCommentsCnt(id);
            communityList.get(i).setCommentCnt(commentsCnt);

        }

        return communityList;
    }

    @Override
    public CommunityViewDto selectCommunity(Long communityId) {

        CommunityViewDto communityDto = communityMapper.selectCommunity(communityId);

        return communityDto;
    }

    @Override
    public List<CommentViewDto> selectAllComments(Long communityId, int recordsPerPage, int firstRecordIndex) {

        List<CommentViewDto> commentsList = communityMapper.selectAllComments(communityId, recordsPerPage, firstRecordIndex);

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
    public void insertComment(List<CommentsDto> commentsDto) {
        communityMapper.insertComment(commentsDto);
    }

    @Override
    public void updateCommunityStateComplete(Long communityId) {
        communityMapper.updateCommunityStateComplete(communityId);
    }

    @Override
    public void updateCommunityStateWait(Long communityId) {
     communityMapper.updateCommunityStateWait(communityId);
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


    public List<CommentViewDto> updateCommentPagingData(int commentCurrentPage, int commentTotalCnt, Long communityId) {
        
        List<CommentViewDto> commentlist = Collections.emptyList();

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


        if( !file.isEmpty() ) {

            String filename = fileUpload(file, request);
            communityDto.setCommunityImg(filename);

        }

        communityMapper.insertCommunity(communityDto);

    }

    @Override
    public void modifyCommunity(CommunityDto communityDto, MultipartFile file, HttpServletRequest request) throws IllegalStateException, IOException {


        if( !file.isEmpty() ) {

            String filename = fileUpload(file, request);
            communityDto.setCommunityImg(filename);

        }

        communityMapper.modifyCommunity(communityDto);

    }


    public String fileUpload(MultipartFile file, HttpServletRequest request) throws IOException {

        String filename=null;

        String originalFileName = file.getOriginalFilename();
        String ext = FilenameUtils.getExtension(originalFileName); //확장자

        UUID uuid = UUID.randomUUID(); //UUID 구하기
        filename = uuid+"."+ext;

        file.transferTo( new File( request.getSession().getServletContext().getRealPath("/")+"fileUpload\\community\\uploadCommunityImg\\" +filename) );  // 저장할 경로를 설정


        return filename;
    }






}