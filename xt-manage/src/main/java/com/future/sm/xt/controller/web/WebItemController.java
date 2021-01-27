package com.future.sm.xt.controller.web;

import com.future.sm.xt.pojo.Item;
import com.future.sm.xt.pojo.ItemDesc;
import com.future.sm.xt.service.ItemDescService;
import com.future.sm.xt.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/web/item/")
public class WebItemController {
    @Autowired
    private ItemService itemService;
    @Autowired
    private ItemDescService itemDescService;

    //http://manage.xt.com/web/item/findItemById?itemId=562379
    @RequestMapping("findItemById")
    public Item findItemById(Long itemId){
        return itemService.findItemById(itemId);
    }

    @RequestMapping("findItemDescById")
    public ItemDesc findItemDescByItemId(Long itemId){
        return itemDescService.getItemDescByItemId(itemId);
    }
}
