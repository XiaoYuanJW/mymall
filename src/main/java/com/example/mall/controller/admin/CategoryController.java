package com.example.mall.controller.admin;


import com.example.mall.controller.BaseController;
import com.example.mall.entity.Category;
import com.example.mall.service.AdminService;
import com.example.mall.service.CategoryService;
import com.example.mall.service.LastIDService;
import com.example.mall.util.PageUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

import static jdk.nashorn.internal.objects.NativeDebug.map;

/**
 * 后台管理-产品类型页
 */
@Controller
public class CategoryController extends BaseController {
    @Resource(name = "adminService")
    private AdminService adminService;
    @Resource(name = "categoryService")
    private CategoryService categoryService;

    /*
    转到后台管理——产品类型页
     */
    @RequestMapping(value = "admin/category", method = RequestMethod.GET)
    public String goToPage(HttpSession session, Map<String,Object> map){
        logger.info("获取前10条产品类型列表");
        PageUtil pageUtil = new PageUtil(0,5);
        List<Category> categoryList = categoryService.getList(null, pageUtil);
        map.put("categoryList",categoryList);
        logger.info("获取产品类型总数量");
        Integer categoryCount = categoryService.getTotal(null);
        map.put("categoryCount", categoryCount);
        logger.info("获取分页信息");
        pageUtil.setTotal(categoryCount);
        map.put("pageUtil", pageUtil);
        logger.info("转到后台管理——产品类型页");
        return "admin/categoryManagePage";
    }

}
