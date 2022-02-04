package com.example.mall.service;

import com.example.mall.entity.ProductImage;
import com.example.mall.util.PageUtil;

import java.util.List;

public interface ProductImageService {
    /**
     * 添加产品图片
     * @param productImage
     * @return
     */
    boolean add(ProductImage productImage);

    /**
     * 添加产品图片列表
     * @param productImageList
     * @return
     */
    boolean addList(List<ProductImage> productImageList);

    /**
     * 更新产品图片
     * @param productImage
     * @return
     */
    boolean update(ProductImage productImage);

    /**
     * 删除图片列表
     * @param productImage_id_list
     * @return
     */
    boolean deleteList(Integer[] productImage_id_list);

    /**
     * 查询图片列表
     * @param product_id
     * @param productImage_type
     * @param pageUtil
     * @return
     */
    List<ProductImage> getList(Integer product_id, Byte productImage_type, PageUtil pageUtil);

    /**
     * id查询图片
     * @param productImage_id
     * @return
     */
    ProductImage get(Integer productImage_id);

    /**
     * 合计图片数量
     * @param product_id
     * @param productImage_type
     * @return
     */
    Integer getTotal(Integer product_id, Byte productImage_type);
}
