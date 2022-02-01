package com.example.mall.dao;

import com.example.mall.entity.Category;
import com.example.mall.util.PageUtil;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CategoryMapper {
    /**
     * 添加种类信息
     * @param category
     * @return
     */
    Integer insertOne(@Param("category") Category category);

    /**
     * 修改种类信息
     * @param category
     * @return
     */
    Integer updateOne(@Param("category") Category category);

    /**
     * 查询种类商品
     * @param category_name
     * @param pageUtil
     * @return
     */
    List<Category> select(@Param("category_name") String category_name,@Param("pageUtil") PageUtil pageUtil);

    /**
     * 通过id获取种类实体信息
     * @param category_id
     * @return
     */
    Category selectOne(@Param("category_id") Integer category_id);

    /**
     * 通过种类名称获取商品总数
     * @param category_name
     * @return
     */
    Integer selectTotal(@Param("category_name") String category_name);
}
