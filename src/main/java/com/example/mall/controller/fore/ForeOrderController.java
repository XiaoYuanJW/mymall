package com.example.mall.controller.fore;

import com.alibaba.fastjson.JSONObject;
import com.example.mall.controller.BaseController;
import com.example.mall.entity.*;
import com.example.mall.service.*;
import com.example.mall.util.OrderUtil;
import com.example.mall.util.PageUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 订单列表页
 */
@Controller
public class ForeOrderController extends BaseController {
    @Resource(name = "productService")
    private ProductService productService;
    @Resource(name = "userService")
    private UserService userService;
    @Resource(name = "orderItemService")
    private OrderItemService orderItemService;
    @Resource(name = "categoryService")
    private CategoryService categoryService;
    @Resource(name = "productImageService")
    private ProductImageService productImageService;
    @Resource(name = "orderService")
    private OrderService orderService;
    @Resource(name = "reviewService")
    private ReviewService reviewService;
    @Resource(name = "lastIDService")
    private LastIDService lastIDService;

    /**
     * 转到前台-订单列表页
     * @return
     */
    @RequestMapping(value = "order", method = RequestMethod.GET)
    public String goToPageSimple() {
        return "redirect:/order/0/10";
    }
    @RequestMapping(value = "order/{index}/{count}", method = RequestMethod.GET)
    public String goToPage(HttpSession session, Map<String, Object> map,
                           @RequestParam(required = false) Byte status,
                           @PathVariable("index") Integer index/* 页数 */,
                           @PathVariable("count") Integer count/* 行数*/) {
        logger.info("检查用户是否登录");
        Object userId = checkUser(session);
        User user;
        if (userId != null) {
            logger.info("获取用户信息");
            user = userService.get(Integer.parseInt(userId.toString()));
            map.put("user", user);
        } else {
            return "redirect:/login";
        }
        Byte[] status_array = null;
        if (status != null) {
            status_array = new Byte[]{status};
        }
        PageUtil pageUtil = new PageUtil(index, count);
        logger.info("根据用户ID:{}获取订单列表", userId);
        List<Order> orderList = orderService.getList(
                new Order().setOrder_user(new User().setUser_id(Integer.valueOf(userId.toString()))), status_array, new OrderUtil("order_id", true), pageUtil);
        //订单总数量
        Integer orderCount = 0;
        if (orderList.size() > 0) {
            orderCount = orderService.getTotal(new Order().setOrder_user(new User().setUser_id(Integer.valueOf(userId.toString()))), status_array);
            logger.info("获取订单项信息及对应的产品信息");
            for (Order order : orderList) {
                List<OrderItem> orderItemList = orderItemService.getListByOrderId(
                        order.getOrder_id(), null);
                if (orderItemList != null) {
                    for (OrderItem orderItem : orderItemList) {
                        Integer product_id = orderItem.getOrderItem_product().getProduct_id();
                        Product product = productService.get(product_id);
                        product.setSingleProductImageList(productImageService.getList(
                                product_id, (byte) 0, new PageUtil(0, 1)
                        ));
                        orderItem.setOrderItem_product(product);
                        if (order.getOrder_status() == 3) {
                            orderItem.setIsReview(reviewService.getTotalByOrderItemId(orderItem.getOrderItem_id()) > 0);
                        }
                    }
                }
                order.setOrderItemList(orderItemList);
            }
        }
        pageUtil.setTotal(orderCount);
        logger.info("获取产品分类列表信息");
        List<Category> categoryList = categoryService.getList(null, new PageUtil(0, 5));
        map.put("pageUtil", pageUtil);
        map.put("orderList", orderList);
        map.put("categoryList", categoryList);
        map.put("status", status);
        logger.info("转到前台天猫-订单列表页");
        return "fore/orderListPage";
    }

