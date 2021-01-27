package com.future.sm.xt.service;

import com.future.sm.xt.pojo.Item;
import com.future.sm.xt.pojo.ItemDesc;
import com.future.sm.xt.vo.EasyUITable;

public interface ItemService {

    EasyUITable findItems(Integer page, Integer rows);

    void saveItemAndItemDesc(Item item,ItemDesc itemDesc);

    void updateItemAndItemDescById(Item item,ItemDesc itemDesc);

    void instockByIds(Long[] ids);

    void reshelfByIds(Long[] ids);

    void deleteItemAndItemDescByIds(Long[] ids);

    Item findItemById(Long itemId);
}
