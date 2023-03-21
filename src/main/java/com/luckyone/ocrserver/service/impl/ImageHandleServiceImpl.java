package com.luckyone.ocrserver.service.impl;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.IoUtil;
import cn.hutool.core.io.file.FileWriter;
import cn.hutool.core.util.StrUtil;
import com.luckyone.ocrserver.model.ResponseModel;
import com.luckyone.ocrserver.model.ResponseModels;
import com.luckyone.ocrserver.service.ImageHandleService;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.UUID;

/**
 * @author lj
 * @date 2023年3月15日
 */
@Slf4j
@Service
public class ImageHandleServiceImpl implements ImageHandleService {

    private static final String  WINDOWS_IMAGE_FILE_PATH = "D:\\";

    @Value("${com.luckyone.ocr.filePath:/tmp/}")
    private String filePath;

    //linux 系统默认UTF-8  python脚本默认输出UTF-8
    @Value("${com.luckyone.ocr.charSet:UTF-8}")
    private String charSet;

    @Value("${com.luckyone.ocr.cmd:python ./res/paddle-input.py }")
    private String cmd;

    /**
     * 图解识别解析
     * @return
     */
    @Override
    public ResponseModel imageParse(String fileName, MultipartFile multipartFile) {
        if (StrUtil.isEmpty(fileName)) {
            fileName = String.valueOf(UUID.randomUUID());
        }
        try {
            if(isWindows()){
                charSet = "GBK";
                filePath = WINDOWS_IMAGE_FILE_PATH;
                cmd = "python ./res/paddle-input.py ";
            }
            //生成文件名+"时间戳" 防止文件名重复 保存文件,解析后 接口返回
            String newFileName = System.currentTimeMillis() + fileName;
            String newFilePath = filePath + "/" + newFileName;
            File file = new File(newFilePath);
            FileWriter local = FileWriter.create(file);
            local.writeFromStream(multipartFile.getInputStream());

            //使用文件名 调用ocr
            String orcRet = ocrParse(newFilePath);

            FileUtil.del(file);
            return ResponseModels.ok(orcRet);
        } catch (IOException e) {
            log.error(e.getMessage(), e);
            return ResponseModels.exception(e.getMessage());
        }
    }

    @SneakyThrows
    private String ocrParse(String picPath){
        log.info("ocrParse识别内容 -------start-------");
        Process proc = Runtime.getRuntime().exec(cmd + picPath);
        //打印输出
        BufferedReader in = new BufferedReader(new InputStreamReader(proc.getInputStream(), charSet));
        String stdOut = IoUtil.read(in);
        log.info("ocrParse输出识别内容：" + stdOut);
        //打印报错
        BufferedReader errorIn = new BufferedReader(new InputStreamReader(proc.getErrorStream(), charSet));
        String errOut = IoUtil.read(errorIn);
        log.info("ocrParse输出识别报错：" + errOut);
        proc.waitFor();
        return stdOut;
    }



    public boolean isWindows() {
        String osName = getOsName();
        return osName != null && osName.startsWith("Windows");
    }

    public String getOsName() {
        return System.getProperty("os.name");
    }


}