    /**
     * 转到前台天猫-订单建立页
     * @param product_id
     * @param product_number
     * @param map
     * @param session
     * @param request
     * @return
     * @throws UnsupportedEncodingException
     */
    @RequestMapping(value = "order/create/{product_id}", method = RequestMethod.GET)
    public String goToOrderConfirmPage(@PathVariable("product_id") Integer product_id,
                                       @RequestParam(required = false, defaultValue = "1") Short product_number,
                                       Map<String, Object> map,
                                       HttpSession session,
                                       HttpServletRequest request) throws UnsupportedEncodingException {
        logger.info("检查用户是否登录");
        Object userId = checkUser(session);
        User user;
        if (userId != null) {
            logger.info("获取用户信息");
            user = userService.get(Integer.parseInt(userId.toString()));
            map.put("user", user);
        } else {
            return "redirect:/login";
        }
        logger.info("通过产品ID获取产品信息：{}", product_id);
        Product product = productService.get(product_id);
        if (product == null) {
            return "redirect:/";
        }
        logger.info("获取产品的详细信息");
        product.setProduct_category(categoryService.get(product.getProduct_category().getCategory_id()));
        product.setSingleProductImageList(productImageService.getList(product_id, (byte) 0, new PageUtil(0, 1)));

        logger.info("封装订单项对象");
        OrderItem orderItem = new OrderItem();
        orderItem.setOrderItem_product(product);
        orderItem.setOrderItem_number(product_number);
        orderItem.setOrderItem_price(product.getProduct_sale_price() * product_number);
        orderItem.setOrderItem_user(new User().setUser_id(Integer.valueOf(userId.toString())));

        //初始化订单
        String order_address = null;
        String detailsAddress = null;
        String order_receiver = null;
        String order_phone = null;
        Cookie[] cookies = request.getCookies();
        //Cookie
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                String cookieName = cookie.getName();
                String cookieValue = cookie.getValue();
                switch (cookieName) {
                    case "order_address":
                        order_address = URLDecoder.decode(cookieValue, "UTF-8");
                        break;
                    case "order_receiver":
                        order_receiver = URLDecoder.decode(cookieValue, "UTF-8");
                        break;
                    case "order_phone":
                        order_phone = URLDecoder.decode(cookieValue, "UTF-8");
                        break;
                    case "detailsAddress":
                        detailsAddress = URLDecoder.decode(cookieValue, "UTF-8");
                        break;
                }
            }
        }
        List<OrderItem> orderItemList = new ArrayList<>();
        orderItemList.add(orderItem);
        map.put("orderItemList", orderItemList);
        map.put("order_address", order_address);
        map.put("orderTotalPrice", orderItem.getOrderItem_price());
        map.put("order_receiver", order_receiver);
        map.put("order_phone", order_phone);
        map.put("detailsAddress", detailsAddress);
        logger.info("转到前台天猫-订单建立页");
        return "fore/productBuyPage";
    }

    /**
     * 创建新订单-单订单项
     * @param session
     * @param map
     * @param response
     * @param order_address
     * @param order_detail_address
     * @param order_receiver
     * @param order_phone
     * @param orderItem_product_id
     * @param orderItem_number
     * @return
     * @throws UnsupportedEncodingException
     */
    @ResponseBody
    @RequestMapping(value = "order", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    public String createOrderByOne(HttpSession session, Map<String, Object> map, HttpServletResponse response,
                                   @RequestParam String order_address,
                                   @RequestParam String order_detail_address,
                                   @RequestParam String order_receiver,
                                   @RequestParam String order_phone,
                                   @RequestParam Integer orderItem_product_id,
                                   @RequestParam Short orderItem_number) throws UnsupportedEncodingException {
        JSONObject object = new JSONObject();
        logger.info("检查用户是否登录");
        Object userId = checkUser(session);
        if (userId == null) {
            object.put("success", false);
            object.put("url", "/login");
            return object.toJSONString();
        }
        Product product = productService.get(orderItem_product_id);
        System.out.println(product);
        if (product == null) {
            object.put("success", false);
            object.put("url", "/");
            return object.toJSONString();
        }
        logger.info("将收货地址等相关信息存入Cookie中,便于下次使用");
        Cookie[] cookies = new Cookie[]{
                new Cookie("order_address", order_address),
                new Cookie("order_receiver", URLEncoder.encode(order_receiver, "UTF-8")),
                new Cookie("detailsAddress", URLEncoder.encode(order_detail_address, "UTF-8")),
                new Cookie("order_phone", URLEncoder.encode(order_phone, "UTF-8"))
        };
        int maxAge = 60 * 60 * 24 * 365;
        for(Cookie cookie : cookies){
            //设置过期时间为一年
            cookie.setMaxAge(maxAge);
            //存储Cookie
            response.addCookie(cookie);
        }
        StringBuffer order_code = new StringBuffer()
                .append(new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()))
                .append(0)
                .append(userId);
        logger.info("生成的订单号为：{}", order_code);
        logger.info("整合订单对象");
        Order order = new Order()
                .setOrder_status((byte) 0)
                .setOrder_address(order_address)
                .setOrder_user(new User().setUser_id(Integer.valueOf(userId.toString())))
                .setOrder_phone(order_phone)
                .setOrder_receiver(order_receiver)
                .setOrder_detail_address(order_detail_address)
                .setOrder_pay_date(new Date())
                .setOrder_code(order_code.toString());
        Boolean yn = orderService.add(order);
        if (!yn) {
            throw new RuntimeException();
        }
        Integer order_id = lastIDService.selectLastID();
        logger.info("整合订单项对象");
        OrderItem orderItem = new OrderItem()
                .setOrderItem_user(new User()
                .setUser_id(Integer.valueOf(userId.toString())))
                .setOrderItem_product(product)
                .setOrderItem_number(orderItem_number)
                .setOrderItem_price(product.getProduct_sale_price() * orderItem_number)
                .setOrderItem_order(new Order().setOrder_id(order_id));
        yn = orderItemService.add(orderItem);
        if (!yn) {
            throw new RuntimeException();
        }
        object.put("success", true);
        object.put("url", "/order/pay/" + order.getOrder_code());
        return object.toJSONString();
    }

