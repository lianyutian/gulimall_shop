package com.atguigu.gulimall.shop.service.impl;

import com.atguigu.gulimall.shop.dao.PermissionDao;
import com.atguigu.gulimall.shop.model.SysPermission;
import com.atguigu.gulimall.shop.service.PermissionService;
import com.atguigu.gulimall.shop.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * @author lm
 * @since 2020/10/27 22:38
 */
@Service
public class PermissionServiceImpl implements PermissionService {
    @Autowired
    private PermissionDao permissionDao;

    @Autowired
    private RoleService roleService;

    @Override
    public List<String> getPermissionIds(String userId) {
        List<String> roleIds = roleService.getRoleIdsByUserId(userId);
        if (CollectionUtils.isEmpty(roleIds)) {
            return null;
        }
        return permissionDao.getPermissionIdsByRoleIds(roleIds);
    }

    @Override
    public List<String> getUserPermsByUserId(String userId) {
        List<String> permissionIds = getPermissionIds(userId);
        if (CollectionUtils.isEmpty(permissionIds)) {
            return null;
        }
        return permissionDao.getUserPermsByPermissionIds(permissionIds);
    }

    @Override
    public List<SysPermission> getMenuByUserId(String userId) {
        List<String> permissionIds = getPermissionIds(userId);
        if (CollectionUtils.isEmpty(permissionIds)) {
            return null;
        }
        return permissionDao.getMenuByPermissionIds(permissionIds);
    }
}
