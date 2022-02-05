package com.example.mall.controller.admin;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.mall.controller.BaseController;
import com.example.mall.entity.Category;
import com.example.mall.entity.Product;
import com.example.mall.entity.ProductImage;
import com.example.mall.service.CategoryService;
import com.example.mall.service.LastIDService;
import com.example.mall.service.ProductImageService;
import com.example.mall.service.ProductService;
import com.example.mall.util.OrderUtil;
import com.example.mall.util.PageUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.*;

/**
 * 后台管理-产品页
 */
@Controller
public class ProductController extends BaseController {
    @Resource(name = "categoryService")
    private CategoryService categoryService;
    @Resource(name = "productService")
    private ProductService productService;
    @Resource(name = "productImageService")
    private ProductImageService productImageService;
    @Resource(name = "lastIDService")
    private LastIDService lastIDService;

    /*
    转到后台管理-产品页
     */
    @RequestMapping(value = "admin/product",method = RequestMethod.GET)
    public String goToPage(HttpSession session, Map<String, Object> map) {
        logger.info("获取产品分类列表");
        List<Category> categoryList = categoryService.getList(null, null);
        map.put("categoryList", categoryList);
        logger.info("获取前10条产品列表");
        PageUtil pageUtil = new PageUtil(0, 10);
        List<Product> productList = productService.getList(null, null, null, pageUtil);
        map.put("productList", productList);
        logger.info("获取产品总数量");
        Integer productCount = productService.getTotal(null, null);
        map.put("productCount", productCount);
        logger.info("获取分页信息");
        pageUtil.setTotal(productCount);
        map.put("pageUtil", pageUtil);
        logger.info("转到后台管理-产品页");
        return "admin/productManagePage";
    }

    /*
    类型查询产品
     */
    @ResponseBody
    @RequestMapping(value = "admin/property/type/{property_category_id}", method = RequestMethod.GET,produces = "application/json;charset=utf-8")
    public String getPropertyByCategoryId(@PathVariable Integer property_category_id/* 属性所属类型ID*/){
        Category category = new Category()
                .setCategory_id(property_category_id);
        JSONObject object = new JSONObject();
        return object.toJSONString();
    }

    /*
    条件查询产品
     */
    @ResponseBody
    @RequestMapping(value = "admin/product/{index}/{count}", method = RequestMethod.GET, produces = "application/json;charset=utf-8")
    public String getProductBySearch(@RequestParam(required = false) String product_name,
                                     @RequestParam(required = false) Integer category_id,
                                     @RequestParam(required = false) Double product_sale_price,
                                     @RequestParam(required = false) Double product_price,
                                     @RequestParam(required = false) Byte[] product_isEnabled_array,
                                     @RequestParam(required = false) String orderBy,
                                     @RequestParam(required = false,defaultValue = "true") Boolean isDesc,
                                     @PathVariable Integer index,
                                     @PathVariable Integer count) throws UnsupportedEncodingException {
        if (product_isEnabled_array != null && (product_isEnabled_array.length <= 0 || product_isEnabled_array.length >=3)) {
            product_isEnabled_array = null;
        }
        if (category_id != null && category_id == 0) {
            category_id = null;
        }
        if (product_name != null) {
            product_name = "".equals(product_name) ? null : URLDecoder.decode(product_name, "UTF-8");
        }
        if (orderBy != null && "".equals(orderBy)) {
            orderBy = null;
        }
        Product product = new Product()
                .setProduct_name(product_name)
                .setProduct_category(new Category().setCategory_id(category_id))
                .setProduct_price(product_price)
                .setProduct_sale_price(product_sale_price);
        OrderUtil orderUtil = null;
        if (orderBy != null) {
            logger.info("根据{}排序，是否倒序:{}",orderBy,isDesc);
            orderUtil = new OrderUtil(orderBy, isDesc);
        }
        JSONObject object = new JSONObject();
        logger.info("按条件获取第{}页的{}条产品", index + 1, count);
        PageUtil pageUtil = new PageUtil(index, count);
        List<Product> productList = productService.getList(product, product_isEnabled_array, orderUtil, pageUtil);
        object.put("productList", JSONArray.parseArray(JSON.toJSONString(productList)));
        logger.info("按条件获取产品总数量");
        Integer productCount = productService.getTotal(product, product_isEnabled_array);
        object.put("productCount", productCount);
        logger.info("获取分页信息");
        pageUtil.setTotal(productCount);
        object.put("totalPage", pageUtil.getTotalPage());
        object.put("pageUtil", pageUtil);
        return object.toJSONString();
    }

