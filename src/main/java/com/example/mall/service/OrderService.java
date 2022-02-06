package com.example.mall.service;

import com.example.mall.entity.Order;
import com.example.mall.util.OrderUtil;
import com.example.mall.util.PageUtil;

import java.util.Date;
import java.util.List;

public interface OrderService {
    /**
     * 添加订单
     * @param order
     * @return
     */
    boolean add(Order order);

    /**
     * 更新订单
     * @param order
     * @return
     */
    boolean update(Order order);

    /**
     * 列表删除订单
     * @param order_id_list
     * @return
     */
    boolean deleteList(Integer[] order_id_list);

    /**
     * 查询订单列表
     * @param order
     * @param order_status_array
     * @param orderUtil
     * @param pageUtil
     * @return
     */
    List<Order> getList(Order order, Byte[] order_status_array, OrderUtil orderUtil, PageUtil pageUtil);

    /**
     * 订单id查询订单
     * @param order_id
     * @return
     */
    Order get(Integer order_id);

    /**
     * 订单编号查询订单
     * @param order_code
     * @return
     */
    Order getByCode(String order_code);

    /**
     * 合计订单数量
     * @param order
     * @param order_status_array
     * @return
     */
    Integer getTotal(Order order,Byte[] order_status_array);

}
