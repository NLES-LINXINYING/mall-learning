package com.example.mall.common.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author linxinying
 * @version 1.0.0
 * @className JwtTokenUtil.java
 * @description 用于生成和解析JWT token的工具类
 * @createTime 2021-06-01 19:37:00
 */
@Component
public class JwtTokenUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(JwtTokenUtil.class);
    private static final String CLAIM_KEY_USERNAME = "sub";
    private static final String CLAIM_KEY_CREATED = "created";

    @Value("${jwt.secret}")
    private String secret;
    @Value("${jwt.expiration}")
    private Long expiration;

    /**
     * @title: generateToken
     * @description: 根据负载生成JWT Token
     * @author: linxinying
     * @updateTime: 2021/6/1 19:45
     * @params: [claims]
     * @return: java.lang.String
     */
    private String generateToken(Map<String, Object> claims) {
        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(generateExpirationDate())
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    /**
     * @title: generateExpirationDate
     * @description: 生成token的过期时间
     * @author: linxinying
     * @updateTime: 2021/6/1 19:46
     * @params: []
     * @return: java.util.Date
     */
    private Date generateExpirationDate() {
        return new Date(System.currentTimeMillis() + expiration * 1000);
    }

    /**
     * @title: getClaimsFormToken
     * @description: 从token中获取JWT中的负载
     * @author: linxinying
     * @updateTime: 2021/6/1 19:48
     * @params: [token]
     * @return: Claims
     */
    private Claims getClaimsFormToken(String token) {
        Claims claims = null;
        try {
            claims = Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody();
        } catch (Exception e) {
            LOGGER.info("jwt格式验证失败:{}", token);
        }
        return claims;
    }

    /**
     * @title: getUserNameFromToken
     * @description: 从token中获取登录名
     * @author: linxinying
     * @updateTime: 2021/6/1 19:54
     * @params: [token]
     * @return: java.lang.String
     */
    public String getUserNameFromToken(String token) {
        String username;
        try {
            Claims claims = getClaimsFormToken(token);
            username = claims.getSubject();
        } catch (Exception e) {
            username = null;
        }
        return username;
    }

    /**
     * @title: validateToken
     * @description: 验证token是否还有效
     * @author: linxinying
     * @updateTime: 2021/6/1 19:57
     * @params: [客户端传入的token, 从数据库查询出来的用户信息]
     * @return: boolean
     */
    public boolean validateToken(String token, UserDetails userDetails) {
        String username = getUserNameFromToken(token);
        return username.equals(userDetails.getUsername()) && !isTokenExpired(token);
    }

    /**
     * @title: isTokenExpired
     * @description: 判断token是否已经失效
     * @author: linxinying
     * @updateTime: 2021/6/1 20:00
     * @params: [token]
     * @return: boolean
     */
    private boolean isTokenExpired(String token) {
        Date expiredDate = getExpiredDateFormToken(token);
        return expiredDate.before(new Date());
    }

    /**
     * @title: getExpiredDateFormToken
     * @description: 从token中获取过期时间
     * @author: linxinying
     * @updateTime: 2021/6/1 20:08
     * @params: [token]
     * @return: java.util.Date
     */
    private Date getExpiredDateFormToken(String token) {
        Claims claims = getClaimsFormToken(token);
        return claims.getExpiration();
    }

    /**
     * @title: generateToken
     * @description: 根据用户信息生成token
     * @author: linxinying
     * @updateTime: 2021/6/1 20:11
     * @params: [userDetails]
     * @return: java.lang.String
     */
    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        claims.put(CLAIM_KEY_USERNAME, userDetails.getUsername());
        claims.put(CLAIM_KEY_CREATED, new Date());
        return generateToken(claims);
    }

    /**
     * @title: canRefresh
     * @description: 判断token能否刷新
     * @author: linxinying
     * @updateTime: 2021/6/1 20:16
     * @params: [token]
     * @return: boolean
     */
    public boolean canRefresh(String token) {
        return !isTokenExpired(token);
    }

    /**
     * @title: refreshToken
     * @description: 刷新token
     * @author: linxinying
     * @updateTime: 2021/6/1 20:18
     * @params: [token]
     * @return: java.lang.String
     */
    public String refreshToken(String token) {
        Claims claims = getClaimsFormToken(token);
        claims.put(CLAIM_KEY_CREATED, new Date());
        return generateToken(claims);
    }
}
