package com.example.mall.service;

import com.example.mall.common.api.CommonResult;

/**
 * @author linxinying
 * @version 1.0.0
 * @className UmsMemberService.java
 * @description 会员管理service
 * @createTime 2021-06-01 16:33:00
 */
public interface UmsMemberService {
    /**
     * 会员验证码
     */
    CommonResult generateAuthCode(String telephone);

    /**
     * 判断验证码和手机号码是否匹配
     */
    CommonResult verifyAuthCode(String telephone, String authCode);
}
