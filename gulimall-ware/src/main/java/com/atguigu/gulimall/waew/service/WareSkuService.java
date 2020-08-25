package com.atguigu.gulimall.waew.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.atguigu.common.utils.PageUtils;
import com.atguigu.gulimall.waew.entity.WareSkuEntity;

import java.util.Map;

/**
 * 商品库存
 *
 * @author liming
 * @email 1036674967@gmail.com
 * @date 2020-08-25 23:41:15
 */
public interface WareSkuService extends IService<WareSkuEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

