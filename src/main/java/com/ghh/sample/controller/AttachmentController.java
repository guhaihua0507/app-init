package com.ghh.sample.controller;

import com.ghh.sample.model.vo.ResponseData;
import org.apache.commons.io.FileUtils;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.InputStreamSource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@RestController
@RequestMapping("/api/attachment")
public class AttachmentController {

    @PostMapping("/upload")
    public ResponseData upload(@RequestParam("file") MultipartFile file) throws IOException {
//        try (OutputStream out = new FileOutputStream("d:/testfile/" + file.getOriginalFilename())) {
//            IOUtils.copy(file.getInputStream(), out);
//
//        }
        FileUtils.copyInputStreamToFile(file.getInputStream(), new File("d:/testfile/" + file.getOriginalFilename()));
        return ResponseData.ok();
    }

    @GetMapping("/download")
    public ResponseEntity<InputStreamSource> download(@RequestParam String fileName) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setContentDispositionFormData("attachment", "company.txt");
        return ResponseEntity
                .ok()
                .headers(headers)
                .body(new FileSystemResource("d:/company.txt"));
    }
}
