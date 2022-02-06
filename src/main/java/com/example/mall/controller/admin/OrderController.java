package com.example.mall.controller.admin;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.mall.controller.BaseController;
import com.example.mall.entity.Category;
import com.example.mall.entity.Order;
import com.example.mall.entity.OrderItem;
import com.example.mall.entity.Product;
import com.example.mall.service.*;
import com.example.mall.util.OrderUtil;
import com.example.mall.util.PageUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Stack;

/**
 * 后台管理-订单页
 */
@Controller
public class OrderController extends BaseController {
    @Resource(name="orderService")
    private OrderService orderService;
    @Resource(name="userService")
    private UserService userService;
    @Resource(name = "orderItemService")
    private OrderItemService orderItemService;
    @Resource(name = "productService")
    private ProductService productService;
    @Resource(name = "productImageService")
    private ProductImageService productImageService;
    @Resource(name = "lastIDService")
    private LastIDService lastIDService;

    /*
    转到后台管理-订单页
     */
    @RequestMapping(value = "admin/order", method = RequestMethod.GET)
    public String goToPage(HttpSession session, Map<String, Object> map){
        logger.info("获取前10条订单列表");
        PageUtil pageUtil = new PageUtil(0, 10);
        List<Order> orderList =
                orderService.getList(
                        null,
                        null,
                        new OrderUtil("order_id",
                                true), pageUtil);
        map.put("orderList",orderList);
        logger.info("获取订单总数量");
        Integer orderCount = orderService.getTotal(null, null);
        map.put("orderCount", orderCount);
        logger.info("获取分页信息");
        pageUtil.setTotal(orderCount);
        map.put("pageUtil", pageUtil);
        logger.info("转到后台管理-订单页-ajax方式");
        return "admin/orderManagePage";
    }

    /*
    按条件查询订单
     */
    @ResponseBody
    @RequestMapping(value = "admin/order/{index}/{count}", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    public String getOrderBySearch(@RequestParam(required = false) String order_code/* 订单号 */,
                                   @RequestParam(required = false) Byte[] order_status_array/* 订单状态数组 */,
                                   @RequestParam(required = false) String orderBy/* 排序字段 */,
                                   @RequestParam(required = false,defaultValue = "true") Boolean isDesc/* 是否倒序 */,
                                   @PathVariable Integer index/* 页数 */,
                                   @PathVariable Integer count/* 行数 */){
        if (order_status_array != null && (order_status_array.length <= 0 || order_status_array.length >=5)) {
            order_status_array = null;
        }
        if (order_code != null){
            order_code = "".equals(order_code) ? null : order_code;
        }
        if (orderBy != null && "".equals(orderBy)) {
            orderBy = null;
        }
        Order order = new Order()
                .setOrder_code(order_code);
        OrderUtil orderUtil = null;
        if (orderBy != null) {
            logger.info("根据{}排序，是否倒序:{}",orderBy,isDesc);
            orderUtil = new OrderUtil(orderBy, isDesc);
        } else {
            orderUtil = new OrderUtil("order_id",
                    true);
        }
        JSONObject object = new JSONObject();
        logger.info("按条件获取第{}页的{}条订单", index + 1, count);
        PageUtil pageUtil = new PageUtil(index, count);
        List<Order> orderList = orderService.getList(order, order_status_array, orderUtil, pageUtil);
        object.put("orderList", JSONArray.parseArray(JSON.toJSONString(orderList)));
        logger.info("按条件获取订单总数量");
        Integer orderCount = orderService.getTotal(order, order_status_array);
        object.put("orderCount", orderCount);
        logger.info("获取分页信息");
        pageUtil.setTotal(orderCount);
        object.put("totalPage", pageUtil.getTotalPage());
        object.put("pageUtil", pageUtil);
        return object.toJSONString();
    }

    /*
    转到后台管理-订单详情页
     */
    @RequestMapping(value = "admin/order/{oid}", method = RequestMethod.GET)
    public String goToDetailsPage(HttpSession session, Map<String, Object> map, @PathVariable Integer oid/* 订单ID */) {
        logger.info("获取order_id为{}的订单信息",oid);
        Order order = orderService.get(oid);
        logger.info("获取订单详情-用户信息");
        order.setOrder_user(userService.get(order.getOrder_user().getUser_id()));
        logger.info("获取订单详情-订单项信息");
        List<OrderItem> orderItemList = orderItemService.getListByOrderId(oid, null);
        if (orderItemList != null) {
            logger.info("获取订单详情-订单项对应的产品信息");
            for (OrderItem orderItem : orderItemList) {
                Integer productId = orderItem.getOrderItem_product().getProduct_id();
                logger.info("获取产品ID为{}的产品信息", productId);
                Product product = productService.get(productId);
                if (product != null) {
                    logger.info("获取产品ID为{}的第一张预览图片信息", productId);
                    product.setSingleProductImageList(productImageService.getList(productId, (byte) 0, new PageUtil(0, 1)));
                }
                orderItem.setOrderItem_product(product);
            }
        }
        order.setOrderItemList(orderItemList);
        map.put("order", order);
        logger.info("转到后台管理-订单详情页-ajax方式");
        return "admin/include/orderDetails";
    }

    /*
    更新订单信息
     */
    @ResponseBody
    @RequestMapping(value = "admin/order/{order_id}", method = RequestMethod.PUT, produces = "application/json;charset=UTF-8")
    public String updateOrder(@PathVariable("order_id") String order_id) {
        JSONObject jsonObject = new JSONObject();
        logger.info("整合订单信息");
        Order order = new Order()
                .setOrder_id(Integer.valueOf(order_id))
                .setOrder_status((byte) 2)
                .setOrder_delivery_date(new Date());
        logger.info("更新订单信息，订单ID值为：{}", order_id);
        boolean yn = orderService.update(order);
        if (yn) {
            logger.info("更新成功！");
            jsonObject.put("success", true);
        } else {
            logger.info("更新失败！事务回滚");
            jsonObject.put("success", false);
            throw new RuntimeException();
        }
        jsonObject.put("order_id", order_id);
        return jsonObject.toJSONString();
    }

}
