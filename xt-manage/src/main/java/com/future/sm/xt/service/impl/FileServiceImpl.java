package com.future.sm.xt.service.impl;

import com.future.sm.xt.exception.ServiceException;
import com.future.sm.xt.service.FileService;
import com.future.sm.xt.vo.EasyUIFile;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

@Service
@PropertySource("classpath:/properties/image.properties")
public class FileServiceImpl implements FileService {
    @Value("${image.root}")
    String root;
    @Value("${image.rootURL}")
    String rootURL;
    @Override
    public EasyUIFile fileUpload(MultipartFile multipartFile) {
        //1.判断文件是否是图片
        String filename = multipartFile.getOriginalFilename();
        //将字符串转化为小写，用正则表达式判断
        filename = filename.toLowerCase();
        if (!filename.matches("^.+\\.(jpg|png|gif)$")){
            return EasyUIFile.fail();
        }

        //2.判断是否是恶意程序
        try {
            BufferedImage bufferedImage = ImageIO.read(multipartFile.getInputStream());
            int width = bufferedImage.getWidth();
            int height = bufferedImage.getHeight();
            if (width==0 || height==0)
                return EasyUIFile.fail();

        //3.实现文件储存
        //3.1 生成文件存储路径
        String dataDir = new SimpleDateFormat("yyyy/MM/dd/").format(new Date());
        File fileDir = new File(root+dataDir);
        if (!fileDir.exists())
            fileDir.mkdirs();

        //3.2 生成文件名以防止重名
        String uuidName = UUID.randomUUID().toString();
        String type = filename.substring(filename.lastIndexOf("."));
        String localFileName = uuidName+type;

        //4.上传文件
        multipartFile.transferTo(new File(root+dataDir+localFileName));

        //5.定义url虚拟地址
        String url = rootURL+dataDir+localFileName;

        return new EasyUIFile(0,
                url,
                width,
                height);

        }catch (Exception e){
            e.printStackTrace();
            throw new ServiceException(e);
        }
    }
}
