package com.future.sm.xt.mapper;

import com.future.sm.xt.pojo.ItemDesc;
import org.apache.ibatis.annotations.Select;

public interface ItemDescMapper {
    int saveItemDesc(ItemDesc itemDesc);

    int updateItemDesc(ItemDesc itemDesc);

    int deleteItemDescByIds(Long[] ids);

    @Select("select * from tb_item_desc where item_id = #{id}")
    ItemDesc getItemDescById(Long id);
}
