package com.future.sm.xt.controller;

import com.future.sm.xt.service.FileService;
import com.future.sm.xt.vo.EasyUIFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@Controller
public class FileController {
    @Autowired
    private FileService fileService;

    /**
     * 图片上传的入门案例：
     *
     * 1.准备文件储存目录  C:\Users\15063\Pictures\img
     * 2.获取文件名称
     * 3.利用工具API实现文件上传
     *
     * MultipartFile:
     * 在多部分请求中接收的已上传文件的表示形式。
     * 文件内容要么存储在内存中，要么临时存储在磁盘上。
     * 在这两种情况下，用户负责将文件内容复制到会话级或持久存储(如果需要的话)。
     * 临时存储将在请求处理结束时被清除。
     */
    @ResponseBody
    @RequestMapping("/file")
    public String file(MultipartFile fileImage) throws IOException {
        String root = "C:/Users/15063/Pictures/img";
        File rootDir = new File(root);
        if (!rootDir.exists())
            rootDir.mkdirs();

        String fileName = fileImage.getOriginalFilename();

        String filePath = root+"/"+fileName;
        fileImage.transferTo(new File(filePath));

        return "上传成功";
    }

    /**实现商品的文件上传*/
    @RequestMapping("/pic/upload")
    @ResponseBody
    public EasyUIFile fileUpload(MultipartFile uploadFile){
        return fileService.fileUpload(uploadFile);
    }
}
