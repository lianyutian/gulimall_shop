package com.atguigu.gulimall.shop.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/**
 * @author lm
 * @since 2020/11/6 0:33
 */
@Setter
@Getter
public class TreeModel {
    /**
     * 菜单id
     */
    private String id;

    /**
     * 菜单名称
     */
    private String label;

    /**
     * 子菜单
     */
    private List<?> children;
}
