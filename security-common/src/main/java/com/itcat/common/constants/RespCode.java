package com.itcat.common.constants;

public enum RespCode {

    //
    SUCCESS("成功", 0),
    //
    LOGIN_FAILED("登录失败", 501),
    //
    NOT_LOGIN("未登录", 1000),
    //
    ACCESS_DENIED("鉴权失败", 1001),
    //
    AUTHENTICATION_FAILURE("认证失败", 1002),

    ;
    private String msg;

    private int code;

    RespCode(String msg, int code) {
        this.msg = msg;
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public RespCode setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    public int getCode() {
        return code;
    }

    public RespCode setCode(int code) {
        this.code = code;
        return this;
    }
}
