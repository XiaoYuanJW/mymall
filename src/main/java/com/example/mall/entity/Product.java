package com.example.mall.entity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * 产品实体类
 */
public class Product {
    /*产品ID*/
    private Integer product_id;
    /*产品名称*/
    private String product_name;
    /*产品品牌*/
    private String product_brand;
    /*产品描述*/
    private String product_description;
    /*产品原价格*/
    private Double product_price;
    /*产品促销价格*/
    private Double product_sale_price;
    /*产品库存*/
    private Integer product_inventory;
    /*产品创建日期*/
    private Date product_create_date;
    /*产品对应类型*/
    private Category product_category;
    /*产品状态*/
    private Byte product_isEnabled;
    /*销量数*/
    private Integer product_sale_count;
    /*评价数*/
    private Integer product_review_count;
    /*产品预览图片集合*/
    private List<ProductImage> singleProductImageList;
    /*产品详细图片集合*/
    private List<ProductImage> detailProductImageList;
    /*产品评论集合*/
    private List<Review> reviewList;
    /*产品订单项集合*/
    private List<OrderItem> orderItemList;

    @Override
    public String toString() {
        return "Product{" +
                "product_id=" + product_id +
                ", product_name='" + product_name + '\'' +
                ", product_brand='" + product_brand + '\'' +
                ", product_description='" + product_description + '\'' +
                ", product_price=" + product_price +
                ", product_sale_price=" + product_sale_price +
                ", product_inventory=" + product_inventory +
                ", product_create_date=" + product_create_date +
                ", product_category=" + product_category +
                ", product_isEnabled=" + product_isEnabled +
                ", product_sale_count=" + product_sale_count +
                ", product_review_count=" + product_review_count +
                ", singleProductImageList=" + singleProductImageList +
                ", detailProductImageList=" + detailProductImageList +
                ", reviewList=" + reviewList +
                ", orderItemList=" + orderItemList +
                '}';
    }

    public Product(Integer product_id, String product_name, String product_brand, String product_description, Double product_price, Double product_sale_price, Integer product_inventory, Date product_create_date, Category product_category, Byte product_isEnabled, Integer product_sale_count, Integer product_review_count, List<ProductImage> singleProductImageList, List<ProductImage> detailProductImageList) {
        this.product_id = product_id;
        this.product_name = product_name;
        this.product_brand = product_brand;
        this.product_description = product_description;
        this.product_price = product_price;
        this.product_sale_price = product_sale_price;
        this.product_inventory = product_inventory;
        this.product_create_date = product_create_date;
        this.product_category = product_category;
        this.product_isEnabled = product_isEnabled;
        this.product_sale_count = product_sale_count;
        this.product_review_count = product_review_count;
        this.singleProductImageList = singleProductImageList;
        this.detailProductImageList = detailProductImageList;
    }

    public Product() {

    }

    public Integer getProduct_id() {
        return product_id;
    }

    public Product setProduct_id(Integer product_id) {
        this.product_id = product_id;
        return this;
    }

    public String getProduct_name() {
        return product_name;
    }

    public Product setProduct_name(String product_name) {
        this.product_name = product_name;
        return this;
    }

    public String getProduct_brand() {
        return product_brand;
    }

    public Product setProduct_brand(String product_brand) {
        this.product_brand = product_brand;
        return this;
    }

    public String getProduct_description() {
        return product_description;
    }

    public Product setProduct_description(String product_description) {
        this.product_description = product_description;
        return this;
    }

    public Double getProduct_price() {
        return product_price;
    }

    public Product setProduct_price(Double product_price) {
        this.product_price = product_price;
        return this;
    }

    public Double getProduct_sale_price() {
        return product_sale_price;
    }

    public Product setProduct_sale_price(Double product_sale_price) {
        this.product_sale_price = product_sale_price;
        return this;
    }

    public Integer getProduct_inventory() {
        return product_inventory;
    }

    public Product setProduct_inventory(Integer product_inventory) {
        this.product_inventory = product_inventory;
        return this;
    }

    public String getProduct_create_date() {
        if(product_create_date != null){
            SimpleDateFormat time=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.UK);
            return time.format(product_create_date);
        }
        return null;
    }

    public Product setProduct_create_date(Date product_create_date) {
        this.product_create_date = product_create_date;
        return this;
    }

    public Category getProduct_category() {
        return product_category;
    }

    public Product setProduct_category(Category product_category) {
        this.product_category = product_category;
        return this;
    }

    public Byte getProduct_isEnabled() {
        return product_isEnabled;
    }

    public Product setProduct_isEnabled(Byte product_isEnabled) {
        this.product_isEnabled = product_isEnabled;
        return this;
    }

    public Integer getProduct_sale_count() {
        return product_sale_count;
    }

    public Product setProduct_sale_count(Integer product_sale_count) {
        this.product_sale_count = product_sale_count;
        return this;
    }

    public Integer getProduct_review_count() {
        return product_review_count;
    }

    public Product setProduct_review_count(Integer product_review_count) {
        this.product_review_count = product_review_count;
        return this;
    }

    public List<ProductImage> getSingleProductImageList() {
        return singleProductImageList;
    }

    public Product setSingleProductImageList(List<ProductImage> singleProductImageList) {
        this.singleProductImageList = singleProductImageList;
        return this;
    }

    public List<ProductImage> getDetailProductImageList() {
        return detailProductImageList;
    }

    public Product setDetailProductImageList(List<ProductImage> detailProductImageList) {
        this.detailProductImageList = detailProductImageList;
        return this;
    }

    public List<Review> getReviewList() {
        return reviewList;
    }

    public Product setReviewList(List<Review> reviewList) {
        this.reviewList = reviewList;
        return this;
    }

    public List<OrderItem> getOrderItemList() {
        return orderItemList;
    }

    public Product setOrderItemList(List<OrderItem> orderItemList) {
        this.orderItemList = orderItemList;
        return this;
    }
}
