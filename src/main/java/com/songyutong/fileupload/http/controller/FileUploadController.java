package com.songyutong.fileupload.http.controller;

import com.songyutong.fileupload.log.LogMethod;
import com.songyutong.fileupload.service.FIleUploadService;
import com.songyutong.fileupload.vo.BaseResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * 文件上传
 *
 * @author: songyutong
 * @date: 2024/1/25
 */
@RestController
@RequestMapping(value = "file")
@Slf4j
public class FileUploadController {

    @Autowired
    private FIleUploadService fileUploadService;

    @LogMethod(desc = "文件上传")
    @PostMapping(value = "upload")
    public BaseResponse uploadFile(@RequestParam(name = "file") MultipartFile multipartFile,
                                   @RequestParam(name = "file_name") String fileName) {
        log.info("执行文件上传(FileUploadController#uploadFile)开始, 参数: [修改前文件名：{}, 文件大小：{}字节, 保存文件名：{}]", multipartFile.getOriginalFilename(), multipartFile.getSize(), fileName);
        return fileUploadService.uploadFile(multipartFile, fileName);
    }

}
