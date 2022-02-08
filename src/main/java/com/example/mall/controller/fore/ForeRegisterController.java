package com.example.mall.controller.fore;

import com.alibaba.fastjson.JSONObject;
import com.example.mall.controller.BaseController;
import com.example.mall.entity.User;
import com.example.mall.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Map;

/**
 * 注册页
 */
@Controller
public class ForeRegisterController extends BaseController {
    @Resource(name="userService")
    private UserService userService;

    /**
     * 转到前台-用户注册页
     * @param map
     * @return
     */
    @RequestMapping(value = "register", method = RequestMethod.GET)
    public String goToPage(Map<String,Object> map) {
        logger.info("转到前台-用户注册页");
        return "fore/register";
    }

    /**
     * 前台-用户注册
     * @param user_name
     * @param user_nickname
     * @param user_password
     * @param user_gender
     * @param user_birthday
     * @param user_address
     * @return
     * @throws ParseException
     */
    @ResponseBody
    @RequestMapping(value = "register/doRegister", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public String register(
            @RequestParam(value = "user_name") String user_name  /*用户名 */,
            @RequestParam(value = "user_nickname") String user_nickname  /*用户昵称 */,
            @RequestParam(value = "user_password") String user_password  /*用户密码*/,
            @RequestParam(value = "user_gender") String user_gender  /*用户性别*/,
            @RequestParam(value = "user_birthday") String user_birthday /*用户生日*/,
            @RequestParam(value = "user_address") String user_address  /*用户所在地 */
    ) throws ParseException {
        logger.info("验证用户名是否存在");
        Integer count = userService.getTotal(new User().setUser_name(user_name));
        if (count > 0) {
            logger.info("用户名已存在，返回错误信息!");
            JSONObject object = new JSONObject();
            object.put("success", false);
            object.put("msg", "用户名已存在，请重新输入！");
            return object.toJSONString();
        }
        logger.info("创建用户对象");
        User user = new User()
                .setUser_name(user_name)
                .setUser_nickname(user_nickname)
                .setUser_password(user_password)
                .setUser_gender(Byte.valueOf(user_gender))
                .setUser_birthday(new SimpleDateFormat("yyyy-MM-dd").parse(user_birthday))
                .setUser_address(user_address);
        System.out.println(user.toString());
        logger.info("用户注册");
        if (userService.add(user)) {
            logger.info("注册成功");
            JSONObject object = new JSONObject();
            object.put("success", true);
            return object.toJSONString();
        } else {
            throw new RuntimeException();
        }
    }
}
