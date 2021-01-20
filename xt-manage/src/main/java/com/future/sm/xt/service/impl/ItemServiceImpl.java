package com.future.sm.xt.service.impl;

import com.future.sm.xt.exception.ServiceException;
import com.future.sm.xt.mapper.ItemMapper;
import com.future.sm.xt.pojo.Item;
import com.future.sm.xt.service.ItemService;
import com.future.sm.xt.vo.EasyUITable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ItemServiceImpl implements ItemService {
	
	@Autowired
	private ItemMapper itemMapper;


	@Override
	public EasyUITable findItems(Integer page,Integer rows) {
		EasyUITable easyUITable = new EasyUITable(
				itemMapper.getCounts(),
				itemMapper.findItemsByPage((page-1)*rows,rows)
		);
		return easyUITable;
	}

	@Override
	public void saveItem(Item item) {
		int row = itemMapper.saveItem(item);
		if (row == 0)
			throw new ServiceException("保存失败");

	}

	@Override
	public void updateById(Item item) {
		int row = itemMapper.updateItem(item);
		if (row == 0) {
			throw new ServiceException("更新失败");
		}
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
}
