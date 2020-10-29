package com.atguigu.gulimall.shop.dao;

import com.atguigu.gulimall.shop.model.SysPermission;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 用户权限dao
 *
 * @author lm
 * @since 2020/10/27 22:39
 */
@Mapper
public interface PermissionDao {
    /**
     * 根据用户ID获取权限
     *
     * @param id 用户ID
     * @return 权限集合
     */
    List<String> getUserPermissionsById(String id);

    /**
     * 根据角色ID获取权限ID
     *
     * @param roleIds 角色ID
     * @return 权限ID
     */
    List<String> getPermissionIdsByRoleIds(List<String> roleIds);

    /**
     * 根据权限ID获取权限信息
     *
     * @param permissionIds 权限ID
     * @return 权限信息
     */
    List<String> getUserPermsByPermissionIds(List<String> permissionIds);

    /**
     * 根据权限ID获取权限菜单
     *
     * @param permissionIds 权限ID
     * @return 权限菜单
     */
    List<SysPermission> getMenuByPermissionIds(List<String> permissionIds);
}
