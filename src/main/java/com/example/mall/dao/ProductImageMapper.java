package com.example.mall.dao;

import com.example.mall.entity.ProductImage;
import com.example.mall.util.PageUtil;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ProductImageMapper {
    /**
     * 添加产品图片
     * @param productImage
     * @return
     */
    Integer insertOne(@Param("productImage") ProductImage productImage);

    /**
     * 添加产品图片列表
     * @param productImageList
     * @return
     */
    Integer insertList(@Param("productImage_list") List<ProductImage> productImageList);

    /**
     * 更新产品图片
     * @param productImage
     * @return
     */
    Integer updateOne(@Param("productImage") ProductImage productImage);

    /**
     * 删除图片列表
     * @param productImage_id_list
     * @return
     */
    Integer deleteList(@Param("productImage_id_list") Integer[] productImage_id_list);

    /**
     * 查询图片列表
     * @param product_id
     * @param productImage_type
     * @param pageUtil
     * @return
     */
    List<ProductImage> select(@Param("product_id") Integer product_id, @Param("productImage_type") Byte productImage_type, @Param("pageUtil") PageUtil pageUtil);

    /**
     * id查询图片
     * @param productImage_id
     * @return
     */
    ProductImage selectOne(@Param("productImage_id") Integer productImage_id);

    /**
     * 合计图片数量
     * @param product_id
     * @param productImage_type
     * @return
     */
    Integer selectTotal(@Param("product_id") Integer product_id, @Param("productImage_type") Byte productImage_type);
}
