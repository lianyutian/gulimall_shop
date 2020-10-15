package com.atguigu.gulimall.shop.dao.user;

import com.atguigu.gulimall.shop.model.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * 用户登录dao
 *
 * @author lm
 * @since 2020-10-12
 */
@Mapper
public interface LoginDao {
    /**
     * 根据用户名查询用户
     *
     * @param userName 用户名
     * @return 用户信息
     */
    User queryUser(String userName);
}
