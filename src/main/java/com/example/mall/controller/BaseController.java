package com.example.mall.controller;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpSession;

/**
 * 基控制器-BaseController
 */
public class BaseController {
    /*
    log4j2日志
     */
    protected Logger logger = LogManager.getLogger(BaseController.class);

    /*
    session检查管理员信息
     */
    protected Object checkAdmin(HttpSession session){
        //session.getAttribute()：从session里获取对象
        Object object = session.getAttribute("adminId");
        if(object==null){
            logger.info("无管理权限，返回管理员登陆页面");
            return null;
        }
        logger.info("当前登录管理员ID：{}", object);
        return object;
    }

    /*
    session检查用户信息
     */
    protected Object checkUser(HttpSession session){
        Object object = session.getAttribute("userId");
        if(object==null){
            logger.info("用户未登录");
            return null;
        }
        logger.info("用户已登录，用户ID：{}", object);
        return object;
    }
}
