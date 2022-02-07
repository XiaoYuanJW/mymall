package com.example.mall.service.impl;

import com.example.mall.dao.OrderItemMapper;
import com.example.mall.entity.OrderItem;
import com.example.mall.service.OrderItemService;
import com.example.mall.util.PageUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service("orderItemService")
public class OrderItemServiceImpl implements OrderItemService {
    private OrderItemMapper orderItemMapper;
    @Resource(name = "orderItemMapper")
    public void setOrderItemMapper(OrderItemMapper orderItemMapper) {
        this.orderItemMapper = orderItemMapper;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Override
    public boolean add(OrderItem orderItem) {
        return orderItemMapper.insertOne(orderItem)>0;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Override
    public boolean update(OrderItem orderItem) {
        return orderItemMapper.updateOne(orderItem)>0;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Override
    public boolean deleteList(Integer[] orderItem_id_list) {
        return orderItemMapper.deleteList(orderItem_id_list)>0;
    }

    @Override
    public List<OrderItem> getList(PageUtil pageUtil) {
        return orderItemMapper.select(pageUtil);
    }

    @Override
    public List<OrderItem> getListByOrderId(Integer order_id, PageUtil pageUtil) {
        return orderItemMapper.selectByOrderId(order_id, pageUtil);
    }

    @Override
    public List<OrderItem> getListByUserId(Integer user_id, PageUtil pageUtil) {
        return orderItemMapper.selectByUserId(user_id, pageUtil);
    }

    @Override
    public List<OrderItem> getListByProductId(Integer product_id, PageUtil pageUtil) {
        return orderItemMapper.selectByProductId(product_id,pageUtil);
    }

    @Override
    public OrderItem get(Integer orderItem_id) {
        return orderItemMapper.selectOne(orderItem_id);
    }

    @Override
    public Integer getTotal() {
        return orderItemMapper.selectTotal();
    }

    @Override
    public Integer getTotalByOrderId(Integer order_id) {
        return orderItemMapper.selectTotalByOrderId(order_id);
    }

    @Override
    public Integer getTotalByUserId(Integer user_id) {
        return orderItemMapper.selectTotalByUserId(user_id);
    }

    @Override
    public Integer getSaleCountByProductId(Integer product_id) {
        return orderItemMapper.selectSaleCount(product_id);
    }
}
