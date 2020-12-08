package com.atguigu.gulimall.shop.dao;

import com.atguigu.gulimall.shop.model.SysRolePermission;
import com.atguigu.gulimall.shop.model.RoleReqForm;
import com.atguigu.gulimall.shop.model.SysRole;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 角色相关dao
 *
 * @author lm
 * @since 2020/10/27 22:23
 */
@Mapper
public interface RoleDao {
    /**
     * 根据用户ID查询用户角色
     *
     * @param id 用户ID
     * @return 用户角色集合
     */
    List<String> getUserRolesById(String id);

    /**
     * 根据用户ID，获取用户相关角色ID
     *
     * @param userId 用户ID
     * @return 用户相关角色ID
     */
    List<String> getRoleIdsByUserId(String userId);

    /**
     * 根据角色ID，获取角色
     * @param rolesId
     * @return
     */
    List<String> getRolesByRoleIds(List<String> rolesId);

    /**
     * 获取角色列表
     *
     * @param roleReqForm 查询条件
     * @return 角色列表
     */
    List<SysRole> getRoleList(RoleReqForm roleReqForm);

    Integer addRole(SysRole sysRole);

    void addRolePermissions(List<SysRolePermission> rolePermissionList);
}
