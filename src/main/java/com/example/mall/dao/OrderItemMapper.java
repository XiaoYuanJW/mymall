package com.example.mall.dao;

import com.example.mall.entity.OrderItem;
import com.example.mall.util.PageUtil;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

@Mapper
public interface OrderItemMapper {
    /**
     * 添加订单项
     * @param orderItem
     * @return
     */
    Integer insertOne(@Param("orderItem") OrderItem orderItem);

    /**
     * 更新订单项
     * @param orderItem
     * @return
     */
    Integer updateOne(@Param("orderItem") OrderItem orderItem);

    /**
     * 列表删除订单项
     * @param orderItem_id_list
     * @return
     */
    Integer deleteList(@Param("orderItem_id_list") Integer[] orderItem_id_list);

    /**
     * 查询订单项列表
     * @param pageUtil
     * @return
     */
    List<OrderItem> select(@Param("pageUtil") PageUtil pageUtil);

    /**
     * 订单id查询订单项
     * @param order_id
     * @param pageUtil
     * @return
     */
    List<OrderItem> selectByOrderId(@Param("order_id") Integer order_id, @Param("pageUtil") PageUtil pageUtil);

    /**
     * 用户id查询订单项
     * @param user_id
     * @param pageUtil
     * @return
     */
    List<OrderItem> selectByUserId(@Param("user_id") Integer user_id, @Param("pageUtil") PageUtil pageUtil);

    /**
     * 产品id查询订单项
     * @param product_id
     * @param pageUtil
     * @return
     */
    List<OrderItem> selectByProductId(@Param("product_id") Integer product_id, @Param("pageUtil") PageUtil pageUtil);

    /**
     * 订单项id查询订单项
     * @param orderItem_id
     * @return
     */
    OrderItem selectOne(@Param("orderItem_id") Integer orderItem_id);

    /**
     * 合计订单项数量
     * @return
     */
    Integer selectTotal();

    /**
     * 订单id合计订单项数量
     * @param order_id
     * @return
     */
    Integer selectTotalByOrderId(@Param("order_id") Integer order_id);

    /**
     * 用户id合计订单项数量
     * @param user_id
     * @return
     */
    Integer selectTotalByUserId(@Param("user_id") Integer user_id);

    /**
     * 产品id合计订单项数量
     * @param product_id
     * @return
     */
    Integer selectTotalByProductId(@Param("product_id") Integer product_id);

    /**
     * 产品id合计销售量
     * @param product_id
     * @return
     */
    Integer selectSaleCount(@Param("product_id") Integer product_id);

//    List<OrderGroup> getTotalByProductId(@Param("product_id") Integer product_id, @Param("beginDate") Date beginDate, @Param("endDate") Date endDate);

}
