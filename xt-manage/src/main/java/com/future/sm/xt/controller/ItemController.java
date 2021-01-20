package com.future.sm.xt.controller;

import com.future.sm.xt.pojo.Item;
import com.future.sm.xt.service.ItemService;
import com.future.sm.xt.vo.EasyUITable;
import com.future.sm.xt.vo.SysResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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

	@PostMapping("save")
	@ResponseBody
	public SysResult save(Item item){
		itemService.saveItem(item);
		return SysResult.success();
	}

	@ResponseBody
	@PostMapping("update")
	public SysResult update(Item item){
		itemService.updateById(item);
		return SysResult.success();
	}

	@ResponseBody
	@PostMapping("instock")
	public SysResult instock(Long... ids){
		itemService.instockByIds(ids);
		return SysResult.success();
	}

	@ResponseBody
	@PostMapping("reshelf")
	public SysResult reshelf(Long... ids){
		itemService.reshelfByIds(ids);
		return SysResult.success();
	}
}
