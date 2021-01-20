package com.future.sm.xt.mapper;

import com.future.sm.xt.pojo.ItemCat;
import com.future.sm.xt.vo.EasyUITree;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface ItemCatMapper {
    @Select("select name from tb_item_cat where id = #{itemCatId}")
    String getNameById(Long itemCatId);

    @Select("select * from tb_item_cat where parent_id = #{parentId}")
    List<ItemCat> getListByParentId(Long parentId);
}
