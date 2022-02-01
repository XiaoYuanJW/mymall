package com.example.mall.service.impl;

import com.example.mall.entity.Product;
import com.example.mall.service.ProductService;
import com.example.mall.util.OrderUtil;
import com.example.mall.util.PageUtil;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("productService")
public class ProductServiceImpl implements ProductService {

    @Override
    public boolean add(Product product) {
        return false;
    }

    @Override
    public boolean update(Product product) {
        return false;
    }

    @Override
    public List<Product> getList(Product product, Byte[] product_isEnabled_array, OrderUtil orderUtil, PageUtil pageUtil) {
        return null;
    }

    @Override
    public Product get(Integer product_Id) {
        return null;
    }
}
