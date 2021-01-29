package com.future.sm.xt.exception;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.future.sm.xt.vo.SysResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
@Slf4j
public class SysException {

    @ResponseBody
    @ExceptionHandler(RuntimeException.class)
    public Object error(Exception e,HttpServletRequest request){//spring赋值


        String callback = request.getParameter("callback");
        /*判断是否是跨域请求：
        * 1. 不是跨域就返回SysResult
        * 2. 是跨域就返回JSONP
        * */
        if (callback==null||"".equals(callback)){//如果callback是空，则不是跨域
            e.printStackTrace();
            log.error(e.getMessage());
            return SysResult.fail();
        }else {//否则是跨域
            e.printStackTrace();
            log.error(e.getMessage());
            return new JSONPObject(callback,SysResult.fail());
        }



    }
}
