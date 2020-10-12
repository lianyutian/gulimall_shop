package com.atguigu.gulimall.shop.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * 用户实体类
 *
 * @author lm
 * @since 2020/9/26
 */
@Setter
@Getter
public class User {
    /**
     * 用户ID
     */
    private Long userId;
    /**
     * 用户名
     */
    private String userName;
    /**
     * 手机号
     */
    private String mobile;
    /**
     * 密码
     */
    private String passWord;
    /**
     * 创建时间
     */
    private Date createTime;

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", mobile='" + mobile + '\'' +
                ", passWord='" + passWord + '\'' +
                ", createTime=" + createTime +
                '}';
    }
}
