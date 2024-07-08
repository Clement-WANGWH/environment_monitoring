package com.neusoft.ysu.nepmserve.util.common;

public enum ResultCode implements IErrorCode{

    SUCCESS(200,"操作成功"),
    FAILED(500,"操作失败"),
    usernameExist(104,"用户名已存在"),
    telephoneExist(105,"手机号已被注册");

    private ResultCode(int code,String msg){
        this.code=code;
        this.msg=msg;
    }

    private int code=200;
    private String msg="成功";

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
