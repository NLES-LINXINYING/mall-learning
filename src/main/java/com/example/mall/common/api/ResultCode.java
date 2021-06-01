package com.example.mall.common.api;

/**
 * @author linxinying
 * @version 1.0.0
 * @ClassName ResultCode.java
 * @Description 枚举一些API常用的操作码
 * @createTime 2021-06-01 09:08:00
 */
public enum ResultCode implements IErrorCode {

    SUCCESS(200, "操作成功"),
    FAILED(500, "操作失败"),
    VALIDATE_FAILED(404, "操作校验失败"),
    UNAUTHORIZED(401, "暂未登录或token已过期"),
    FORBIDDEN(303, "暂未登录或token已过期");

    private long code;
    private String message;

    ResultCode(long code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public long getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
