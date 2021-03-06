package com.atguigu.gulimall.shop.controller;

import com.atguigu.gulimall.shop.common.DataResult;
import com.atguigu.gulimall.shop.service.MenuService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 菜单Controller
 *
 * @author lm
 * @since 2020/10/28 20:21
 */
@RestController
@RequestMapping("/menu")
public class MenuController {
    @Autowired
    private MenuService menuService;

    /**
     * 获取菜单
     *
     * @return
     */
    @GetMapping("/getMenuList")
    public DataResult getMenuList() {
        return DataResult.success(menuService.getMenuList());
    }

    /**
     * 获取菜单
     *
     * @return
     */
    @GetMapping("/getMenus")
    public DataResult getMenus() {
        return DataResult.success(menuService.getMenus());
    }
}
