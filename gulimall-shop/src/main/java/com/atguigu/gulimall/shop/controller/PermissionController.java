package com.atguigu.gulimall.shop.controller;

import com.atguigu.gulimall.shop.common.DataResult;
import com.atguigu.gulimall.shop.model.SysPermission;
import com.atguigu.gulimall.shop.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 菜单权限管理
 *
 * @author lm
 * @since 2020/11/3 21:23
 */
@RestController
@RequestMapping("/permission")
public class PermissionController {
    @Autowired
    private PermissionService permissionService;

    /**
     * 获取所有菜单权限
     *
     * @return 菜单权限
     */
    @GetMapping("/getAllPermission")
    public DataResult<List<SysPermission>> getAllPermission() {
        return DataResult.success(permissionService.getAllPermission());
    }

    /**
     * 获取菜单权限树(到菜单)
     *
     * @return 菜单树
     */
    @GetMapping("/getPermissionMenuTree")
    public DataResult<List<SysPermission>> getPermissionMenuTree() {
        return DataResult.success(permissionService.getPermissionMenuTree());
    }

    @PostMapping("/addPermission")
    public DataResult addPermission(@RequestBody String params) {
        permissionService.addPermission(params);
        return DataResult.success();
    }
}
