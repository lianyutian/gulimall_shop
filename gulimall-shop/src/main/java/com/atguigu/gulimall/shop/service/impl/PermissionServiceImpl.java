package com.atguigu.gulimall.shop.service.impl;

import com.atguigu.gulimall.shop.constants.Constant;
import com.atguigu.gulimall.shop.dao.PermissionDao;
import com.atguigu.gulimall.shop.model.SysPermission;
import com.atguigu.gulimall.shop.service.PermissionService;
import com.atguigu.gulimall.shop.service.RoleService;
import com.atguigu.gulimall.shop.utils.JWTUtil;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

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

    @Override
    public List<SysPermission> getPermissionTree() {
        // 获取当前用户ID
        String token = (String) SecurityUtils.getSubject().getPrincipal();
        String userId = JWTUtil.getUserId(token);
        List<SysPermission> permissions = getPermissions(userId);
        if (CollectionUtils.isEmpty(permissions)) {
            return Collections.emptyList();
        }
        return getPermissions(permissions);
    }

    private List<SysPermission> getPermissions(List<SysPermission> permissions){
        List<SysPermission> permissionTree = new ArrayList<>();
        for(SysPermission permission : permissions){
            permission.setChildren(getSubMenuList(permission.getId(), permissions));
            // 目录
            if(permission.getType() == Constant.MenuType.CATALOG.getValue()){
                permissionTree.add(permission);
            }
        }
        return permissionTree;
    }

    /**
     * 二级菜单
     *
     * @param id 根菜单ID
     * @param menuList 用户菜单项
     * @return 二级菜单
     */
    private List<SysPermission> getSubMenuList(String id, List<SysPermission> menuList) {
        return menuList.stream().filter(menu -> menu.getPid().equals(id)).collect(Collectors.toList());
    }

    private List<SysPermission> getPermissions(String userId) {
        List<String> permissionIds = getPermissionIds(userId);
        if (CollectionUtils.isEmpty(permissionIds)) {
            return Collections.emptyList();
        }
        return permissionDao.getPermissions(permissionIds);
    }
}
