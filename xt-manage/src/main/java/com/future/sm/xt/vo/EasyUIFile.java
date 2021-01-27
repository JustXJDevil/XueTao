package com.future.sm.xt.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EasyUIFile {
    private Integer error;      //0代表是一张图片，如果是0，前台才可以解析并显示。1代表不是图片
    private String url;         //图片的保存路径
    private Integer width;      //图片的宽度
    private Integer height;     //图片的高度

    //为了简化操作，提供以下方法
    public static EasyUIFile fail(){
        return new EasyUIFile(1,null,null,null);
    }
}
