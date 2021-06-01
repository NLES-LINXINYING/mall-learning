package com.example.mall.common.api;

/**
 * @author linxinying
 * @version 1.0.0
 * @ClassName IErrorCode.java
 * @Description 封装API的错误码
 * @createTime 2021-06-01 09:10:00
 */
public interface IErrorCode {
    long getCode();

    String getMessage();
}
