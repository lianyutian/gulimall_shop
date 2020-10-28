package com.atguigu.gulimall.shop.service;

import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 权限相关service
 *
 * @author lm
 * @since 2020/10/27 22:37
 */
public interface PermissionService {
    /**
     * 根据用户ID获取权限
     *
     * @param id 用户ID
     * @return 权限集合
     */
    List<String> getUserPermissionsByUserId(String id);
}
