package com.luckyone.ocrserver.controller;


import com.luckyone.ocrserver.model.ResponseModel;
import com.luckyone.ocrserver.service.ImageHandleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * 图片处理接口
 *
 * @author lj
 * @date 2023/3/14
 */
@Slf4j
@RestController
@RequestMapping("/image")
public class ImageHandleController {

    @Resource
    private ImageHandleService imageHandleService;

    @PostMapping("/parse")
    public ResponseModel<String> imageParse(HttpServletRequest request, @RequestParam("file") MultipartFile file, @RequestParam(value = "fileName", required = false) String fileName) {
        log.info("接受图片分析请求，文件名：" + fileName);
        return imageHandleService.imageParse(fileName, file);
    }

    //测试接口
    @GetMapping("/hello")
    public String imageParse() throws InterruptedException {
        log.info("接受hello");
//        Thread.sleep(5000L);
        return "hello";
    }


}