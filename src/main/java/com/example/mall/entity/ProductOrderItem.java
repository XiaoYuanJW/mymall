package com.example.mall.entity;

public class ProductOrderItem {
    /*订单项ID*/
    private Integer orderItem_id;
    /*订单项产品数量*/
    private Integer orderItem_number;
    /*订单项产品总价格*/
    private Double orderItem_price;
    /*订单项对应产品*/
    private Product orderItem_product;
    /*订单项对应订单*/
    private ProductOrder orderItem_order;
    /*订单项对应用户*/
    private User orderItem_user;
    /*订单产品是否已经评价*/
    private Boolean isReview;

    @Override
    public String toString() {
        return "ProductOrderItem{" +
                "orderItem_id=" + orderItem_id +
                ", orderItem_number=" + orderItem_number +
                ", orderItem_price=" + orderItem_price +
                ", orderItem_product=" + orderItem_product +
                ", orderItem_order=" + orderItem_order +
                ", orderItem_user=" + orderItem_user +
                '}';
    }

    public ProductOrderItem() {
    }

    public ProductOrderItem(Integer orderItem_id, Integer orderItem_number, Double orderItem_price, Product orderItem_product, ProductOrder orderItem_order, User orderItem_user) {
        this.orderItem_id = orderItem_id;
        this.orderItem_number = orderItem_number;
        this.orderItem_price = orderItem_price;
        this.orderItem_product = orderItem_product;
        this.orderItem_order = orderItem_order;
        this.orderItem_user = orderItem_user;
    }

    public Integer getOrderItem_id() {
        return orderItem_id;
    }

    public ProductOrderItem setOrderItem_id(Integer orderItem_id) {
        this.orderItem_id = orderItem_id;
        return this;
    }

    public Integer getOrderItem_number() {
        return orderItem_number;
    }

    public ProductOrderItem setOrderItem_number(Integer orderItem_number) {
        this.orderItem_number = orderItem_number;
        return this;
    }

    public Double getOrderItem_price() {
        return orderItem_price;
    }

    public ProductOrderItem setOrderItem_price(Double orderItem_price) {
        this.orderItem_price = orderItem_price;
        return this;
    }

    public Product getOrderItem_product() {
        return orderItem_product;
    }

    public ProductOrderItem setOrderItem_product(Product orderItem_product) {
        this.orderItem_product = orderItem_product;
        return this;
    }

    public ProductOrder getOrderItem_order() {
        return orderItem_order;
    }

    public ProductOrderItem setOrderItem_order(ProductOrder orderItem_order) {
        this.orderItem_order = orderItem_order;
        return this;
    }

    public User getOrderItem_user() {
        return orderItem_user;
    }

    public ProductOrderItem setOrderItem_user(User orderItem_user) {
        this.orderItem_user = orderItem_user;
        return this;
    }
}
