package com.example.mall.service.impl;

import com.example.mall.dao.OrderMapper;
import com.example.mall.entity.Order;
import com.example.mall.service.OrderService;
import com.example.mall.util.OrderUtil;
import com.example.mall.util.PageUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service("orderService")
public class OrderServiceImpl implements OrderService {
    private OrderMapper orderMapper;
    @Resource(name = "orderMapper")
    public void setOrderMapper(OrderMapper orderMapper) {
        this.orderMapper = orderMapper;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Override
    public boolean add(Order order) {
        return orderMapper.insertOne(order)>0;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Override
    public boolean update(Order order) {
        return orderMapper.updateOne(order)>0;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Override
    public boolean deleteList(Integer[] order_id_list) {
        return orderMapper.deleteList(order_id_list)>0;
    }

    @Override
    public List<Order> getList(Order order, Byte[] order_status_array, OrderUtil orderUtil, PageUtil pageUtil) {
        return orderMapper.select(order, order_status_array, orderUtil, pageUtil);
    }

    @Override
    public Order get(Integer order_id) {
        return orderMapper.selectOne(order_id);
    }

    @Override
    public Order getByCode(String order_code) {
        return orderMapper.selectByCode(order_code);
    }

    @Override
    public Integer getTotal(Order order, Byte[] order_status_array) {
        return orderMapper.selectTotal(order,order_status_array);
    }
}
