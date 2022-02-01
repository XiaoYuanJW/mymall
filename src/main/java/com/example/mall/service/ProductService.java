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
     * 通过id搜索商品
     * @param product_Id
     * @return
     */
    Product get(Integer product_Id);
}
