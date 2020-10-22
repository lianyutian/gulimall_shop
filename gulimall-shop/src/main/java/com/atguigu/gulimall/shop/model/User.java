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
    private String id;

    /**
     * 用户名
     */
    public String userName;

    /**
     * 密码
     */
    public String passWord;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * token
     */
    private String acctssToken;

    /**
     * 盐值
     */
    private String salt;
}
