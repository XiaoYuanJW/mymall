package com.example.mall.controller.admin;

import com.example.mall.controller.BaseController;
import com.example.mall.entity.Admin;
import com.example.mall.service.AdminService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.util.Map;

/**
 * 后台管理-主页
 */
@Controller
public class AdminHomeController extends BaseController {
    @Resource(name = "adminService")
    private AdminService adminService;

    /**
     * 转到后台管理-主页
     * @param session
     * @param map
     * @return
     * @throws ParseException
     */
    @RequestMapping(value = "admin", method = RequestMethod.GET)
    public String goToPage(HttpSession session, Map<String, Object> map) throws ParseException {
        logger.info("获取管理员信息");
        Object adminId = checkAdmin(session);
        if (adminId == null) {
            return "redirect:/admin/login";
        }
        Admin admin = adminService.get(null, Integer.parseInt(adminId.toString()));
        map.put("admin", admin);
        logger.info("转到后台管理-主页");
        return "admin/homePage";
    }

}
