package com.atguigu.gulimall.shop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.stereotype.Component;

/**
 * 启动入口类
 *
 * @author lm
 * @since 2020/9/26
 */
@SpringBootApplication
public class GuliMallShopApplication {
    public static void main(String[] args) {
        SpringApplication.run(GuliMallShopApplication.class, args);
    }
}
