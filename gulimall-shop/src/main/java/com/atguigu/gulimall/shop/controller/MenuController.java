package com.atguigu.gulimall.shop.controller;

import com.atguigu.gulimall.shop.common.DataResult;
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

    /**
     * 获取菜单
     *
     * @return
     */
    @GetMapping("/getMenuList")
    public DataResult getMenuList() {
        return DataResult.success();
    }
}
