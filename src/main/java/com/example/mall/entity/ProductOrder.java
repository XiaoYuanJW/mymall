package com.example.mall.entity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * 订单实体类
 */
public class ProductOrder {
    /*订单ID*/
    private Integer order_id;
    /*订单流水号*/
    private String order_code;
    /*订单地址*/
    private String order_address;
    /*订单详细地址*/
    private String order_detail_address;
    /*订单邮政编码*/
    private String order_post;
    /*订单收货人名称*/
    private String order_receiver;
    /*订单支付日期*/
    private Date order_pay_date;
    /*订单发货日期*/
    private Date order_delivery_date;
    /*订单确认日期*/
    private Date order_confirm_date;
    /*订单状态*/
    private Byte order_status;
    /*订单对应用户*/
    private User order_user;
    /*订单收货人号码*/
    private String order_phone;
    /*订单项集合*/
    private List<ProductOrderItem> productOrderItemList;

    @Override
    public String toString() {
        return "ProductOrder{" +
                "order_id=" + order_id +
                ", order_code='" + order_code + '\'' +
                ", order_address='" + order_address + '\'' +
                ", order_detail_address='" + order_detail_address + '\'' +
                ", order_post='" + order_post + '\'' +
                ", order_receiver='" + order_receiver + '\'' +
                ", order_pay_date=" + order_pay_date +
                ", order_delivery_date=" + order_delivery_date +
                ", order_confirm_date=" + order_confirm_date +
                ", order_status=" + order_status +
                ", order_user=" + order_user +
                ", order_phone='" + order_phone + '\'' +
                ", productOrderItemList=" + productOrderItemList +
                '}';
    }

    public ProductOrder() {
    }

    public ProductOrder(Integer order_id, String order_code, String order_address, String order_detail_address, String order_post, String order_receiver, Date order_pay_date, Byte order_status, User order_user, String order_phone) {
        this.order_id = order_id;
        this.order_code = order_code;
        this.order_address = order_address;
        this.order_detail_address = order_detail_address;
        this.order_post = order_post;
        this.order_receiver = order_receiver;
        this.order_pay_date = order_pay_date;
        this.order_status = order_status;
        this.order_user = order_user;
        this.order_phone = order_phone;
    }

    public ProductOrder(Integer order_id, String order_code, String order_address, String order_detail_address, String order_post, String order_receiver, Date order_pay_date, Date order_delivery_date, Date order_confirm_date, Byte order_status, User order_user, String order_phone, List<ProductOrderItem> productOrderItemList) {
        this.order_id = order_id;
        this.order_code = order_code;
        this.order_address = order_address;
        this.order_detail_address = order_detail_address;
        this.order_post = order_post;
        this.order_receiver = order_receiver;
        this.order_pay_date = order_pay_date;
        this.order_delivery_date = order_delivery_date;
        this.order_confirm_date = order_confirm_date;
        this.order_status = order_status;
        this.order_user = order_user;
        this.order_phone = order_phone;
        this.productOrderItemList = productOrderItemList;
    }

    public Integer getOrder_id() {
        return order_id;
    }

    public ProductOrder setOrder_id(Integer order_id) {
        this.order_id = order_id;
        return this;
    }

    public String getOrder_code() {
        return order_code;
    }

    public ProductOrder setOrder_code(String order_code) {
        this.order_code = order_code;
        return this;
    }

    public String getOrder_address() {
        return order_address;
    }

    public ProductOrder setOrder_address(String order_address) {
        this.order_address = order_address;
        return this;
    }

    public String getOrder_detail_address() {
        return order_detail_address;
    }

    public ProductOrder setOrder_detail_address(String order_detail_address) {
        this.order_detail_address = order_detail_address;
        return this;
    }

    public String getOrder_post() {
        return order_post;
    }

    public ProductOrder setOrder_post(String order_post) {
        this.order_post = order_post;
        return this;
    }

    public String getOrder_receiver() {
        return order_receiver;
    }

    public ProductOrder setOrder_receiver(String order_receiver) {
        this.order_receiver = order_receiver;
        return this;
    }

    public String getOrder_pay_date() {
        if(order_pay_date != null){
            SimpleDateFormat time=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.UK);
            return time.format(order_pay_date);
        }
        return null;
    }

    public ProductOrder setOrder_pay_date(Date order_pay_date) {
        this.order_pay_date = order_pay_date;
        return this;
    }

    public String getOrder_delivery_date() {
        if(order_delivery_date != null){
            SimpleDateFormat time=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.UK);
            return time.format(order_delivery_date);
        }
        return null;
    }

    public ProductOrder setOrder_delivery_date(Date order_delivery_date) {
        this.order_delivery_date = order_delivery_date;
        return this;
    }

    public String getOrder_confirm_date() {
        if(order_confirm_date != null){
            SimpleDateFormat time=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.UK);
            return time.format(order_confirm_date);
        }
        return null;
    }

    public ProductOrder setOrder_confirm_date(Date order_confirm_date) {
        this.order_confirm_date = order_confirm_date;
        return this;
    }

    public Byte getOrder_status() {
        return order_status;
    }

    public ProductOrder setOrder_status(Byte order_status) {
        this.order_status = order_status;
        return this;
    }

    public User getOrder_user() {
        return order_user;
    }

    public ProductOrder setOrder_user(User order_user) {
        this.order_user = order_user;
        return this;
    }

    public String getOrder_phone() {
        return order_phone;
    }

    public ProductOrder setOrder_phone(String order_phone) {
        this.order_phone = order_phone;
        return this;
    }

    public List<ProductOrderItem> getProductOrderItemList() {
        return productOrderItemList;
    }

    public ProductOrder setProductOrderItemList(List<ProductOrderItem> productOrderItemList) {
        this.productOrderItemList = productOrderItemList;
        return this;
    }
}
