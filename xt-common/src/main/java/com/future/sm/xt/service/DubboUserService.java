package com.future.sm.xt.service;

import com.future.sm.xt.pojo.User;

public interface DubboUserService {

    void saveUser(User user);

    String findUserByUI(User user, String ip);
}
