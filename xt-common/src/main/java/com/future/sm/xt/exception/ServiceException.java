package com.future.sm.xt.exception;

public class ServiceException extends RuntimeException{
    public ServiceException(){
        super();
    }
    public ServiceException(String msg){
        super(msg);
    }
    public ServiceException(Throwable t){
        super(t);
    }
}
