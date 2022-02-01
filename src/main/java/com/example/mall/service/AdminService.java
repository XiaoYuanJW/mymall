package com.example.mall.service;

import com.example.mall.entity.Admin;

public interface AdminService {
    /**
     * 添加管理员信息
     * @param admin
     * @return
     */
    boolean add(Admin admin);

    /**
     * 修改管理员信息
     * @param admin
     * @return
     */
    boolean update(Admin admin);

    /**
     * 查询管理员信息
     * @param admin_name
     * @param admin_id
     * @return
     */
    Admin get(String admin_name, Integer admin_id);

    /**
     * 登录
     * @param admin_name
     * @param admin_password
     * @return
     */
    Admin login(String admin_name,String admin_password);
}
