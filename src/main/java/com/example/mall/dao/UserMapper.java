package com.example.mall.dao;

import com.example.mall.entity.Admin;
import com.example.mall.entity.User;
import com.example.mall.util.OrderUtil;
import com.example.mall.util.PageUtil;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserMapper {
    /*
    添加用户信息
     */
    Integer insertOne(@Param("user") User user);
    /*
    修改用户信息
     */
    Integer updateOne(@Param("user") User user);
    /*
    查询用户表单信息
     */
    List<User> select(@Param("user") User user, @Param("orderUtil") OrderUtil orderUtil, @Param("pageUtil") PageUtil pageUtil);
    /*
    查询用户信息
     */
    User selectOne(@Param("user_id") Integer user_id);
    /*
    用户登录验证
     */
    User selectByLogin(@Param("user_name")String user_name, @Param("user_password")String user_password);
    /*
    用户数量统计
     */
    Integer selectTotal(@Param("user") User user);
}
