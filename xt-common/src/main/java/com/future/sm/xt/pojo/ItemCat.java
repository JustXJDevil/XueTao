package com.future.sm.xt.pojo;

import lombok.Data;

@Data
public class ItemCat extends BasePojo{
    private Long id;
    private Long parentId;
    private String name;
    private Integer status;
    private Integer sortOrder;	//排序号
    private Boolean isParent;   //是否为父级

}
