package com.atguigu.gulimall.shop.controller;

import com.atguigu.gulimall.shop.common.DataResult;
import com.atguigu.gulimall.shop.model.RoleReqForm;
import com.atguigu.gulimall.shop.model.SysRole;
import com.atguigu.gulimall.shop.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.xml.crypto.Data;

/**
 * 角色管理
 *
 * @author lm
 * @since 2020/11/18 21:16
 */
@RestController
@RequestMapping("/role")
public class RoleController {
    @Autowired
    RoleService roleService;
    /**
     * 获取角色列表
     *
     * @return 角色
     */
    @PostMapping("/getRoleList")
    public DataResult getRoleList(@RequestBody RoleReqForm roleReqForm) {
        return DataResult.success(roleService.getRoleList(roleReqForm));
    }
}
