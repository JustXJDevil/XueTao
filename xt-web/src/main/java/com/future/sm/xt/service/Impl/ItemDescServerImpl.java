package com.future.sm.xt.service.Impl;

import com.future.sm.xt.annotation.CacheFind;
import com.future.sm.xt.pojo.ItemDesc;
import com.future.sm.xt.service.ItemDescServer;
import com.future.sm.xt.util.HttpClientService;
import com.future.sm.xt.util.ObjectMapperUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class ItemDescServerImpl implements ItemDescServer {
    @Autowired
    private HttpClientService httpClientService;

    @CacheFind
    @Override
    public ItemDesc findItemDescByItemId(Long itemId) {
        String url = "http://manage.xt.com/web/item/findItemDescById";
        Map<String,String> params = new HashMap<>();
        params.put("itemId",itemId+"");
        String json = httpClientService.doGet(url,params);

        return ObjectMapperUtil.toObject(json,ItemDesc.class);
    }
}
