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
    User queryUser(User user);
}
