package com.example.mall.dao;

import com.example.mall.entity.Product;
import com.example.mall.util.OrderUtil;
import com.example.mall.util.PageUtil;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ProductMapper {
    /**
     * 添加商品
     * @param product
     * @return
     */
    Integer insertOne(@Param("product") Product product);

    /**
     * 修改商品
     * @param product
     * @return
     */
    Integer updateOne(@Param("product") Product product);

    /**
     * 获取商品信息
     * @param product
     * @param product_isEnabled_array
     * @param orderUtil
     * @param pageUtil
     * @return
     */
    List<Product> select(@Param("product") Product product, @Param("product_isEnabled_array") Byte[] product_isEnabled_array, @Param("orderUtil") OrderUtil orderUtil, @Param("pageUtil") PageUtil pageUtil);

    /**
     * 获取商品品牌
     * @param product
     * @param pageUtil
     * @return
     */
    List<Product> selectBrand(@Param("product") Product product, @Param("pageUtil") PageUtil pageUtil);

    /**
     * 通过id搜索商品
     * @param product_Id
     * @return
     */
    Product selectOne(@Param("product_id") Integer product_Id);

    /**
     * 总计
     * @param product
     * @param product_isEnabled_array
     * @return
     */
    Integer selectTotal(@Param("product") Product product,@Param("product_isEnabled_array") Byte[] product_isEnabled_array);

    /**
     * 按组合条件查询商品列表
     * @param product
     * @param bytes
     * @param orderUtil
     * @param pageUtil
     * @param product_name_split
     * @return
     */
    List<Product> selectMoreList(@Param("product") Product product, @Param("product_isEnabled_array") Byte[] bytes, @Param("orderUtil") OrderUtil orderUtil, @Param("pageUtil") PageUtil pageUtil, @Param("product_name_split") String[] product_name_split);

    /**
     * 按组合条件查询商品总数量
     * @param product
     * @param product_isEnabled_array
     * @param product_name_split
     * @return
     */
    Integer selectMoreListTotal(@Param("product") Product product, @Param("product_isEnabled_array") Byte[] product_isEnabled_array, @Param("product_name_split") String[] product_name_split);
}
