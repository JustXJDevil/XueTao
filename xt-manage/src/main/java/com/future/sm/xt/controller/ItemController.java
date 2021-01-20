package com.future.sm.xt.controller;

import com.future.sm.xt.service.ItemService;
import com.future.sm.xt.vo.EasyUITable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("/item/")
public class ItemController {
	
	@Autowired
	private ItemService itemService;
	
	@GetMapping("query")
	@ResponseBody
	public EasyUITable query(Integer page,Integer rows){
		return itemService.findItems(page,rows);
	}
	
	
}
