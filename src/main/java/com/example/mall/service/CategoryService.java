package com.example.mall.service;

import com.example.mall.entity.Category;
import com.example.mall.util.PageUtil;

import java.util.List;

public interface CategoryService {
    /**
     * 添加种类信息
     * @param category
     * @return
     */
    boolean add(Category category);

    /**
     * 修改种类信息
     * @param category
     * @return
     */
    boolean update(Category category);

    /**
     * 查询种类商品
     * @param category_name
     * @param pageUtil
     * @return
     */
    List<Category> getList(String category_name, PageUtil pageUtil);

    /**
     * 通过id获取种类实体信息
     * @param category_id
     * @return
     */
    Category get(Integer category_id);

    /**
     * 通过种类名称获取商品总数
     * @param category_name
     * @return
     */
    Integer getTotal(String category_name);
}
