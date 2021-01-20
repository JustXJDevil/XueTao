package com.future.sm.xt.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.future.sm.xt.pojo.Item;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface ItemMapper extends BaseMapper<Item>{

    @Select("select count(*) from tb_item")
    int getCounts();

    List<?> findItemsByPage(@Param("page") Integer page,
                            @Param("rows") Integer rows);


    int saveItem(Item item);

    int updateItem(Item item);

    int instockByIds(Long[] ids);

    int reshelfByids(Long[] ids);
}
