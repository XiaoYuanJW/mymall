package com.example.mall.controller.admin;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.mall.controller.BaseController;
import com.example.mall.entity.*;
import com.example.mall.service.LastIDService;
import com.example.mall.service.ProductService;
import com.example.mall.service.ReviewService;
import com.example.mall.service.UserService;
import com.example.mall.util.OrderUtil;
import com.example.mall.util.PageUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Array;
import java.net.URLDecoder;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * 后台管理-评论页
 */
@Controller
public class ReviewController extends BaseController {
    @Resource(name = "reviewService")
    private ReviewService reviewService;
    @Resource(name = "lastIDService")
    private LastIDService lastIDService;
    @Resource(name = "userService")
    private UserService userService;
    @Resource(name = "productService")
    private ProductService productService;

    /*
    转到后台管理-评论信息页
     */
    @RequestMapping(value = "admin/review", method = RequestMethod.GET)
    public String goToPage(HttpSession session, Map<String, Object> map) {
        logger.info("获取前10条评论信息列表");
        PageUtil pageUtil = new PageUtil(0, 10);
        List<Review> reviewList = reviewService.getList(null,null,null,null);
        if (reviewList != null) {
            logger.info("获取评论详情");
            for (Review review : reviewList) {
                Integer userId = review.getReview_user().getUser_id();
                User user = userService.get(userId);
                Integer productId = review.getReview_product().getProduct_id();
                Product product = productService.get(productId);
                review.setReview_user(user).setReview_product(product);
            }
        }
        map.put("reviewList",reviewList);
        logger.info("获取评论总数量");
        Integer reviewCount = reviewService.getTotal(null,null);
        map.put("reviewCount", reviewCount);
        logger.info("获取分页信息");
        pageUtil.setTotal(reviewCount);
        map.put("pageUtil", pageUtil);
        logger.info("转到后台管理-打赏信息页");
        return "admin/reviewManagePage";
    }


    /*
    按条件查询评论
     */
    @ResponseBody
    @RequestMapping(value = "admin/review/{index}/{count}", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
    public String getRewardBySearch(@RequestParam(required = false) String review_content/* 评论内容 */,
                                    @RequestParam(required = false) Byte[] review_stars_array/* 评论星级数组 */,
                                    @RequestParam(required = false) String orderBy/* 排序字段 */,
                                    @RequestParam(required = false,defaultValue = "true") Boolean isDesc/* 是否倒序 */,
                                    @PathVariable Integer index/* 页数 */,
                                    @PathVariable Integer count/* 行数 */) throws UnsupportedEncodingException {
        if (review_stars_array != null && (review_stars_array.length <= 0 || review_stars_array.length >=5)) {
            review_stars_array = null;
        }
        System.out.println(Arrays.toString(review_stars_array));
        if (review_content != null) {
            review_content = "".equals(review_content) ? null : URLDecoder.decode(review_content, "UTF-8");
        }
        if (orderBy != null && "".equals(orderBy)) {
            orderBy = null;
        }

        Review review = new Review().setReview_content(review_content);
        OrderUtil orderUtil = null;
        if (orderBy != null) {
            logger.info("根据{}排序，是否倒序:{}",orderBy,isDesc);
            orderUtil = new OrderUtil(orderBy, isDesc);
        }
        JSONObject object = new JSONObject();
        logger.info("按条件获取第{}页的{}条评论", index + 1, count);
        PageUtil pageUtil = new PageUtil(index, count);
        List<Review> reviewList = reviewService.getList(review,review_stars_array,orderUtil,pageUtil);
        if (reviewList != null) {
            logger.info("获取评论详情");
            for (Review r : reviewList) {
                Integer userId = r.getReview_user().getUser_id();
                User user = userService.get(userId);
                Integer productId = r.getReview_product().getProduct_id();
                Product product = productService.get(productId);
                r.setReview_user(user).setReview_product(product);
            }
        }
        object.put("reviewList", JSONArray.parseArray(JSON.toJSONString(reviewList)));
        logger.info("按条件获取评论总条数");
        Integer reviewCount = reviewService.getTotal(review,review_stars_array);
        object.put("reviewCount", reviewCount);
        logger.info("获取分页信息");
        pageUtil.setTotal(reviewCount);
        object.put("totalPage", pageUtil.getTotalPage());
        object.put("pageUtil", pageUtil);
        return object.toJSONString();
    }
}
