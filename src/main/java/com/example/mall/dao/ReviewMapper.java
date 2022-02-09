package com.example.mall.dao;

import com.example.mall.entity.Order;
import com.example.mall.entity.Review;
import com.example.mall.util.OrderUtil;
import com.example.mall.util.PageUtil;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ReviewMapper {
    /**
     * 添加评论
     * @param review
     * @return
     */
    Integer insertOne(@Param("review") Review review);

    /**
     * 修改评论
     * @param review
     * @return
     */
    Integer updateOne(@Param("review") Review review);

    /**
     * 列表删除评论
     * @param review_id_list
     * @return
     */
    Integer deleteList(@Param("review_id_list") Integer[] review_id_list);

    /**
     * 查询评论列表
     * @param review
     * @param pageUtil
     * @return
     */
    List<Review> select(@Param("review") Review review, @Param("review_stars_array") Byte[] review_stars_array, @Param("orderUtil") OrderUtil orderUtil, @Param("pageUtil") PageUtil pageUtil);

    /**
     * 用户id查询评论列表
     * @param user_id
     * @param pageUtil
     * @return
     */
    List<Review> selectByUserId(@Param("user_id") Integer user_id, @Param("pageUtil") PageUtil pageUtil);

    /**
     * 产品id查询评论列表
     * @param product_id
     * @param pageUtil
     * @return
     */
    List<Review> selectByProductId(@Param("product_id") Integer product_id, @Param("pageUtil") PageUtil pageUtil);

    /**
     * 评论id查询评论
     * @param review_id
     * @return
     */
    Review selectOne(@Param("review_id") Integer review_id);

    /**
     * 评论数量
     * @param review
     * @return
     */
    Integer selectTotal(@Param("review") Review review,@Param("review_stars_array") Byte[] review_stars_array);

    /**
     * 用户评论数量合计
     * @param user_id
     * @return
     */
    Integer selectTotalByUserId(@Param("user_id") Integer user_id);

    /**
     * 产品评论数量合计
     * @param product_id
     * @return
     */
    Integer selectTotalByProductId(@Param("product_id") Integer product_id);

    /**
     * 订单项评论数量合计
     * @param orderItem_id
     * @return
     */
    Integer selectTotalByOrderItemId(@Param("orderItem_id") Integer orderItem_id);
}
