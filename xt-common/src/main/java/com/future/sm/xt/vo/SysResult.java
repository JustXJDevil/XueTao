package com.future.sm.xt.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 系统级vo对象
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SysResult {
    private Integer status;     //200:success or 201:error
    private String msg;         //提示信息
    private Object data;        //返回的数据

    /*只是告知用户操作成功，不需要返回数据*/
    public static SysResult success(){
        return new SysResult(200,null,null);
    }

    /*告知用户操作成功，并返回数据*/
    public static SysResult success(Object data){
        return new SysResult(200,null,data);
    }

    /*成功之后返回msg(不能只写String参数，会和上面的Object参数方法冲突，添加一个参数，不需要时就写null)*/
    public static SysResult success(String msg,Object data){
        return new SysResult(200,null,data);
    }

    /*失败*/
    public static SysResult fail(){
        return new SysResult(201,null,null);
    }
}
