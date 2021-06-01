package com.example.mall.common.api;

/**
 * @author linxinying
 * @version 1.0.0
 * @ClassName CommonResult.java
 * @Description 通用返回对象
 * @createTime 2021-06-01 08:47:00
 */
public class CommonResult<T> {

    private long code;
    private String message;
    private T data;

    public CommonResult() {
    }

    public CommonResult(long code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    /**
     * @title: success
     * @description: 成功返回结果
     * @author: linxinying
     * @updateTime: 2021/6/1 8:53
     * @params: 获取的数据
     * @return: CommonResult
     */
    public static <T> CommonResult<T> success(T data) {
        return new CommonResult<T>(ResultCode.SUCCESS.getCode(), ResultCode.SUCCESS.getMessage(), data);
    }

    /**
     * @title: success
     * @description: 成功返回结果
     * @author: linxinying
     * @updateTime: 2021/6/1 8:53
     * @params: 获取的数据
     * @return: CommonResult
     */
    public static <T> CommonResult<T> success(T data, String message) {
        return new CommonResult<T>(ResultCode.SUCCESS.getCode(), message, data);
    }

    /**
     * @title: failed
     * @description: 失败返回结果
     * @author: linxinying
     * @updateTime: 2021/6/1 8:57
     * @params: 错误码
     * @return: CommonResult
     */
    public static <T> CommonResult<T> failed(IErrorCode errorCode) {
        return new CommonResult<T>(errorCode.getCode(), errorCode.getMessage(), null);
    }
    /**
     * @title: failed
     * @description: 失败返回结果
     * @author: linxinying
     * @updateTime: 2021/6/1 8:57
     * @params: 提示信息
     * @return: CommonResult
     */
    public static <T> CommonResult<T> failed(String message) {
        return new CommonResult<T>(ResultCode.FAILED.getCode(), message, null);
    }

    /**
     * @title: failed
     * @description: 失败返回结果
     * @author: linxinying
     * @updateTime: 2021/6/1 8:57
     * @params: 无
     * @return: CommonResult
     */
    public static <T> CommonResult<T> failed() {
        return failed(ResultCode.FAILED);
    }

    /**
     * @title: validateFailed
     * @description: 参数验证失败返回结果
     * @author: linxinying
     * @updateTime: 2021/6/1 8:57
     * @params: 无
     * @return: CommonResult
     */
    public static <T> CommonResult<T> validateFailed() {
        return failed(ResultCode.VALIDATE_FAILED);
    }

    /**
     * @title: validateFailed
     * @description: 参数验证失败返回结果
     * @author: linxinying
     * @updateTime: 2021/6/1 8:57
     * @params: 提示信息
     * @return: CommonResult
     */
    public static <T> CommonResult<T> validateFailed(String message) {
        return new CommonResult<T>(ResultCode.VALIDATE_FAILED.getCode(), message, null);
    }

    /**
     * @title: unauthorized
     * @description: 未登录返回结果
     * @author: linxinying
     * @updateTime: 2021/6/1 8:57
     * @params: 获取的数据
     * @return: CommonResult
     */
    public static <T> CommonResult<T> unauthorized(T data) {
        return new CommonResult<T>(ResultCode.UNAUTHORIZED.getCode(), ResultCode.UNAUTHORIZED.getMessage(), data);
    }

    /**
     * @title: forbidden
     * @description: 未授权返回结果
     * @author: linxinying
     * @updateTime: 2021/6/1 8:57
     * @params: 获取的数据
     * @return: CommonResult
     */
    public static <T> CommonResult<T> forbidden(T data) {
        return new CommonResult<T>(ResultCode.FORBIDDEN.getCode(), ResultCode.FORBIDDEN.getMessage(), data);
    }

    public long getCode() {
        return code;
    }

    public void setCode(long code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
