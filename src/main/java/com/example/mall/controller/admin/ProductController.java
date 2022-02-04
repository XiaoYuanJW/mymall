package com.example.mall.controller.admin;

import com.example.mall.controller.BaseController;
import com.example.mall.entity.Category;
import com.example.mall.entity.Product;
import com.example.mall.entity.ProductImage;
import com.example.mall.service.CategoryService;
import com.example.mall.service.LastIDService;
import com.example.mall.service.ProductImageService;
import com.example.mall.service.ProductService;
import com.example.mall.util.PageUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 后台管理-产品页
 */
@Controller
public class ProductController extends BaseController {
    @Resource(name = "categoryService")
    private CategoryService categoryService;
    @Resource(name = "productService")
    private ProductService productService;
    @Resource(name = "productImageService")
    private ProductImageService productImageService;
    @Resource(name = "lastIDService")
    private LastIDService lastIDService;

    /*
    转到后台管理-产品页
     */
    @RequestMapping(value = "admin/product",method = RequestMethod.GET)
    public String goToPage(HttpSession session, Map<String, Object> map) {
        logger.info("获取产品分类列表");
        List<Category> categoryList = categoryService.getList(null, null);
        map.put("categoryList", categoryList);
        logger.info("获取前10条产品列表");
        PageUtil pageUtil = new PageUtil(0, 10);
        List<Product> productList = productService.getList(null, null, null, pageUtil);
        map.put("productList", productList);
        logger.info("获取产品总数量");
        Integer productCount = productService.getTotal(null, null);
        map.put("productCount", productCount);
        logger.info("获取分页信息");
        pageUtil.setTotal(productCount);
        map.put("pageUtil", pageUtil);
        logger.info("转到后台管理-产品页");
        return "admin/productManagePage";
    }

    /*
    转到后台管理-产品详情页
     */
    @RequestMapping(value="admin/product/{pid}",method = RequestMethod.GET)
    public String goToDetailsPage(HttpSession session, Map<String, Object> map, @PathVariable Integer pid/* 产品ID */) {
        logger.info("获取product_id为{}的产品信息",pid);
        Product product = productService.get(pid);
        Integer product_id =product.getProduct_id();
        logger.info("获取产品预览图片信息");
        List<ProductImage> singleProductImageList = productImageService.getList(product_id, (byte) 0, null);
        product.setSingleProductImageList(singleProductImageList);
        logger.info("获取产品详情图片信息");
        List<ProductImage> detailsProductImageList = productImageService.getList(product_id, (byte) 1, null);
        product.setDetailProductImageList(detailsProductImageList);
        map.put("product",product);
        logger.info("获取分类列表");
        List<Category> categoryList = categoryService.getList(null,null);
        map.put("categoryList",categoryList);
        logger.info("转到后台管理-产品详情页-ajax方式");
        return "admin/include/productDetails";
    }
}
