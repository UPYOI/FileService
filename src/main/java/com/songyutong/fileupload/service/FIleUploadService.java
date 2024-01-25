package com.songyutong.fileupload.service;

import com.songyutong.fileupload.vo.BaseResponse;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * 文件上传
 *
 * @author: songyutong
 * @date: 2024/1/25
 */
@Slf4j
@Service
public class FIleUploadService {

    @Value(value = "${file.path}")
    private String filePath;

    /**
     * 文件上传服务
     * @param multipartFile 上传文件
     * @return 返回结果
     */
    public BaseResponse uploadFile(MultipartFile multipartFile, String fileName) {
        BaseResponse baseResponse = new BaseResponse();
        try {
            File folder = new File(filePath);
            if (!folder.isDirectory()) {
                folder.mkdirs();
            }
            String oldName = multipartFile.getOriginalFilename();
            Assertions.assertNotNull(oldName);
            fileName = fileName + oldName.substring(oldName.lastIndexOf("."));

            File file = new File(folder, fileName);
            if (file.exists()) {
                baseResponse.setRetCode("10");
                baseResponse.setRetMsg("文件名已存在");
                return baseResponse;
            }
            multipartFile.transferTo(file);

            log.info("文件上传成功，文件路径：{}", file.getAbsolutePath());
            baseResponse.buildSuccess();
            return baseResponse;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
