package com.example.mall.entity;

/**
 * 管理员实体类
 */
public class Admin {
    /*管理员id*/
    private Integer admin_id;
    /*管理员登录名*/
    private String admin_name;
    /*管理员密码*/
    private String admin_password;
    /*管理员昵称*/
    private String admin_nickname;
    /*管理员图像*/
    private String admin_icon;
    /*管理员电话*/
    private String admin_phone;
    /*管理员邮箱*/
    private String admin_email;

    @Override
    public String toString() {
        return "Admin{" +
                "admin_id=" + admin_id +
                ", admin_name='" + admin_name + '\'' +
                ", admin_password='" + admin_password + '\'' +
                ", admin_icon='" + admin_icon + '\'' +
                ", admin_phone='" + admin_phone + '\'' +
                ", admin_email='" + admin_email + '\'' +
                '}';
    }

    public Admin() {

    }

    public Admin(Integer admin_id, String admin_name, String admin_password, String admin_nickname, String admin_phone, String admin_email) {
        this.admin_id = admin_id;
        this.admin_name = admin_name;
        this.admin_password = admin_password;
        this.admin_nickname = admin_nickname;
        this.admin_phone = admin_phone;
        this.admin_email = admin_email;
    }

    public Admin(Integer admin_id, String admin_name, String admin_password, String admin_nickname, String admin_icon, String admin_phone, String admin_email) {
        this.admin_id = admin_id;
        this.admin_name = admin_name;
        this.admin_password = admin_password;
        this.admin_nickname = admin_nickname;
        this.admin_icon = admin_icon;
        this.admin_phone = admin_phone;
        this.admin_email = admin_email;
    }

    public Integer getAdmin_id() {
        return admin_id;
    }

    public Admin setAdmin_id(Integer admin_id) {
        this.admin_id = admin_id;
        return this;
    }

    public String getAdmin_name() {
        return admin_name;
    }

    public Admin setAdmin_name(String admin_name) {
        this.admin_name = admin_name;
        return this;
    }

    public String getAdmin_password() {
        return admin_password;
    }

    public Admin setAdmin_password(String admin_password) {
        this.admin_password = admin_password;
        return this;
    }

    public String getAdmin_nickname() {
        return admin_nickname;
    }

    public Admin setAdmin_nickname(String admin_nickname) {
        this.admin_nickname = admin_nickname;
        return this;
    }

    public String getAdmin_icon() {
        return admin_icon;
    }

    public Admin setAdmin_icon(String admin_icon) {
        this.admin_icon = admin_icon;
        return this;
    }

    public String getAdmin_phone() {
        return admin_phone;
    }

    public Admin setAdmin_phone(String admin_phone) {
        this.admin_phone = admin_phone;
        return this;
    }

    public String getAdmin_email() {
        return admin_email;
    }

    public Admin setAdmin_email(String admin_email) {
        this.admin_email = admin_email;
        return this;
    }
}
