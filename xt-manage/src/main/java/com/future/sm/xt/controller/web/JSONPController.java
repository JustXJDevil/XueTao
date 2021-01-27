package com.future.sm.xt.controller.web;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.future.sm.xt.pojo.ItemDesc;
import com.future.sm.xt.util.ObjectMapperUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class JSONPController {

//    @RequestMapping("/web/testJSONP")
    public String testJSONP(String callback){
        ItemDesc itemDesc = new ItemDesc();
        itemDesc.setItemId(1000L);
        itemDesc.setItemDesc("itemDesc");
        return callback+"("+ ObjectMapperUtil.toJSON(itemDesc) +")";
    }

    @RequestMapping("/web/testJSONP")
    public JSONPObject testJSONPObject(String callback){
        ItemDesc itemDesc = new ItemDesc();
        itemDesc.setItemId(1000L);
        itemDesc.setItemDesc("itemDesc");
        return new JSONPObject(callback,itemDesc);
    }
}
