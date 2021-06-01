package com.example.mall.controller;

import com.example.mall.common.api.CommonResult;
import com.example.mall.service.UmsMemberService;
import io.micrometer.core.instrument.Tags;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author linxinying
 * @version 1.0.0
 * @className UmsMemberController.java
 * @description 会员登录注册管理controller
 * @createTime 2021-06-01 16:30:00
 */
@Controller
@Api(tags = "会员登录注册管理")
@RequestMapping(value = "/sso")
public class UmsMemberController {

    @Autowired
    private UmsMemberService memberService;

    @ApiOperation("/获取验证码")
    @RequestMapping(value = "/getAuthCode", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult getAuthCode(@RequestParam("telephone") String telephone) {
        return memberService.generateAuthCode(telephone);
    }

    @ApiOperation("/判断验证码是否正确")
    @RequestMapping(value = "verifyAuthCode", method = RequestMethod.POST)
    @ResponseBody
    public CommonResult verifyAuthCode(@RequestParam("verifyCode") String telephone, @RequestParam("authCode") String authCode) {
        return memberService.verifyAuthCode(telephone, authCode);
    }
}
