package com.atguigu.gulimall.shop.service.impl;

import com.atguigu.gulimall.shop.constants.Constant;
import com.atguigu.gulimall.shop.dao.DeptDao;
import com.atguigu.gulimall.shop.model.SysDept;
import com.atguigu.gulimall.shop.model.SysPermission;
import com.atguigu.gulimall.shop.model.TreeModel;
import com.atguigu.gulimall.shop.service.DeptService;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 部门管理服务类
 *
 * @author lm
 * @since 2020/12/10 20:35
 */
@Service
public class DeptServiceImpl implements DeptService {
    @Autowired
    private DeptDao deptDao;
    @Override
    public List<SysDept> getAllDeptList() {
        return deptDao.getAllDeptList();
    }

    @Override
    public List<TreeModel> getDeptTree() {
        List<SysDept> allDeptList = getAllDeptList();
        if (CollectionUtils.isEmpty(allDeptList)) {
            return Collections.emptyList();
        }
        List<TreeModel> childList = new ArrayList<>();
        allDeptList.forEach(dept -> {
            // 判断是总部
            if (String.valueOf(Constant.Type.HEAD_DEPT.getValue()).equals(dept.getPDeptId())) {
                TreeModel treeModel = new TreeModel();
                treeModel.setId(dept.getDeptId());
                treeModel.setLabel(dept.getDeptName());
                List<TreeModel> child = getChild(dept.getDeptId(), allDeptList);
                if (!CollectionUtils.isEmpty(child)) {
                    treeModel.setChildren(child);
                }
                childList.add(treeModel);
            }
        });
        return childList;
    }

    /**
     * 获取子部门
     *
     * @param id 父部门
     * @param sysDeptList 所有部门
     * @return 子部门
     */
    private List<TreeModel> getChild(String id, List<SysDept> sysDeptList) {
        List<TreeModel> childList = new ArrayList<>();
        sysDeptList.stream().filter(dept -> dept.getPDeptId().equals(id)).forEach(dept -> {
            TreeModel treeModel = new TreeModel();
            treeModel.setId(dept.getDeptId());
            treeModel.setLabel(dept.getDeptName());
            treeModel.setChildren(getChild(dept.getDeptId(), sysDeptList));
            childList.add(treeModel);
        });
        return childList;
    }
}
