package com.future.sm.xt.service.impl;

import com.future.sm.xt.exception.ServiceException;
import com.future.sm.xt.mapper.ItemDescMapper;
import com.future.sm.xt.pojo.ItemDesc;
import com.future.sm.xt.service.ItemDescService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemDescServiceImpl implements ItemDescService {
    @Autowired
    private ItemDescMapper itemDescMapper;
    @Override
    public ItemDesc getItemDescByItemId(Long itemId) {
        ItemDesc itemDesc = itemDescMapper.getItemDescById(itemId);
        if (itemDesc == null)
            throw new ServiceException("没有描述");
        return itemDesc;
    }

}