//    /**
//     * 创建新订单-多订单项
//     * @param session
//     * @param map
//     * @param response
//     * @param order_address
//     * @param order_detail_address
//     * @param order_receiver
//     * @param order_phone
//     * @param orderItemJSON
//     * @return
//     * @throws UnsupportedEncodingException
//     */
//    @ResponseBody
//    @RequestMapping(value = "order/list", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
//    public String createOrderByList(HttpSession session, Map<String, Object> map, HttpServletResponse response,
//                                    @RequestParam String order_address,
//                                    @RequestParam String order_detail_address,
//                                    @RequestParam String order_receiver,
//                                    @RequestParam String order_phone,
//                                    @RequestParam String orderItemJSON) throws UnsupportedEncodingException {
//        JSONObject object = new JSONObject();
//        logger.info("检查用户是否登录");
//        Object userId = checkUser(session);
//        if (userId == null) {
//            object.put("success", false);
//            object.put("url", "/login");
//            return object.toJSONString();
//        }
//        JSONObject orderItemMap = JSONObject.parseObject(orderItemJSON);
//        Set<String> orderItem_id = orderItemMap.keySet();
//        List<OrderItem> orderItemList = new ArrayList<>(3);
//        if (orderItem_id.size() > 0) {
//            for (String id : orderItem_id) {
//                OrderItem orderItem = orderItemService.get(Integer.valueOf(id));
//                if (orderItem == null || !orderItem.getOrderItem_user().getUser_id().equals(userId)) {
//                    logger.warn("订单项为空或用户状态不一致！");
//                    object.put("success", false);
//                    object.put("url", "/cart");
//                    return object.toJSONString();
//                }
//                if (orderItem.getOrderItem_order() != null) {
//                    logger.warn("用户订单项不属于购物车，回到购物车页");
//                    object.put("success", false);
//                    object.put("url", "/cart");
//                    return object.toJSONString();
//                }
//                boolean yn = orderItemService.update(new OrderItem().setOrderItem_id(Integer.valueOf(id)));
//                if (!yn) {
//                    throw new RuntimeException();
//                }
//                orderItem.setOrderItem_product(productService.get(orderItem.getOrderItem_product().getProduct_id()));
//                orderItemList.add(orderItem);
//            }
//        } else {
//            object.put("success", false);
//            object.put("url", "/cart");
//            return object.toJSONString();
//        }
//        logger.info("将收货地址等相关信息存入Cookie中,便于下次使用");
//        Cookie[] cookies = new Cookie[]{
//                new Cookie("order_address", URLEncoder.encode(order_address, "UTF-8")),
//                new Cookie("order_receiver", URLEncoder.encode(order_receiver, "UTF-8")),
//                new Cookie("order_phone", URLEncoder.encode(order_phone, "UTF-8")),
//                new Cookie("detailsAddress", URLEncoder.encode(order_detail_address, "UTF-8"))
//        };
//        int maxAge = 60 * 60 * 24 * 365;
//        for(Cookie cookie : cookies){
//            //设置过期时间为一年
//            cookie.setMaxAge(maxAge);
//            //存储Cookie
//            response.addCookie(cookie);
//        }
//        StringBuffer order_code = new StringBuffer()
//                .append(new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()))
//                .append(0)
//                .append(userId);
//        logger.info("生成的订单号为：{}", order_code);
//        logger.info("整合订单对象");
//        Order order = new Order()
//                .setOrder_status((byte) 0)
//                .setOrder_address(order_address)
//                .setOrder_user(new User().setUser_id(Integer.valueOf(userId.toString())))
//                .setOrder_phone(order_phone)
//                .setOrder_receiver(order_receiver)
//                .setOrder_detail_address(order_detail_address)
//                .setOrder_pay_date(new Date())
//                .setOrder_code(order_code.toString());
//        Boolean yn = orderService.add(order);
//        if (!yn) {
//            throw new RuntimeException();
//        }
//        Integer order_id = lastIDService.selectLastID();
//        logger.info("整合订单项对象");
//        for (OrderItem orderItem : orderItemList) {
//            orderItem.setOrderItem_order(new Order().setOrder_id(order_id));
//            yn = orderItemService.update(orderItem);
//        }
//        if (!yn) {
//            throw new RuntimeException();
//        }
//
//        object.put("success", true);
//        object.put("url", "/order/pay/" + order.getOrder_code());
//        return object.toJSONString();
//    }

}
