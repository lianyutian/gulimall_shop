package com.atguigu.gulimall.shop.service;

import com.atguigu.gulimall.shop.model.SysPermission;

import java.util.List;

/**
 * 权限相关service
 *
 * @author lm
 * @since 2020/10/27 22:37
 */
public interface PermissionService {
    /**
     * 根据用户ID获取用户权限ID
     *
     * @param userId 用户ID
     * @return 权限ID
     */
    List<String> getPermissionIds(String userId);


    /**
     * 根据用户ID获取权限
     *
     * @param id 用户ID
     * @return 权限集合
     */
    List<String> getUserPermsByUserId(String id);

    /**
     * 根据用户ID获取用户菜单
     *
     * @param userId 用户ID
     * @return 菜单项
     */
    List<SysPermission> getMenuByUserId(String userId);

    /**
     * 获取所有菜单权限
     *
     * @return 菜单权限
     */
    List<SysPermission> getAllPermission();

    /**
     * 添加权限
     *
     * @param params 权限
     */
    void addPermission(String params);

    /**
     * 获取菜单权限树
     *
     * @return 菜单树
     */
    List<SysPermission> getPermissionMenuTree();
}
