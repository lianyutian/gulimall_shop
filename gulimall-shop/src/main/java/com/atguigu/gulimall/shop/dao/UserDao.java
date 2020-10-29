package com.atguigu.gulimall.shop.dao;

import com.atguigu.gulimall.shop.model.SysUser;
import org.apache.ibatis.annotations.Mapper;

/**
 * 用户dao
 *
 * @author lm
 * @since 2020-10-12
 */
@Mapper
public interface UserDao {
    /**
     * 根据用户名查询用户
     *
     * @param userName 用户名
     * @return 用户信息
     */
    SysUser getUserByUserName(String userName);
}
