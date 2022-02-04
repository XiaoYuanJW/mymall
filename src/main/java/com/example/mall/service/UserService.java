package com.example.mall.service;

import com.example.mall.entity.User;
import com.example.mall.util.OrderUtil;
import com.example.mall.util.PageUtil;

import java.util.List;

public interface UserService {
    /*
    添加用户信息
     */
    boolean add(User user);
    /*
    修改用户信息
     */
    boolean update(User user);
    /*
    查询用户表单信息
     */
    List<User> getList(User user, OrderUtil orderUtil, PageUtil pageUtil);
    /*
    查询用户信息
     */
    User get(Integer user_id);
    /*
    用户登录验证
     */
    User login(String user_name, String user_password);
    /*
    用户数量统计
     */
    Integer getTotal(User user);
}
