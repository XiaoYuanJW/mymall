package com.example.mall.service.impl;

import com.example.mall.dao.AdminMapper;
import com.example.mall.entity.Admin;
import com.example.mall.service.AdminService;
import com.example.mall.util.PageUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service("adminService")
public class AdminServiceImpl implements AdminService {
    private AdminMapper adminMapper;
    @Resource(name = "adminMapper")
    public void setAdminMapper(AdminMapper adminMapper) {
        this.adminMapper = adminMapper;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Override
    public boolean add(Admin admin) {
        return false;
    }

    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Override
    public boolean update(Admin admin) {
        return adminMapper.updateOne(admin)>0;
    }

    @Override
    public Admin get(String admin_name, Integer admin_id) {
        return adminMapper.selectOne(admin_name, admin_id);
    }

    @Override
    public Admin login(String admin_name, String admin_password) {
        return adminMapper.selectByLogin(admin_name, admin_password);
    }
}
