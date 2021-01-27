package com.future.sm.xt.service;

import com.future.sm.xt.vo.EasyUIFile;
import org.springframework.web.multipart.MultipartFile;

public interface FileService {
    EasyUIFile fileUpload(MultipartFile multipartFile);
}
