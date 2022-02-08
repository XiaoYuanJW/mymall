package com.example.mall.service;

import com.example.mall.entity.Product;
import com.example.mall.util.OrderUtil;
import com.example.mall.util.PageUtil;

import java.util.List;

public interface ProductService {
    /**
     * 添加商品
     * @param product
     * @return
     */
    boolean add(Product product);

    /**
     * 修改商品
     * @param product
     * @return
     */
    boolean update(Product product);

    /**
     * 获取商品信息
     * @param product
     * @param product_isEnabled_array
     * @param orderUtil
     * @param pageUtil
     * @return
     */
    List<Product> getList(Product product, Byte[] product_isEnabled_array, OrderUtil orderUtil, PageUtil pageUtil);

    /**
     * 获取商品品牌
     * @param product
     * @param pageUtil
     * @return
     */
    List<Product> getBrand(Product product, PageUtil pageUtil);

    /**
     * 通过id搜索商品
     * @param product_Id
     * @return
     */
    Product get(Integer product_Id);
    
    /**
     * 合计
     * @param product
     * @param product_isEnabled_array
     * @return
     */
    Integer getTotal(Product product,Byte[] product_isEnabled_array);

    /**
     * 按组合条件查询商品列表
     * @param product
     * @param bytes
     * @param orderUtil
     * @param pageUtil
     * @param product_name_split
     * @return
     */
    List<Product> getMoreList(Product product, Byte[] bytes, OrderUtil orderUtil, PageUtil pageUtil, String[] product_name_split);

    /**
     * 按组合条件查询商品总数量
     * @param product
     * @param bytes
     * @param product_name_split
     * @return
     */
    Integer getMoreListTotal(Product product, Byte[] bytes, String[] product_name_split);
}
