package com.CPR.redHome.controller.common;

import com.CPR.redHome.dto.seller.ProductRegistrationDto;
import lombok.extern.log4j.Log4j2;
import net.coobird.thumbnailator.Thumbnailator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

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

    @Value("${custom.upload.path}") // yml변수
    private String uploadPath;

    /*
     파일 저장 시 다음과 같은 사항으 고려해야한다

     1. 업로드 된 확장자가 이미지만 가능하도록 검사( 첨부파일 을 이용한 원격 셀)
        - 'shell script' 파일 등을 업로드해서 공격하는 기법이 있기에 파일을 저장하는 순간에도 검사하는 과정이 필요
        - 이 처리는 multipartFile에서 제공하난 getContentType()을 이용해서 처리할 수 있다.
     2. 동일한 이름의 파일이 업로드 된다면 기존 파일을 덮어쓰는 문제
        - UUID 를 이용하여 동일한 파일이지만 다른이름을 부여할 수 있다.
     3. 업로드된 파일을 저장하는 폴더의 용량

    */


    @PostMapping("/uploadAjax")
    public ResponseEntity<List<ProductRegistrationDto>> uploadFile(MultipartFile[] uploadFiles) {

        List<ProductRegistrationDto> registDtoList = new ArrayList<>();

        for (MultipartFile uploadFile : uploadFiles) {

            //이미지 파일만 업로드 가능
            if (uploadFile.getContentType().startsWith("image") == false) {
                log.warn("this file is not image type");
                return new ResponseEntity<>(HttpStatus.FORBIDDEN);
            }

            // 실제 파일 이름 IE나  Edge는 전체경로가 들어옴으로
            String originalName = uploadFile.getOriginalFilename();
            String fileName = originalName.substring(originalName.lastIndexOf("\\") + 1);

            log.info("fileName : " + fileName);

            //날짜 폴더 생성
            String folderPath = makeFolder();

            //UUID
            String uuid = UUID.randomUUID().toString();

            //저장할 파일 이름 중간에 "-" 를 이용해서 구분
            String savaName = uploadPath + File.separator + folderPath + File.separator + uuid + "_" + fileName;

            Path savePath = Paths.get(savaName);

            try {
                uploadFile.transferTo(savePath); //실제 이미지 저장

                // 섬네일 생성
                String thumbnailSaveName = uploadPath + File.separator + folderPath + File.separator + "s_" + uuid + "_" + fileName;

                // 썸네일 파일 이름은준간에 s_로 시작
                File thumbnailFile = new File(thumbnailSaveName);

                //섬네일 생성
                Thumbnailator.createThumbnail(savePath.toFile(), thumbnailFile, 200, 200);

                registDtoList.add(new ProductRegistrationDto(fileName, uuid, folderPath));

            } catch (IOException e) {

                e.printStackTrace();
            }

        } //end for
        return new ResponseEntity<>(registDtoList, HttpStatus.OK);
    }

    @GetMapping("/display")
    public ResponseEntity<byte[]> getFile(String fileName) {
        ResponseEntity<byte[]> result = null;
        try {
            String srcFileName = URLDecoder.decode(fileName, "UTF-8");
            log.info("fileName :" + srcFileName);

            File file = new File(uploadPath + File.separator + srcFileName);
            log.info("file : " + file);

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
    private String makeFolder() {

        String str = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
        String folderPath = str.replace("//", File.separator);

        //make folder
        File uploadPathFolder = new File(uploadPath, folderPath);
        if (uploadPathFolder.exists() == false) {
            uploadPathFolder.mkdirs();
        }
        return folderPath;
    }

    @PostMapping("/removeFile")
    public ResponseEntity<Boolean> removeFile(String fileName) {
        String srcFileName = null;
        try {
            srcFileName = URLDecoder.decode(fileName, "UTF-8");
            File file = new File(uploadPath + File.separator + srcFileName);
            boolean result = file.delete();

            File thumbnail = new File(file.getParent(), "s_" + file.getName());

            result = thumbnail.delete();

            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (UnsupportedEncodingException e) {

            e.printStackTrace();
            return new ResponseEntity<>(false, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}
