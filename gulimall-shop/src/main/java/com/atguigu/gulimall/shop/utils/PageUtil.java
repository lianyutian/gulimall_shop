package com.atguigu.gulimall.shop.utils;

import com.atguigu.gulimall.shop.model.PageInfo;
import com.github.pagehelper.Page;

import java.util.List;

/**
 * 设置分页信息
 *
 * @author lm
 * @since 2020-12-7
 */
public class PageUtil {
    private PageUtil() {
    }

    public static <T> PageInfo<T> getPageInfo(List<T> list) {
        PageInfo<T> result = new PageInfo<>();
        if (list instanceof Page) {
            Page<T> page = (Page<T>) list;
            result.getPage().setTotalRows(page.getTotal());
            result.getPage().setTotalPages(page.getPages());
            result.getPage().setPageNum(page.getPageNum());
            result.getPage().setCurPageSize(page.size());
            result.getPage().setPageSize(page.getPageSize());
            result.setDataList(page.getResult());
        }
        return result;
    }
}
