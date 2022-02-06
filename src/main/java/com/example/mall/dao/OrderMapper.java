package com.example.mall.dao;

import com.example.mall.entity.Order;
import com.example.mall.util.OrderUtil;
import com.example.mall.util.PageUtil;
import com.sun.org.apache.xpath.internal.operations.Or;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

@Mapper
public interface OrderMapper {
    /**
     * 添加订单
     * @param order
     * @return
     */
    Integer insertOne(@Param("order") Order order);

    /**
     * 更新订单
     * @param order
     * @return
     */
    Integer updateOne(@Param("order") Order order);

    /**
     * 列表删除订单
     * @param order_id_list
     * @return
     */
    Integer deleteList(@Param("order_id_list")Integer[] order_id_list);

    /**
     * 查询订单列表
     * @param order
     * @param order_status_array
     * @param orderUtil
     * @param pageUtil
     * @return
     */
    List<Order> select(@Param("order") Order order,@Param("order_status_array") Byte[] order_status_array,@Param("orderUtil") OrderUtil orderUtil, @Param("pageUtil") PageUtil pageUtil);

    /**
     * 订单id查询订单
     * @param order_id
     * @return
     */
    Order selectOne(@Param("order_id") Integer order_id);

    /**
     * 订单编号查询订单
     * @param order_code
     * @return
     */
    Order selectByCode(@Param("order_code") String order_code);

    /**
     * 合计订单数量
     * @param order
     * @param order_status_array
     * @return
     */
    Integer selectTotal(@Param("order") Order order, @Param("order_status_array") Byte[] order_status_array);

//    List<OrderGroup> getTotalByDate(@Param("beginDate") Date beginDate, @Param("endDate") Date endDate);
}
