package com.future.sm.xt.service;

import com.future.sm.xt.vo.EasyUITree;

import java.util.List;

public interface ItemCatService{

    String getNameById(Long itemCatId);

    List<EasyUITree> getListByParentId(Long parentId);
}
