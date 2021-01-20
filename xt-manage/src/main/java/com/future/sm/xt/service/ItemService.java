package com.future.sm.xt.service;

import com.future.sm.xt.vo.EasyUITable;

public interface ItemService {

    EasyUITable findItems(Integer page, Integer rows);
}
