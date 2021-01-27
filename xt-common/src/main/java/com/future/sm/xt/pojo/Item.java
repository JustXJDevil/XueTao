package com.future.sm.xt.pojo;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 由于前台后台都要使用商品，故写在common中
 */
@JsonIgnoreProperties(ignoreUnknown=true) //表示JSON转化时忽略未知属性(看下面的getImages()方法)
@TableName("tb_item")
@Data
@Accessors(chain=true)
public class Item extends BasePojo{
	@TableId(type=IdType.AUTO)
	private Long id;				//商品id
	private String title;			//商品标题
	private String sellPoint;		//商品卖点信息
	private Long   price;			//商品价格 Long > dubbo
	private Integer num;			//商品数量
	private String barcode;			//条形码
	private String image;			//商品图片信息   1.jpg,2.jpg,3.jpg
	private Long   cid;				//表示商品的分类id
	private Integer status;			//1正常，2下架
	
	//为了满足页面调用需求,添加get方法
	// (页面有个地方:${item.images[0]},我们多加一个这个get方法这样转json的时候就会多一个属性images
	// ,为了json<->Object不报错,使用上面的注解)
	public String[] getImages(){
		
		return image.split(",");
	}
}
