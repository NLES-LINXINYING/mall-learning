package com.example.mall.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author admin
 * @version 1.0.0
 * @ClassName MyBatisConfig.java
 * @Description MyBatis配置类
 * @createTime 2021-05-31 20:45:00
 */
@Configuration
@MapperScan("com.example.mall.mbg.mapper")
public class MyBatisConfig {

}
