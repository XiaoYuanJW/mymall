package com.example.mall.service;

import com.example.mall.entity.Order;
import com.example.mall.entity.Review;
import com.example.mall.util.OrderUtil;
import com.example.mall.util.PageUtil;

import java.util.List;

public interface ReviewService {
    /**
     * 添加评论
     * @param review
     * @return
     */
    boolean add(Review review);

    /**
     * 修改评论
     * @param review
     * @return
     */
    boolean update(Review review);

    /**
     * 列表删除评论
     * @param review_id_list
     * @return
     */
    boolean deleteList(Integer[] review_id_list);


    /**
     * 查询评论列表
     * @param review
     * @param review_stars_array
     * @param orderUtil
     * @param pageUtil
     * @return
     */
    List<Review> getList(Review review,Byte[] review_stars_array,OrderUtil orderUtil,PageUtil pageUtil);

    /**
     * 用户id查询评论列表
     * @param user_id
     * @param pageUtil
     * @return
     */
    List<Review> getListByUserId(Integer user_id, PageUtil pageUtil);

    /**
     * 产品id查询评论列表
     * @param product_id
     * @param pageUtil
     * @return
     */
    List<Review> getListByProductId(Integer product_id, PageUtil pageUtil);

    /**
     * 评论id查询评论
     * @param review_id
     * @return
     */
    Review get(Integer review_id);

    /**
     * 评论数量
     * @param review
     * @return
     */
    Integer getTotal(Review review,Byte[] review_stars_array);

    /**
     * 用户评论数量合计
     * @param user_id
     * @return
     */
    Integer getTotalByUserId(Integer user_id);

    /**
     * 产品评论数量合计
     * @param product_id
     * @return
     */
    Integer getTotalByProductId(Integer product_id);

    /**
     * 订单项评论数量合计
     * @param productOrderItem_id
     * @return
     */
    Integer getTotalByOrderItemId(Integer productOrderItem_id);
}
