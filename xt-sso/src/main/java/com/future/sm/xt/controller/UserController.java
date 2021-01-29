package com.future.sm.xt.controller;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.future.sm.xt.service.UserService;
import com.future.sm.xt.vo.SysResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/user/")
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping("check/{param}/{type}")
    @ResponseBody
    public JSONPObject check(@PathVariable String param, @PathVariable Integer type,String callback){
//        SysResult sysResult = null;
//        if()
//            sysResult =  SysResult.success("OK",true);
//        else
//            sysResult = SysResult.fail("failed",false);

        return new JSONPObject(callback,SysResult.success(userService.check(param,type)));
    }

    public
}
