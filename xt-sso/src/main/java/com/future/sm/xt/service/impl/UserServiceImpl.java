package com.future.sm.xt.service.impl;

import com.future.sm.xt.mapper.UserMapper;
import com.future.sm.xt.pojo.User;
import com.future.sm.xt.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Override
    public boolean check(String param, Integer type) {

        Map<Integer,String> typeMap = new HashMap<>();
        typeMap.put(1,"username");
        typeMap.put(2,"phone");
        typeMap.put(3,"email");

        User user = userMapper.check(param,typeMap.get(type));
//        int a = 1/0;
        return user==null?false:true;

//        return (type==1)?checkUsername(param):((type==2)?checkPhone(param):checkEmail(param));

    }

    private boolean checkEmail(String param) {
        return (userMapper.checkEmail(param)==null)?false:true;
    }

    private boolean checkPhone(String param) {
        return (userMapper.checkPhone(param)==null)?false:true;
    }

    private boolean checkUsername(String param) {
        return (userMapper.checkUsername(param)==null)?false:true;
    }
}
