package com.atguigu.gulimall.shop.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.atguigu.gulimall.shop.constants.Constant;
import com.atguigu.gulimall.shop.dao.PermissionDao;
import com.atguigu.gulimall.shop.model.SelectMenuModel;
import com.atguigu.gulimall.shop.model.SysPermission;
import com.atguigu.gulimall.shop.service.PermissionService;
import com.atguigu.gulimall.shop.service.RoleService;
import com.atguigu.gulimall.shop.utils.JWTUtil;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.*;
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
    public List<SysPermission> getAllPermission() {
        return getPermissionTree(permissionDao.getAllPermission());
    }

    private List<SelectMenuModel> getSelectMenuTree(List<SysPermission> sysPermissionList) {
        if (CollectionUtils.isEmpty(sysPermissionList)) {
            return Collections.emptyList();
        }
        List<SelectMenuModel> selectMenuTree = new ArrayList<>();
        sysPermissionList.forEach(permission -> {
            // 判断是否目录
            if (permission.getType().equals(Constant.MenuType.CATALOG.getValue())) {
                SelectMenuModel selectMenu = new SelectMenuModel();
                selectMenu.setId(permission.getId());
                selectMenu.setLabel(permission.getName());
                List<SelectMenuModel> childMenu = getChildMenu(permission.getId(), sysPermissionList);
                if (!CollectionUtils.isEmpty(childMenu)) {
                    selectMenu.setChildren(childMenu);
                }
                selectMenuTree.add(selectMenu);
            }
        });
        return selectMenuTree;
    }

    /**
     * 获取权限树
     *
     * @return 权限树
     */
    private List<SysPermission> getPermissionTree(List<SysPermission> sysPermissionList) {
        if (CollectionUtils.isEmpty(sysPermissionList)) {
            return Collections.emptyList();
        }
        List<SysPermission> permissionTree = new ArrayList<>();
        sysPermissionList.forEach(permission -> {
            // 判断是否目录
            if (permission.getType().equals(Constant.MenuType.CATALOG.getValue())) {
                SysPermission sysPermission = new SysPermission();
                BeanUtils.copyProperties(permission, sysPermission);
                sysPermission.setChildren(getChild(permission.getId(), sysPermissionList));
                permissionTree.add(sysPermission);
            }
        });
        return permissionTree;
    }

    /**
     * 获取子权限（不包含按钮）
     *
     * @param id                父菜单权限id
     * @param sysPermissionList 所有菜单权限
     * @return 子权限
     */
    private List<SelectMenuModel> getChildMenu(String id, List<SysPermission> sysPermissionList) {
        List<SelectMenuModel> childMenuTree = new ArrayList<>();
        sysPermissionList.stream().filter(permission -> permission.getPid().equals(id) && permission.getType() != Constant.MenuType.BUTTON.getValue()).forEach(permission -> {
            SelectMenuModel childMenu = new SelectMenuModel();
            childMenu.setId(permission.getId());
            childMenu.setLabel(permission.getName());
            childMenu.setChildren(getChildMenu(permission.getId(), sysPermissionList));
            childMenuTree.add(childMenu);
        });
        return childMenuTree;
    }

    /**
     * 获取子权限
     *
     * @param id                父菜单权限id
     * @param sysPermissionList 所有菜单权限
     * @return 子权限
     */
    private List<SysPermission> getChild(String id, List<SysPermission> sysPermissionList) {
        List<SysPermission> childList = new ArrayList<>();
        sysPermissionList.stream().filter(permission -> permission.getPid().equals(id)).forEach(permission -> {
            SysPermission sysPermission = new SysPermission();
            BeanUtils.copyProperties(permission, sysPermission);
            sysPermission.setChildren(getChild(permission.getId(), sysPermissionList));
            childList.add(sysPermission);
        });
        return childList;
    }

    @Override
    public List<SelectMenuModel> getSelectMenuTree() {
        return getSelectMenuTree(permissionDao.getAllPermission());
    }


    @Override
    public int addPermission(String params) {
        SysPermission sysPermission = JSON.parseObject(params, new TypeReference<SysPermission>() {
        });
        sysPermission.setId(UUID.randomUUID().toString());
        sysPermission.setCreateTime(new Date());
        return permissionDao.addPermission(sysPermission);
    }

    private String getUserId() {
        String token = (String) SecurityUtils.getSubject().getPrincipal();
        return JWTUtil.getUserId(token);
    }

    private List<SysPermission> getPermissions(List<SysPermission> permissions) {
        List<SysPermission> permissionTree = new ArrayList<>();
        for (SysPermission permission : permissions) {
            permission.setChildren(getSubMenuList(permission.getId(), permissions));
            // 目录
            if (permission.getType() == Constant.MenuType.CATALOG.getValue()) {
                permissionTree.add(permission);
            }
        }
        return permissionTree;
    }

    /**
     * 二级菜单
     *
     * @param id       根菜单ID
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