    /*
    转到后台管理-产品详情页
     */
    @RequestMapping(value="admin/product/{pid}",method = RequestMethod.GET)
    public String goToDetailsPage(HttpSession session, Map<String, Object> map, @PathVariable Integer pid/* 产品ID */) {
        logger.info("获取product_id为{}的产品信息",pid);
        Product product = productService.get(pid);
        Integer product_id =product.getProduct_id();
        logger.info("获取产品预览图片信息");
        List<ProductImage> singleProductImageList = productImageService.getList(product_id, (byte) 0, null);
        product.setSingleProductImageList(singleProductImageList);
        logger.info("获取产品详情图片信息");
        List<ProductImage> detailsProductImageList = productImageService.getList(product_id, (byte) 1, null);
        product.setDetailProductImageList(detailsProductImageList);
        map.put("product",product);
        logger.info("获取分类列表");
        List<Category> categoryList = categoryService.getList(null,null);
        map.put("categoryList",categoryList);
        logger.info("转到后台管理-产品详情页-ajax方式");
        return "admin/include/productDetails";
    }

    /*
    更新产品信息
     */
    @ResponseBody
    @RequestMapping(value = "admin/product/{product_id}", method = RequestMethod.PUT, produces = "application/json;charset=utf-8")
    public String updateProduct(@RequestParam String product_name,
                                @RequestParam String product_brand,
                                @RequestParam Integer product_category_id,
                                @RequestParam Double product_sale_price,
                                @RequestParam Double product_price,
                                @RequestParam String product_description,
                                @RequestParam Integer product_inventory,
                                @RequestParam Byte product_isEnabled,
                                @RequestParam(required = false) String[] productSingleImageList,
                                @RequestParam(required = false) String[] productDetailsImageList,
                                @PathVariable("product_id") Integer product_id) {
        JSONObject jsonObject = new JSONObject();
        logger.info("整合产品信息");
        Product product = new Product()
                .setProduct_id(product_id)
                .setProduct_name(product_name)
                .setProduct_brand(product_brand)
                .setProduct_category(new Category().setCategory_id(product_category_id))
                .setProduct_sale_price(product_sale_price)
                .setProduct_price(product_price)
                .setProduct_description(product_description)
                .setProduct_inventory(product_inventory)
                .setProduct_isEnabled(product_isEnabled)
                .setProduct_create_date(new Date());
        logger.info("更新产品信息，产品ID值为：{}", product_id);
        boolean yn = productService.update(product);
        if (!yn) {
            logger.info("产品信息更新失败！事务回滚");
            jsonObject.put("success", false);
            throw new RuntimeException();
        }
        logger.info("产品信息更新成功！");
        if (productSingleImageList != null && productSingleImageList.length > 0) {
            logger.info("整合产品子信息-产品预览图片");
            List<ProductImage> productImageList = new ArrayList<>(5);
            for (String imageName : productSingleImageList) {
                productImageList.add(new ProductImage()
                        .setProductImage_type((byte) 0)
                        .setProductImage_src(imageName.substring(imageName.lastIndexOf("/") + 1))
                        .setProductImage_product(product)
                );
            }
            logger.info("共有{}条产品预览图片数据", productImageList.size());
            yn = productImageService.addList(productImageList);
            if (yn) {
                logger.info("产品预览图片添加成功！");
            } else {
                logger.warn("产品预览图片添加失败！事务回滚");
                jsonObject.put("success", false);
                throw new RuntimeException();
            }
        }
        if (productDetailsImageList != null && productDetailsImageList.length > 0) {
            logger.info("整合产品子信息-产品详情图片");
            List<ProductImage> productImageList = new ArrayList<>(5);
            for (String imageName : productDetailsImageList) {
                productImageList.add(new ProductImage()
                        .setProductImage_type((byte) 1)
                        .setProductImage_src(imageName.substring(imageName.lastIndexOf("/") + 1))
                        .setProductImage_product(product)
                );
            }
            logger.info("共有{}条产品详情图片数据", productImageList.size());
            yn = productImageService.addList(productImageList);
            if (yn) {
                logger.info("产品详情图片添加成功！");
            } else {
                logger.warn("产品详情图片添加失败！事务回滚");
                jsonObject.put("success", false);
                throw new RuntimeException();
            }
        }
        jsonObject.put("success", true);
        jsonObject.put("product_id", product_id);
        return jsonObject.toJSONString();
    }

    /*
    转到后台管理-产品添加页
     */
    @RequestMapping(value = "admin/product/new",method = RequestMethod.GET)
    public String goToAddPage(HttpSession session,Map<String, Object> map){
        logger.info("获取分类列表");
        List<Category> categoryList = categoryService.getList(null,null);
        map.put("categoryList",categoryList);
        logger.info("转到后台管理-产品添加页");
        return "admin/include/productDetails";
    }

