package com.luckyone.ocrserver.service;


import com.luckyone.ocrserver.model.ResponseModel;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author lj
 * @date 2022/12/16
 */
public interface ImageHandleService {

    ResponseModel<String> imageParse(String fileName, MultipartFile file) ;

}
