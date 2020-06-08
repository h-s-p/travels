package com.baizhi.travels.controller;


import com.baizhi.travels.entity.Result;
import com.baizhi.travels.entity.User;
import com.baizhi.travels.service.UserService;
import com.baizhi.travels.utils.CreateImageCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Base64Utils;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("user")
@CrossOrigin//运行跨域
@Slf4j
public class UserController {
    @Autowired
    private UserService userService;


    @RequestMapping("login")
    public Result login(@RequestBody User user) {
        Result result = new Result();
        log.info("user:" + user);
        try {
            User userDB = userService.login(user);
            //登入用户保存用户标记

            result.setMsg("登入成功");
        } catch (Exception e) {
            result.setState(false).setMsg(e.getMessage());
        }

        return result;
    }















    /*用户注册*/


    @PostMapping("register")
    public Result register(String code, String key, @RequestBody User user, HttpServletRequest request) {
        Result result = new Result();
        log.info("接受到的验证码" + code);
        log.info("接受到的验证码为" + key);
        log.info("接受到的对象：" + user);
//        验证码验证
        String keyCode = (String) request.getServletContext().getAttribute(key);
        log.info(keyCode);
        try {
            if (code.equalsIgnoreCase(keyCode)) {
                //注册用户

                userService.register(user);
                result.setMsg("注册成功'");

            } else {
                throw new RuntimeException("验证码错误");

            }

        } catch (Exception e) {
            e.printStackTrace();
            result.setMsg(e.getMessage()).setState(false);

        }
        return result;

    }


    /*生成验证码*/
    @GetMapping("getImage")


    public Map<String, String> getImage(HttpServletRequest request) throws IOException {
        Map<String, String> result = new HashMap<>();
        CreateImageCode createImageCode = new CreateImageCode();
//        获取验证码
        String securityCode = createImageCode.getCode();
//        验证码存入session
        String key = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        request.getServletContext().setAttribute(key, securityCode);
        BufferedImage image = createImageCode.getBuffImg();
        //进行base64编码
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ImageIO.write(image, "png", bos);
        String string = Base64Utils.encodeToString(bos.toByteArray());
//        String string = Base64Utils.encodeToString(bos.toByteArray());
        result.put("key", key);
        result.put("image", string);
        return result;


    }
}
