package com.example.mall.controller.fore;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.mall.controller.BaseController;
import com.example.mall.entity.OrderItem;
import com.example.mall.entity.Product;
import com.example.mall.entity.User;
import com.example.mall.service.*;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 购物车列表页
 */
@Controller
public class ForeCartController extends BaseController {
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
     * 转到前台-购物车订单建立页
     * @param map
     * @param session
     * @param request
     * @param order_item_list
     * @return
     * @throws UnsupportedEncodingException
     */
    @RequestMapping(value = "order/create/byCart", method = RequestMethod.GET)
    public String goToOrderConfirmPageByCart(Map<String, Object> map,
                                             HttpSession session, HttpServletRequest request,
                                             @RequestParam(required = false) Integer[] order_item_list) throws UnsupportedEncodingException {
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
        if (order_item_list == null || order_item_list.length == 0) {
            logger.warn("用户订单项数组不存在，回到购物车页");
            return "redirect:/cart";
        }
        logger.info("通过订单项ID数组获取订单信息");
        List<OrderItem> orderItemList = new ArrayList<>(order_item_list.length);
        for (Integer orderItem_id : order_item_list) {
            orderItemList.add(orderItemService.get(orderItem_id));
        }
        logger.info("------检查订单项合法性------");
        if (orderItemList.size() == 0) {
            logger.warn("用户订单项获取失败，回到购物车页");
            return "redirect:/cart";
        }
        for (OrderItem orderItem : orderItemList) {
            if (orderItem.getOrderItem_user().getUser_id() != userId) {
                logger.warn("用户订单项与用户不匹配，回到购物车页");
                return "redirect:/cart";
            }
            if (orderItem.getOrderItem_order() != null) {
                logger.warn("用户订单项不属于购物车，回到购物车页");
                return "redirect:/cart";
            }
        }
        logger.info("验证通过，获取订单项的产品信息");
        double orderTotalPrice = 0.0;
        for (OrderItem orderItem : orderItemList) {
            Product product = productService.get(orderItem.getOrderItem_product().getProduct_id());
            product.setProduct_category(categoryService.get(product.getProduct_category().getCategory_id()));
            product.setSingleProductImageList(productImageService.getList(product.getProduct_id(), (byte) 0, new PageUtil(0, 1)));
            orderItem.setOrderItem_product(product);
            orderTotalPrice += orderItem.getOrderItem_price();
        }
        String order_address = null;
        String detailsAddress = null;
        String order_receiver = null;
        String order_phone = null;
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                String cookieName = cookie.getName();
                String cookieValue = cookie.getValue();
                switch (cookieName) {
                    case "order_address":
                        order_address = cookieValue;
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
        map.put("orderItemList", orderItemList);
        map.put("orderTotalPrice", orderTotalPrice);
        map.put("order_address",order_address);
        map.put("order_receiver", order_receiver);
        map.put("order_phone", order_phone);
        map.put("detailsAddress", detailsAddress);
        logger.info("转到前台天猫-订单建立页");
        return "fore/productBuyPage";
    }

    /**
     * 转到前台-购物车页
     * @param map
     * @param session
     * @return
     */
    @RequestMapping(value = "cart", method = RequestMethod.GET)
    public String goToCartPage(Map<String, Object> map, HttpSession session) {
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
        logger.info("获取用户购物车信息");
        List<OrderItem> orderItemList = orderItemService.getListByUserId(Integer.valueOf(userId.toString()), null);
        Integer orderItemTotal = 0;
        if (orderItemList.size() > 0) {
            logger.info("获取用户购物车的商品总数");
            orderItemTotal = orderItemService.getTotalByUserId(Integer.valueOf(userId.toString()));
            logger.info("获取用户购物车内的商品信息");
            for (OrderItem orderItem : orderItemList) {
                Integer product_id = orderItem.getOrderItem_product().getProduct_id();
                Product product = productService.get(product_id);
                product.setSingleProductImageList(productImageService.getList(product_id, (byte) 0, null));
                product.setProduct_category(categoryService.get(product.getProduct_category().getCategory_id()));
                orderItem.setOrderItem_product(product);
            }
        }
        map.put("orderItemList", orderItemList);
        map.put("orderItemTotal", orderItemTotal);

        logger.info("转到前台天猫-购物车页");
        return "fore/productBuyCarPage";
    }

    /**
     * 创建订单项-购物车
     * @param product_id
     * @param product_number
     * @param session
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "orderItem/create/{product_id}", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    public String createOrderItem(@PathVariable("product_id") Integer product_id,
                                  @RequestParam(required = false, defaultValue = "1") Short product_number,
                                  HttpSession session,
                                  HttpServletRequest request) {
        JSONObject object = new JSONObject();
        logger.info("检查用户是否登录");
        Object userId = checkUser(session);
        if (userId == null) {
            object.put("url", "/login");
            object.put("success", false);
            return object.toJSONString();
        }

        logger.info("通过产品ID获取产品信息：{}", product_id);
        Product product = productService.get(product_id);
        if (product == null) {
            object.put("url", "/login");
            object.put("success", false);
            return object.toJSONString();
        }

        OrderItem productOrderItem = new OrderItem();
        logger.info("检查用户的购物车项");
        List<OrderItem> orderItemList = orderItemService.getListByUserId(Integer.valueOf(userId.toString()), null);
        for (OrderItem orderItem : orderItemList) {
            if (orderItem.getOrderItem_product().getProduct_id().equals(product_id)) {
                logger.info("找到已有的产品，进行数量追加");
                int number = orderItem.getOrderItem_number();
                number += 1;
                productOrderItem.setOrderItem_id(orderItem.getOrderItem_id());
                productOrderItem.setOrderItem_number((short) number);
                productOrderItem.setOrderItem_price(number * product.getProduct_sale_price());
                boolean yn = orderItemService.update(productOrderItem);
                if (yn) {
                    object.put("success", true);
                } else {
                    object.put("success", false);
                }
                return object.toJSONString();
            }
        }
        logger.info("封装订单项对象");
        productOrderItem.setOrderItem_product(product);
        productOrderItem.setOrderItem_number(product_number);
        productOrderItem.setOrderItem_price(product.getProduct_sale_price() * product_number);
        productOrderItem.setOrderItem_user(new User().setUser_id(Integer.valueOf(userId.toString())));
        boolean yn = orderItemService.add(productOrderItem);
        if (yn) {
            object.put("success", true);
        } else {
            object.put("success", false);
        }
        return object.toJSONString();
    }

    /**
     * 删除订单项-购物车
     * @param orderItem_id
     * @param session
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "orderItem/{orderItem_id}", method = RequestMethod.DELETE, produces = "application/json;charset=utf-8")
    public String deleteOrderItem(@PathVariable("orderItem_id") Integer orderItem_id,
                                  HttpSession session,
                                  HttpServletRequest request) {
        JSONObject object = new JSONObject();
        logger.info("检查用户是否登录");
        Object userId = checkUser(session);
        if (userId == null) {
            object.put("url", "/login");
            object.put("success", false);
            return object.toJSONString();
        }
        logger.info("检查用户的购物车项");
        List<OrderItem> orderItemList = orderItemService.getListByUserId(Integer.valueOf(userId.toString()), null);
        boolean isMine = false;
        for (OrderItem orderItem : orderItemList) {
            logger.info("找到匹配的购物车项");
            if (orderItem.getOrderItem_id().equals(orderItem_id)) {
                isMine = true;
                break;
            }
        }
        if (isMine) {
            logger.info("删除订单项信息");
            boolean yn = orderItemService.deleteList(new Integer[]{orderItem_id});
            if (yn) {
                object.put("success", true);
            } else {
                object.put("success", false);
            }
        } else {
            object.put("success", false);
        }
        return object.toJSONString();
    }

    /**
     * 更新购物车订单项数量
     * @param session
     * @param map
     * @param response
     * @param orderItemMap
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "orderItem", method = RequestMethod.PUT, produces = "application/json;charset=utf-8")
    public String updateOrderItem(HttpSession session, Map<String, Object> map, HttpServletResponse response,
                                  @RequestParam String orderItemMap) {
        JSONObject object = new JSONObject();
        logger.info("检查用户是否登录");
        Object userId = checkUser(session);
        if (userId == null) {
            object.put("success", false);
            return object.toJSONString();
        }

        JSONObject orderItemString = JSON.parseObject(orderItemMap);
        Set<String> orderItemIDSet = orderItemString.keySet();
        if (orderItemIDSet.size() > 0) {
            logger.info("更新产品订单项数量");
            for (String key : orderItemIDSet) {
                OrderItem orderItem = orderItemService.get(Integer.valueOf(key));
                if (orderItem == null || !orderItem.getOrderItem_user().getUser_id().equals(userId)) {
                    logger.warn("订单项为空或用户状态不一致！");
                    object.put("success", false);
                    return object.toJSONString();
                }
                if (orderItem.getOrderItem_order() != null) {
                    logger.warn("用户订单项不属于购物车，回到购物车页");
                    return "redirect:/cart";
                }
                Short number = Short.valueOf(orderItemString.getString(key));
                if (number <= 0 || number > 500) {
                    logger.warn("订单项产品数量不合法！");
                    object.put("success", false);
                    return object.toJSONString();
                }
                double price = orderItem.getOrderItem_price() / orderItem.getOrderItem_number();
                Boolean yn = orderItemService.update(new OrderItem().setOrderItem_id(Integer.valueOf(key)).setOrderItem_number(number).setOrderItem_price(number * price));
                if (!yn) {
                    throw new RuntimeException();
                }
            }
            Object[] orderItemIDArray = orderItemIDSet.toArray();
            object.put("success", true);
            object.put("orderItemIDArray", orderItemIDArray);
            return object.toJSONString();
        } else {
            logger.warn("无订单项可以处理");
            object.put("success", false);
            return object.toJSONString();
        }
    }
}
