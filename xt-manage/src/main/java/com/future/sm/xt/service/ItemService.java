package com.future.sm.xt.service;

import com.future.sm.xt.pojo.Item;
import com.future.sm.xt.vo.EasyUITable;

public interface ItemService {

    EasyUITable findItems(Integer page, Integer rows);

    void saveItem(Item item);

    void updateById(Item item);

    void instockByIds(Long[] ids);

    void reshelfByIds(Long[] ids);
}
