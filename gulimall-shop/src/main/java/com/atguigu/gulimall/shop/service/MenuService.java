package com.atguigu.gulimall.shop.service;

import com.atguigu.gulimall.shop.model.TreeModel;
import com.atguigu.gulimall.shop.model.SysPermission;

import java.util.List;

/**
 * 用户菜单接口
 *
 * @author lm
 * @since 2020/10/29 19:46
 */
public interface MenuService {
    /**
     * 获取用户权限菜单
     *
     * @return 菜单
     */
    List<SysPermission> getMenuList();

    List<TreeModel> getMenus();
}
