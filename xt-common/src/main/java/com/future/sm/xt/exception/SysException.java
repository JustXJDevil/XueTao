package com.future.sm.xt.exception;

import com.future.sm.xt.vo.SysResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
@Slf4j
public class SysException {

    @ResponseBody
    @ExceptionHandler(RuntimeException.class)
    public SysResult error(Exception e){
        e.printStackTrace();
        log.error(e.getMessage());
        return SysResult.fail();
    }
}
