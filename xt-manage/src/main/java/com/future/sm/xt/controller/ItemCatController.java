package com.future.sm.xt.controller;

import com.future.sm.xt.service.ItemCatService;
import com.future.sm.xt.vo.EasyUITree;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/item/cat/")
public class ItemCatController {
    @Autowired
    private ItemCatService itemCatService;

    @GetMapping("queryItemName")
    @ResponseBody
    public String queryItemName(Long itemCatId){
        String name = itemCatService.getNameById(itemCatId);
        return name;
    }

    @ResponseBody
    @PostMapping("list")
    public List<EasyUITree> getList(@RequestParam(name="id",defaultValue="0") Long parentId){
        return itemCatService.getListByParentId(parentId);
    }
}
