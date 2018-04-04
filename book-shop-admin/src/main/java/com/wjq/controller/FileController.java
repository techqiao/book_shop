package com.wjq.controller;

import com.wjq.dto.FileInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Date;

/**
 * <p>Description : book_shop
 * <p>Date : 2018/1/20 13:13
 * <p>@author : wjq
 */
@RestController
@RequestMapping("/file")
public class FileController {
    @PostMapping("/upload")
    public FileInfo update(MultipartFile file) throws IOException {
        System.out.println(file.getContentType());
        System.out.println(file.getName());
        System.out.println(file.getOriginalFilename());
        System.out.println(file.getSize());
        String path = "D:\\shuyang\\book_shop\\book-shop-admin\\src\\main\\java\\com\\wjq\\controller";
        String extention = StringUtils.substringAfterLast(file.getOriginalFilename(), ".");
        file.transferTo(new File(path, new Date().getTime() + "." + extention));
        return null;
    }

    @GetMapping("/download")
    public void download(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String filePath = "D:\\shuyang\\book_shop\\book-shop-admin\\src\\main\\java\\com\\wjq\\controller\\1516427517085.test";
        try (
                InputStream inputStream = new FileInputStream(filePath);
                OutputStream outputStream = response.getOutputStream();

//                response.setContentType("application/x-download");
//                response.addHeader("Content-Disposition","attachment,filename=test.txt");
//
//               IOUtils.copy(inputStream,outputStream);
        ) {

        }
    }
}
