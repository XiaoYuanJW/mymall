package com.example.mall.entity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * 评论实体类
 */
public class Review {
    /*评论ID*/
    private Integer review_id;
    /*评论内容*/
    private String review_content;
    /*评论星级*/
    private Integer review_stars;
    /*评论时间*/
    private Date review_createDate;
    /*评论对应用户*/
    private User review_user;
    /*评论对应产品*/
    private Product review_product;
    /*评论对应订单项*/
    private OrderItem review_orderItem;

    @Override
    public String toString() {
        return "Review{" +
                "review_id=" + review_id +
                ", review_content='" + review_content + '\'' +
                ", review_stars=" + review_stars +
                ", review_createDate=" + review_createDate +
                ", review_user=" + review_user +
                ", review_product=" + review_product +
                '}';
    }

    public Review() {
    }

    public Review(Integer review_id, String review_content, Integer review_stars, User review_user, Product review_product) {
        this.review_id = review_id;
        this.review_content = review_content;
        this.review_stars = review_stars;
        this.review_user = review_user;
        this.review_product = review_product;
    }

    public Review(Integer review_id, String review_content, Integer review_stars, Date review_createDate, User review_user, Product review_product) {
        this.review_id = review_id;
        this.review_content = review_content;
        this.review_stars = review_stars;
        this.review_createDate = review_createDate;
        this.review_user = review_user;
        this.review_product = review_product;
    }

    public Integer getReview_id() {
        return review_id;
    }

    public Review setReview_id(Integer review_id) {
        this.review_id = review_id;
        return this;
    }

    public String getReview_content() {
        return review_content;
    }

    public Review setReview_content(String review_content) {
        this.review_content = review_content;
        return this;
    }

    public Integer getReview_stars() {
        return review_stars;
    }

    public Review setReview_stars(Integer review_stars) {
        this.review_stars = review_stars;
        return this;
    }

    public String getReview_createDate() {
        if(review_createDate != null){
            SimpleDateFormat time=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.UK);
            return time.format(review_createDate);
        }
        return null;
    }

    public Review setReview_createDate(Date review_createDate) {
        this.review_createDate = review_createDate;
        return this;
    }

    public User getReview_user() {
        return review_user;
    }

    public Review setReview_user(User review_user) {
        this.review_user = review_user;
        return this;
    }

    public Product getReview_product() {
        return review_product;
    }

    public Review setReview_product(Product review_product) {
        this.review_product = review_product;
        return this;
    }

    public OrderItem getReview_orderItem() {
        return review_orderItem;
    }

    public Review setReview_orderItem(OrderItem review_orderItem) {
        this.review_orderItem = review_orderItem;
        return this;
    }
}
