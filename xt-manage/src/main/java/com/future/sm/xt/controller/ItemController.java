package com.future.sm.xt.controller;

import com.future.sm.xt.pojo.Item;
import com.future.sm.xt.pojo.ItemDesc;
import com.future.sm.xt.service.ItemDescService;
import com.future.sm.xt.service.ItemService;
import com.future.sm.xt.vo.EasyUITable;
import com.future.sm.xt.vo.SysResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/item/")
public class ItemController {
	
	@Autowired
	private ItemService itemService;
	@Autowired
	private ItemDescService itemDescService;

	@GetMapping("query")
	@ResponseBody
	public EasyUITable query(Integer page,Integer rows){
		return itemService.findItems(page,rows);
	}

	@PostMapping("save")
	@ResponseBody
	public SysResult save(Item item, ItemDesc itemDesc){
		itemService.saveItemAndItemDesc(item,itemDesc);
		return SysResult.success();
	}

	@ResponseBody
	@PostMapping("update")
	public SysResult update(Item item,ItemDesc itemDesc){
		itemService.updateItemAndItemDescById(item,itemDesc);
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

	@ResponseBody
	@PostMapping("delete")
	public SysResult delete(Long... ids){
		itemService.deleteItemAndItemDescByIds(ids);
		return SysResult.success();
	}

	@ResponseBody
	@GetMapping("query/item/desc/{itemId}")
	public SysResult getItemDesc(@PathVariable Long itemId){
		return SysResult.success(itemDescService.getItemDescByItemId(itemId));
	}
}
