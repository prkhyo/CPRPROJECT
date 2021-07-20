package com.CPR.redHome.controller.common;

import com.CPR.redHome.dto.common.ImageUploadDto;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@Log4j2
public class UploadController {

    @PostMapping("/uploadAjax")
    public ResponseEntity<List<ImageUploadDto>> uploadFile(MultipartFile[] uploadFiles, HttpServletRequest request) {

        String uploadPath = request.getSession().getServletContext().getRealPath("/fileUpload/product/");
        List<ImageUploadDto> registDtoList = new ArrayList<>();

        for (MultipartFile uploadFile : uploadFiles) {

            //이미지 파일만 업로드 가능
            if (uploadFile.getContentType().startsWith("image") == false) {
                log.warn("this file is not image type");
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
            }

            // 실제 파일 이름 IE나  Edge는 전체경로가 들어옴으로
            String originalName = uploadFile.getOriginalFilename();
            String fileName = originalName.substring(originalName.lastIndexOf("\\") + 1);

            //날짜 폴더 생성
            String folderPath = makeFolder(uploadPath);

            //UUID
            String uuid = UUID.randomUUID().toString();

            //저장할 파일 이름 중간에 "-" 를 이용해서 구분
            String savaName = uploadPath + File.separator + folderPath + File.separator + uuid + "_" + fileName;
            Path savePath = Paths.get(savaName);

            try {
                uploadFile.transferTo(savePath); //실제 이미지 저장

                registDtoList.add(new ImageUploadDto(fileName, uuid, folderPath));

            } catch (IOException e) {

                e.printStackTrace();
            }

        } //end for
        return new ResponseEntity<>(registDtoList, HttpStatus.OK);
    }

    // 암호화 된 이미지명 넣으면 이미지가 출력 됩니다 ^^
    @GetMapping("/display")
    public ResponseEntity<byte[]> getFile(String fileName, HttpServletRequest request) {
        ResponseEntity<byte[]> result = null;
        try {
            String srcFileName = URLDecoder.decode(fileName, "UTF-8");

            File file = new File(request.getSession().getServletContext().getRealPath("/fileUpload/product/") + File.separator + srcFileName);
            HttpHeaders header = new HttpHeaders();

            //MIME타입 처리
            header.add("Content-Type", Files.probeContentType(file.toPath()));
            // 파일 데이터 처리
            result = new ResponseEntity<>(FileCopyUtils.copyToByteArray(file), header, HttpStatus.OK);

        } catch (Exception e) {
            log.error(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return result;
    }


    // 폴더가 미존재 시 생성
    private String makeFolder(String path) {
        String str = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
        String folderPath = str.replace("//", File.separator);

        //make folder
        File uploadPathFolder = new File(path, folderPath);
        if (uploadPathFolder.exists() == false) {
            uploadPathFolder.mkdirs();
        }
        return folderPath;
    }

    @PostMapping("/removeFile")
    public ResponseEntity<Boolean> removeFile(String fileName, HttpServletRequest request) {
        String uploadPath = request.getSession().getServletContext().getRealPath("/fileUpload/product/");

        String srcFileName = null;

        try {
            srcFileName = URLDecoder.decode(fileName, "UTF-8");
            File file = new File(uploadPath + File.separator + srcFileName);
            boolean result = file.delete();

            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (UnsupportedEncodingException e) {

            e.printStackTrace();
            return new ResponseEntity<>(false, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}
