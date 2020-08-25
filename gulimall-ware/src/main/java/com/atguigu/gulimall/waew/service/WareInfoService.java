package com.atguigu.gulimall.waew.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.atguigu.common.utils.PageUtils;
import com.atguigu.gulimall.waew.entity.WareInfoEntity;

import java.util.Map;

/**
 * 仓库信息
 *
 * @author liming
 * @email 1036674967@gmail.com
 * @date 2020-08-25 23:41:15
 */
public interface WareInfoService extends IService<WareInfoEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

