package com.future.sm.xt.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.future.sm.xt.pojo.User;
import com.future.sm.xt.service.DubboUserService;
import com.future.sm.xt.util.IPUtil;
import com.future.sm.xt.vo.SysResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/user/")
public class UserController {
    @Reference(check = false)
    private DubboUserService dubboUserService;

    @RequestMapping("{module}")
    public String login(@PathVariable String module){
        return module;
    }

    @ResponseBody
    @RequestMapping("doRegister")
    public SysResult doRegister(User user){
        dubboUserService.saveUser(user);
        return SysResult.success();
    }

    /*
    * cookie的用法
    *   setMaxAge(>0):存活时间单位为秒
    *   setMaxAge(0):立即删除
    *   setMaxAge(-1):会话关闭时删除
    * 关于path的说明：
    *   url:www.jd.com/login.html
    *   cookie.setPath("/");--该页面可以使用该cookie
    *   cookie.setPath("/abc")--该页面不能使用该cookie
    * */
    @ResponseBody
    @RequestMapping("doLogin")
    public SysResult doLogin(User user, HttpServletRequest request, HttpServletResponse response){
        //1. 动态获取IP地址
        String ip = IPUtil.getIpAddr(request);
        //2.校验
        String ticket = dubboUserService.findUserByUI(user,ip);
        if (ticket == null || "".equals(ticket)) {
            return SysResult.fail();
        }
        //3. 将ticket信息发送到cookie中
        Cookie ticketCookie = new Cookie("XT_TICKET",ticket);
        ticketCookie.setDomain("xt.com");//共享cookie给同域名服务器
        ticketCookie.setMaxAge(3600*24*7);
        ticketCookie.setPath("/");//根目录有效
        response.addCookie(ticketCookie);
        return SysResult.success();
    }
}
