package com.example.mall.service.impl;

import com.example.mall.common.api.CommonResult;
import com.example.mall.service.RedisService;
import com.example.mall.service.UmsMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Random;

/**
 * @author linxinying
 * @version 1.0.0
 * @className UmsMemberServiceImpl.java
 * @description TODO
 * @createTime 2021-06-01 16:52:00
 */
@Service
public class UmsMemberServiceImpl implements UmsMemberService {

    @Autowired
    private RedisService redisService;
    @Value("${redis.key.prefix.authCode}")
    private String REDIS_KEY_PREFIX_AUTH_CODE;
    @Value("${redis.key.expire.authCode}")
    private Long AUTH_CODE_EXPIRE_SECONDS;

    /**
     * @title: generateAuthCode
     * @description: 获取验证码
     * @author: linxinying
     * @updateTime: 2021/6/1 17:06
     * @params: [telephone]
     * @return: com.example.mall.common.api.CommonResult
     */
    @Override
    public CommonResult generateAuthCode(String telephone) {

        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 6; i++) {
            sb.append(random.nextInt(10));
        }
        System.out.println("生成的验证码：" + sb);

        // 验证码绑定手机号存储到redis
        redisService.set(REDIS_KEY_PREFIX_AUTH_CODE + telephone, sb.toString());
        redisService.expire(REDIS_KEY_PREFIX_AUTH_CODE + telephone, AUTH_CODE_EXPIRE_SECONDS);
        return CommonResult.success(sb.toString());
    }

    /**
     * @title: verifyAuthCode
     * @description: 对输入的验证码进行校验
     * @author: linxinying
     * @updateTime: 2021/6/1 17:07
     * @params: [telephone, authCode]
     * @return: com.example.mall.common.api.CommonResult
     */
    @Override
    public CommonResult verifyAuthCode(String telephone, String authCode) {

        if (StringUtils.isEmpty(authCode)) {
            return CommonResult.failed("请输入验证码");
        }

        String realAuthCode = redisService.get(REDIS_KEY_PREFIX_AUTH_CODE + telephone);
        boolean result = authCode.equals(realAuthCode);
        System.out.println("输入的验证码：" + authCode + ", 服务器验证码：" + realAuthCode);
        if (result) {
            return CommonResult.success(null, "验证码校验成功");
        } else {
            return CommonResult.failed("验证码不正确");
        }
    }
}
