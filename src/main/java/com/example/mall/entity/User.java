package com.example.mall.entity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * 用户实体类
 */
public class User {
    /*用户ID*/
    private Integer user_id;
    /*用户登录名*/
    private String user_name;
    /*用户密码*/
    private String user_password;
    /*用户昵称*/
    private String user_nickname;
    /*用户头像路径*/
    private String user_icon;
    /*用户电话*/
    private String user_phone;
    /*用户邮箱*/
    private String user_email;
    /*用户签名*/
    private String user_sign;
    /*用户性别*/
    private Byte user_gender;
    /*用户生日*/
    private Date user_birthday;
    /*用户地址*/
    private String user_address;
    /*用户账号创建时间*/
    private Date user_create_time;
    /*评论集合*/
    private List<Review> reviewList;
    /*订单项（购物车）集合*/
    private List<OrderItem> productOrderItemList;
    /*订单集合*/
    private List<Order> productOrderList;

    @Override
    public String toString() {
        return "User{" +
                "user_id=" + user_id +
                ", user_name='" + user_name + '\'' +
                ", user_password='" + user_password + '\'' +
                ", user_nickname='" + user_nickname + '\'' +
                ", user_icon='" + user_icon + '\'' +
                ", user_phone='" + user_phone + '\'' +
                ", user_email='" + user_email + '\'' +
                ", user_sign='" + user_sign + '\'' +
                ", user_gender=" + user_gender +
                ", user_birthday=" + user_birthday +
                ", user_address='" + user_address + '\'' +
                ", user_create_time=" + user_create_time +
                ", reviewList=" + reviewList +
                '}';
    }

    public User() {
    }

    public User(Integer user_id, String user_name, String user_password, String user_nickname, String user_icon, String user_phone, String user_email, String user_sign, Byte user_gender, Date user_birthday, String user_address, List<Review> reviewList) {
        this.user_id = user_id;
        this.user_name = user_name;
        this.user_password = user_password;
        this.user_nickname = user_nickname;
        this.user_icon = user_icon;
        this.user_phone = user_phone;
        this.user_email = user_email;
        this.user_sign = user_sign;
        this.user_gender = user_gender;
        this.user_birthday = user_birthday;
        this.user_address = user_address;
        this.reviewList = reviewList;
    }

    public User(Integer user_id, String user_name, String user_password, String user_nickname, String user_icon, String user_phone, String user_email, String user_sign, Byte user_gender, Date user_birthday, String user_address, Date user_create_time, List<Review> reviewList) {
        this.user_id = user_id;
        this.user_name = user_name;
        this.user_password = user_password;
        this.user_nickname = user_nickname;
        this.user_icon = user_icon;
        this.user_phone = user_phone;
        this.user_email = user_email;
        this.user_sign = user_sign;
        this.user_gender = user_gender;
        this.user_birthday = user_birthday;
        this.user_address = user_address;
        this.user_create_time = user_create_time;
        this.reviewList = reviewList;
    }

    public User(Integer user_id, String user_name, String user_password, String user_nickname, String user_phone, String user_email, String user_sign, Byte user_gender) {
        this.user_id = user_id;
        this.user_name = user_name;
        this.user_password = user_password;
        this.user_nickname = user_nickname;
        this.user_gender = user_gender;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public User setUser_id(Integer user_id) {
        this.user_id = user_id;
        return this;
    }

    public String getUser_name() {
        return user_name;
    }

    public User setUser_name(String user_name) {
        this.user_name = user_name;
        return this;
    }

    public String getUser_password() {
        return user_password;
    }

    public User setUser_password(String user_password) {
        this.user_password = user_password;
        return this;
    }

    public String getUser_nickname() {
        return user_nickname;
    }

    public User setUser_nickname(String user_nickname) {
        this.user_nickname = user_nickname;
        return this;
    }

    public String getUser_icon() {
        return user_icon;
    }

    public User setUser_icon(String user_icon) {
        this.user_icon = user_icon;
        return this;
    }

    public String getUser_phone() {
        return user_phone;
    }

    public User setUser_phone(String user_phone) {
        this.user_phone = user_phone;
        return this;
    }

    public String getUser_email() {
        return user_email;
    }

    public User setUser_email(String user_email) {
        this.user_email = user_email;
        return this;
    }

    public String getUser_sign() {
        return user_sign;
    }

    public User setUser_sign(String user_sign) {
        this.user_sign = user_sign;
        return this;
    }

    public Byte getUser_gender() {
        return user_gender;
    }

    public User setUser_gender(Byte user_gender) {
        this.user_gender = user_gender;
        return this;
    }

    public String getUser_birthday() {
        if(user_birthday != null){
            SimpleDateFormat time=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.UK);
            return time.format(user_birthday);
        }
        return null;
    }

    public User setUser_birthday(Date user_birthday) {
        this.user_birthday = user_birthday;
        return this;
    }

    public String getUser_address() {
        return user_address;
    }

    public User setUser_address(String user_address) {
        this.user_address = user_address;
        return this;
    }

    public String getUser_create_time() {
        if(user_birthday != null){
            SimpleDateFormat time=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.UK);
            return time.format(user_birthday);
        }
        return null;
    }

    public User setUser_create_time(Date user_create_time) {
        this.user_create_time = user_create_time;
        return this;
    }

    public List<Review> getReviewList() {
        return reviewList;
    }

    public User setReviewList(List<Review> reviewList) {
        this.reviewList = reviewList;
        return this;
    }

    public List<OrderItem> getProductOrderItemList() {
        return productOrderItemList;
    }

    public User setProductOrderItemList(List<OrderItem> productOrderItemList) {
        this.productOrderItemList = productOrderItemList;
        return this;
    }

    public List<Order> getProductOrderList() {
        return productOrderList;
    }

    public User setProductOrderList(List<Order> productOrderList) {
        this.productOrderList = productOrderList;
        return this;
    }
}
