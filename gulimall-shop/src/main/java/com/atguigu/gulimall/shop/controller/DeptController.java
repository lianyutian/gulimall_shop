package com.atguigu.gulimall.shop.controller;

import com.atguigu.gulimall.shop.common.DataResult;
import com.atguigu.gulimall.shop.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 部门管理Controller
 *
 * @author lm
 * @since 2020/12/10 20:28
 */
@RestController
@RequestMapping("/dept")
public class DeptController {
    @Autowired
    private DeptService deptService;

    /**
     * 获取所有部门列表
     *
     * @return 部门信息
     */
    @GetMapping("/getAllDeptList")
    public DataResult getAllDeptList() {
        return DataResult.success(deptService.getAllDeptList());
    }

    /**
     * 获取部门树
     *
     * @return 部门树
     */
    @GetMapping("/getDeptTree")
    public DataResult getDeptTree() {
        return DataResult.success(deptService.getDeptTree());
    }
}
