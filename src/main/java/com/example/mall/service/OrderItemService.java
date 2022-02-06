package com.example.mall.service;

import com.example.mall.entity.OrderItem;
import com.example.mall.util.PageUtil;

import java.util.Date;
import java.util.List;

public interface OrderItemService {
    /**
     * 添加订单项
     * @param orderItem
     * @return
     */
    boolean add(OrderItem orderItem);

    /**
     * 更新订单项
     * @param orderItem
     * @return
     */
    boolean update(OrderItem orderItem);

    /**
     * 列表删除订单项
     * @param orderItem_id_list
     * @return
     */
    boolean deleteList(Integer[] orderItem_id_list);

    /**
     * 查询订单项列表
     * @param pageUtil
     * @return
     */
    List<OrderItem> getList(PageUtil pageUtil);

    /**
     * 订单id查询订单项
     * @param order_id
     * @param pageUtil
     * @return
     */
    List<OrderItem> getListByOrderId(Integer order_id, PageUtil pageUtil);

    /**
     * 用户id查询订单项
     * @param user_id
     * @param pageUtil
     * @return
     */
    List<OrderItem> getListByUserId(Integer user_id, PageUtil pageUtil);

    /**
     * 产品id查询订单项
     * @param product_id
     * @param pageUtil
     * @return
     */
    List<OrderItem> getListByProductId(Integer product_id, PageUtil pageUtil);

    /**
     * 订单项id查询订单项
     * @param orderItem_id
     * @return
     */
    OrderItem get(Integer orderItem_id);

    /**
     * 合计订单项数量
     * @return
     */
    Integer getTotal();

    /**
     * 订单id合计订单项数量
     * @param order_id
     * @return
     */
    Integer getTotalByOrderId(Integer order_id);

    /**
     * 用户id合计订单项数量
     * @param user_id
     * @return
     */
    Integer getTotalByUserId(Integer user_id);

    /**
     * 产品id合计订单项数量
     * @param product_id
     * @return
     */
    Integer getSaleCountByProductId(Integer product_id);
}
