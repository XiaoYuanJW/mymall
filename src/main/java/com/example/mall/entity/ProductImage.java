package com.example.mall.entity;

/**
 * 产品图片实体类
 */
public class ProductImage {
    /*产品图片ID*/
    private Integer productImage_id;
    /*产品图片类型*/
    private Byte productImage_type;
    /*产品图片路径*/
    private String productImage_src;
    /*产品图片对应产品*/
    private Product productImage_product_id;

    @Override
    public String toString() {
        return "ProductImage{" +
                "productImage_id=" + productImage_id +
                ", productImage_type=" + productImage_type +
                ", productImage_src='" + productImage_src + '\'' +
                ", productImage_product_id=" + productImage_product_id +
                '}';
    }

    public ProductImage() {

    }

    public ProductImage(Integer productImage_id, Byte productImage_type, String productImage_src, Product productImage_product_id) {
        this.productImage_id = productImage_id;
        this.productImage_type = productImage_type;
        this.productImage_src = productImage_src;
        this.productImage_product_id = productImage_product_id;
    }

    public Integer getProductImage_id() {
        return productImage_id;
    }

    public ProductImage setProductImage_id(Integer productImage_id) {
        this.productImage_id = productImage_id;
        return this;
    }

    public Byte getProductImage_type() {
        return productImage_type;
    }

    public ProductImage setProductImage_type(Byte productImage_type) {
        this.productImage_type = productImage_type;
        return this;
    }

    public String getProductImage_src() {
        return productImage_src;
    }

    public ProductImage setProductImage_src(String productImage_src) {
        this.productImage_src = productImage_src;
        return this;
    }

    public Product getProductImage_product_id() {
        return productImage_product_id;
    }

    public ProductImage setProductImage_product_id(Product productImage_product_id) {
        this.productImage_product_id = productImage_product_id;
        return this;
    }
}
