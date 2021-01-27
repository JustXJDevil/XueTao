package com.future.sm.xt.service.impl;

import com.future.sm.xt.exception.ServiceException;
import com.future.sm.xt.mapper.ItemDescMapper;
import com.future.sm.xt.mapper.ItemMapper;
import com.future.sm.xt.pojo.Item;
import com.future.sm.xt.pojo.ItemDesc;
import com.future.sm.xt.service.ItemService;
import com.future.sm.xt.vo.EasyUITable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class ItemServiceImpl implements ItemService {
	
	@Autowired
	private ItemMapper itemMapper;
	@Autowired
	private ItemDescMapper itemDescMapper;


	@Override
	public EasyUITable findItems(Integer page,Integer rows) {
		EasyUITable easyUITable = new EasyUITable(
				itemMapper.getCounts(),
				itemMapper.findItemsByPage((page-1)*rows,rows)
		);
		return easyUITable;
	}

	@Transactional
	@Override
	public void saveItemAndItemDesc(Item item, ItemDesc itemDesc) {
		int row = itemMapper.saveItem(item);
		if (row == 0)
			throw new ServiceException("保存失败");
		itemDesc.setItemId(item.getId());
		int row1 = itemDescMapper.saveItemDesc(itemDesc);
		if (row1 == 0)
			throw new ServiceException("保存itemDesc失败");

	}

	@Transactional
	@Override
	public void updateItemAndItemDescById(Item item,ItemDesc itemDesc) {
		int row = itemMapper.updateItem(item);
		if (row == 0) {
			throw new ServiceException("更新失败");
		}
		itemDesc.setItemId(item.getId());
		int row1 = itemDescMapper.updateItemDesc(itemDesc);
		if (row1 == 0)
			throw new ServiceException("更新itemDesc失败");
	}

	/*1:正常  2:下架*/
	@Override
	public void instockByIds(Long[] ids) {
		int row = itemMapper.instockByIds(ids);
		if (row == 0)
			throw new ServiceException("下架失败");
	}

	@Override
	public void reshelfByIds(Long[] ids) {
		int row = itemMapper.reshelfByids(ids);
		if (row == 0)
			throw new ServiceException("上架失败");
	}
	@Transactional
	@Override
	public void deleteItemAndItemDescByIds(Long[] ids) {
		int row = itemMapper.deleteItemsByIds(ids);
		if (row == 0)
			throw new ServiceException("删除失败");
		int row1 = itemDescMapper.deleteItemDescByIds(ids);
		if (row1 == 0)
			throw new ServiceException("删除itemDesc失败");
	}

	@Override
	public Item findItemById(Long itemId) {
		return itemMapper.findItemById(itemId);
	}

}