    /*
    添加产品信息
     */
    @ResponseBody
    @RequestMapping(value = "admin/product", method = RequestMethod.POST,produces = "application/json;charset=utf-8")
    public String addProduct(@RequestParam String product_name,
                             @RequestParam String product_brand,
                             @RequestParam Integer product_category_id,
                             @RequestParam Double product_sale_price,
                             @RequestParam Double product_price,
                             @RequestParam String product_description,
                             @RequestParam Integer product_inventory,
                             @RequestParam Byte product_isEnabled,
                             @RequestParam(required = false) String[] productSingleImageList,
                             @RequestParam(required = false) String[] productDetailsImageList) {
        JSONObject jsonObject = new JSONObject();
        logger.info("整合产品信息");
        Product product = new Product()
                .setProduct_name(product_name)
                .setProduct_brand(product_brand)
                .setProduct_category(new Category().setCategory_id(product_category_id))
                .setProduct_sale_price(product_sale_price)
                .setProduct_price(product_price)
                .setProduct_description(product_description)
                .setProduct_inventory(product_inventory)
                .setProduct_isEnabled(product_isEnabled)
                .setProduct_create_date(new Date());
        logger.info("添加产品信息");
        boolean yn = productService.add(product);
        if (!yn) {
            logger.warn("产品添加失败！事务回滚");
            jsonObject.put("success", false);
            throw new RuntimeException();
        }
        int product_id = lastIDService.selectLastID();
        logger.info("添加成功！,新增产品的ID值为：{}", product_id);
        if (productSingleImageList != null && productSingleImageList.length > 0) {
            logger.info("整合产品子信息-产品预览图片");
            List<ProductImage> productImageList = new ArrayList<>(5);
            for (String imageName : productSingleImageList) {
                productImageList.add(new ProductImage()
                        .setProductImage_type((byte) 0)
                        .setProductImage_src(imageName.substring(imageName.lastIndexOf("/") + 1))
                        .setProductImage_product(new Product().setProduct_id(product_id))
                );
            }
            logger.info("共有{}条产品预览图片数据", productImageList.size());
            yn = productImageService.addList(productImageList);
            if (yn) {
                logger.info("产品预览图片添加成功！");
            } else {
                logger.warn("产品预览图片添加失败！事务回滚");
                jsonObject.put("success", false);
                throw new RuntimeException();
            }
        }
        if (productDetailsImageList != null && productDetailsImageList.length > 0) {
            logger.info("整合产品子信息-产品详情图片");
            List<ProductImage> productImageList = new ArrayList<>(5);
            for (String imageName : productDetailsImageList) {
                productImageList.add(new ProductImage()
                        .setProductImage_type((byte) 1)
                        .setProductImage_src(imageName.substring(imageName.lastIndexOf("/") + 1))
                        .setProductImage_product(new Product().setProduct_id(product_id))
                );
            }
            logger.info("共有{}条产品详情图片数据", productImageList.size());
            yn = productImageService.addList(productImageList);
            if (yn) {
                logger.info("产品详情图片添加成功！");
            } else {
                logger.warn("产品详情图片添加失败！事务回滚");
                jsonObject.put("success", false);
                throw new RuntimeException();
            }
        }
        logger.info("产品信息及其子信息添加成功！");
        jsonObject.put("success", true);
        jsonObject.put("product_id", product_id);
        return jsonObject.toJSONString();
    }

    /*
    上传产品图片
     */
    @ResponseBody
    @RequestMapping(value = "admin/uploadProductImage", method = RequestMethod.POST, produces = "application/json;charset=utf-8")
    public String uploadProductImage(@RequestParam MultipartFile file, @RequestParam String imageType, HttpSession session) {
        String originalFileName = file.getOriginalFilename();
        logger.info("获取图片原始文件名：{}", originalFileName);
        String extension = originalFileName.substring(originalFileName.lastIndexOf('.'));
        String filePath;
        String fileName = UUID.randomUUID() + extension;
        if ("single".equals(imageType)) {
            filePath = session.getServletContext().getRealPath("/") + "res/images/item/productSinglePicture/" + fileName;
        } else {
            filePath = session.getServletContext().getRealPath("/") + "res/images/item/productDetailsPicture/" + fileName;
        }
        logger.info("文件上传路径：{}", filePath);
        JSONObject object = new JSONObject();
        try {
            logger.info("文件上传中...");
            file.transferTo(new File(filePath));
            logger.info("文件上传完成");
            object.put("success", true);
            object.put("fileName", fileName);
        } catch (IOException e) {
            logger.warn("文件上传失败！");
            e.printStackTrace();
            object.put("success", false);
        }
        return object.toJSONString();
    }

    /*
    删除图片
     */
    @ResponseBody
    @RequestMapping(value = "admin/productImage/{productImage_id}",method = RequestMethod.DELETE,produces = "application/json;charset=utf-8")
    public String deleteProductImageById(@PathVariable Integer productImage_id/* 产品图片ID */){
        JSONObject object = new JSONObject();
        logger.info("获取productImage_id为{}的产品图片信息",productImage_id);
        ProductImage productImage = productImageService.get(productImage_id);
        logger.info("删除产品图片");
        Boolean yn = productImageService.deleteList(new Integer[]{productImage_id});
        if (yn) {
            logger.info("删除图片成功！");
            object.put("success", true);
        } else {
            logger.warn("删除图片失败！事务回滚");
            object.put("success", false);
            throw new RuntimeException();
        }
        return object.toJSONString();
    }

}
