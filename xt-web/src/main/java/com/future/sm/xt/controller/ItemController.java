package com.future.sm.xt.controller;

import com.future.sm.xt.service.ItemDescServer;
import com.future.sm.xt.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/items/")
public class ItemController {
    @Autowired
    private ItemService itemService;
    @Autowired
    private ItemDescServer itemDescServer;

    @RequestMapping("{itemId}")
    public String itemPage(@PathVariable Long itemId, Model model){
        model.addAttribute("item",itemService.findItemById(itemId));
        model.addAttribute("itemDesc",itemDescServer.findItemDescByItemId(itemId));
        return "item";
    }
}
