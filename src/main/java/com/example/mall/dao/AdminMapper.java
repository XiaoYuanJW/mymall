package com.example.mall.dao;

import com.example.mall.entity.Admin;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface AdminMapper {
    /**
     * 添加管理员信息
     * @param admin
     * @return
     */
    Integer insertOne(@Param("admin")Admin admin);

    /**
     * 修改管理员信息
     * @param admin
     * @return
     */
    Integer updateOne(@Param("admin")Admin admin);

    /**
     * 查询管理员信息
     * @param admin_name
     * @param admin_id
     * @return
     */
    Admin selectOne(@Param("admin_name") String admin_name, @Param("admin_id") Integer admin_id);

    /**
     * 登录
     * @param admin_name
     * @param admin_password
     * @return
     */
    Admin selectByLogin(@Param("admin_name") String admin_name, @Param("admin_password") String admin_password);
}
