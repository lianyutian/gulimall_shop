package com.atguigu.gulimall.product.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.atguigu.common.utils.PageUtils;
import com.atguigu.common.utils.Query;

import com.atguigu.gulimall.product.dao.CategoryDao;
import com.atguigu.gulimall.product.entity.CategoryEntity;
import com.atguigu.gulimall.product.service.CategoryService;


@Service("categoryService")
public class CategoryServiceImpl extends ServiceImpl<CategoryDao, CategoryEntity> implements CategoryService {
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<CategoryEntity> page = this.page(
                new Query<CategoryEntity>().getPage(params),
                new QueryWrapper<CategoryEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public List<CategoryEntity> listWithTree() {
        // 查出所有分类
        List<CategoryEntity> categoryEntityList = baseMapper.selectList(null);
        // 返回树形分类信息
        return categoryEntityList.stream()
                .filter(categoryEntity -> categoryEntity.getParentCid() == 0)
                .map(menu -> {
                    menu.setChildren(getChildren(categoryEntityList, menu));
                    return menu;
                })
                .sorted((menu1, menu2) -> menuSort(menu1, menu2))
                .collect(Collectors.toList());
    }

    /**
     * 获取子分类信息
     *
     * @param allMenus 所有菜单
     * @param root 起始菜单
     * @return 起始菜单下所有菜单
     */
    private List<CategoryEntity> getChildren(List<CategoryEntity> allMenus, CategoryEntity root) {
        return allMenus.stream()
                .filter(menu ->
                        menu.getParentCid().equals(root.getCatId()))
                .map(menu -> {
                    menu.setChildren(getChildren(allMenus, menu));
                    return menu;
                })
                .sorted((menu1, menu2) ->  menuSort(menu1, menu2))
                .collect(Collectors.toList());
    }

    /**
     * 菜单排序
     *
     * @param menu1 菜单1
     * @param menu2 菜单2
     * @return 菜单顺序
     */
    private int menuSort(CategoryEntity menu1, CategoryEntity menu2) {
        return ((menu1.getSort() == null ? 0 : menu1.getSort())) - (menu2.getSort() == null ? 0 : menu2.getSort());
    }
}