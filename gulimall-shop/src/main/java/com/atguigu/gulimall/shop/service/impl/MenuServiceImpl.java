package com.atguigu.gulimall.shop.service.impl;

import com.atguigu.gulimall.shop.constants.Constant;
import com.atguigu.gulimall.shop.model.SelectMenuModel;
import com.atguigu.gulimall.shop.model.SysPermission;
import com.atguigu.gulimall.shop.service.MenuService;
import com.atguigu.gulimall.shop.service.PermissionService;
import com.atguigu.gulimall.shop.utils.JWTUtil;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 用户菜单service
 *
 * @author lm
 * @since 2020/10/29 20:07
 */
@Service
public class MenuServiceImpl implements MenuService {
    @Autowired
    private PermissionService permissionService;

    @Override
    public List<SysPermission> getMenuList() {
        // 获取当前用户ID
        String token = (String) SecurityUtils.getSubject().getPrincipal();
        String userId = JWTUtil.getUserId(token);
        // 根据用户ID获取用户对应菜单项
        List<SysPermission> permissionMenus = permissionService.getMenuByUserId(userId);
        return getMenuTreeList(permissionMenus);
    }

    @Override
    public List<SelectMenuModel> getMenus() {
        List<SysPermission> menuList = getMenuList();
        List<SelectMenuModel> list = new ArrayList<>();
        menuList.forEach(menu -> {
            SelectMenuModel menuModel = new SelectMenuModel();
            menuModel.setId(menu.getId());
            menuModel.setLabel(menu.getName());
            list.add(menuModel);
        });
        return list;
    }

    private List<SysPermission> getMenuTreeList(List<SysPermission> menuList){
        List<SysPermission> subMenuList = new ArrayList<>();
        for(SysPermission menu : menuList){
            // 目录
            if(menu.getType() == Constant.MenuType.CATALOG.getValue()){
                menu.setChildren(getSubMenuList(menu.getId(), menuList));
                subMenuList.add(menu);
            }
        }
        return subMenuList;
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
}
