package com.atguigu.gulimall.shop.service;

import com.atguigu.gulimall.shop.model.PageInfo;
import com.atguigu.gulimall.shop.model.RoleReqForm;

import java.util.List;

/**
 * 用户角色servic
 *
 * @author lm
 * @since 2020/10/27 22:20
 */
public interface RoleService {
    /**
     * 根据用户ID查询用户角色
     *
     * @param id 用户ID
     * @return 用户角色集合
     */
    List<String> getUserRolesByUserId(String id);

    /**
     * 根据用户ID获取用户角色ID
     *
     * @param userId 用户ID
     * @return 角色ID
     */
    List<String> getRoleIdsByUserId(String userId);

    /**
     * 获取角色列表
     *
     * @param roleReqForm 查询条件
     * @return 角色列表
     */
    PageInfo getRoleList(RoleReqForm roleReqForm);
}
