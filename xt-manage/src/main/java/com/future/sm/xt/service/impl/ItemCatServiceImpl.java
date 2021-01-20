package com.future.sm.xt.service.impl;

import com.future.sm.xt.mapper.ItemCatMapper;
import com.future.sm.xt.pojo.Item;
import com.future.sm.xt.pojo.ItemCat;
import com.future.sm.xt.service.ItemCatService;
import com.future.sm.xt.vo.EasyUITree;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ItemCatServiceImpl implements ItemCatService {
    @Autowired
    private ItemCatMapper itemCatMapper;

    @Override
    public String getNameById(Long itemCatId) {
        String name = itemCatMapper.getNameById(itemCatId);
        return name;
    }

    @Override
    public List<EasyUITree> getListByParentId(Long parentId) {
        List<ItemCat> itemCats = itemCatMapper.getListByParentId(parentId);

        List<EasyUITree> treeList = new ArrayList<>(itemCats.size());
        for (ItemCat itemcat :
                itemCats) {
            EasyUITree easyUITree = new EasyUITree(
                    itemcat.getId(),
                    itemcat.getName(),
                    itemcat.getIsParent()?"closed":"open"//如果是父级 closed,否则 表示3级标题 open
            );
            treeList.add(easyUITree);
        }
        return treeList;
    }
}
