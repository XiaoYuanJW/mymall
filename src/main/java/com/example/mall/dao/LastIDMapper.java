package com.example.mall.dao;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface LastIDMapper {
    /**
     * 查询最后一位的id
     * @return
     */
    int selectLastID();
}
