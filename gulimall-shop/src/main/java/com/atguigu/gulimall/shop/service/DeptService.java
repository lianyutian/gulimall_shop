package com.atguigu.gulimall.shop.service;

import com.atguigu.gulimall.shop.model.SysDept;
import com.atguigu.gulimall.shop.model.TreeModel;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 部门服务类接口
 *
 * @author lm
 * @since 2020/12/10 20:33
 */
public interface DeptService {
    List<SysDept> getAllDeptList();

    List<TreeModel> getDeptTree();
}
