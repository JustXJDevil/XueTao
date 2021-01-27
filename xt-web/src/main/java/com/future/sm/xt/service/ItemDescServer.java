package com.future.sm.xt.service;

import com.future.sm.xt.pojo.ItemDesc;

public interface ItemDescServer {
    ItemDesc findItemDescByItemId(Long itemId);
}
