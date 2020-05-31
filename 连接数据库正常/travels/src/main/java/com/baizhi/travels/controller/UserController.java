package com.baizhi.travels.controller;


import com.baizhi.travels.entity.User;
import com.baizhi.travels.utils.CreateImageCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;

@Controller
@RequestMapping("user")
@CrossOrigin//运行跨域
@Slf4j
public class UserController {


    /*用户注册*/


    @PostMapping("register")
    @ResponseBody
    public String register(String code, @RequestBody User user, HttpSession session) {
        log.info("接受到的验证码" + code);
        log.info("接受到的对象：" + user);
        String sessionCode = (String) session.getAttribute("code");
        if (code.equalsIgnoreCase(sessionCode)) {
            //注册用户

        }
        throw new RuntimeException("验证码错误");

    }


    /*生成验证码*/
    @GetMapping("getImage")
    public void getImage(HttpServletResponse response, HttpSession session) throws IOException {
        CreateImageCode createImageCode = new CreateImageCode();
//        获取验证码
        String securityCode = createImageCode.getCode();
//        验证码存入session
        session.setAttribute("code", securityCode);
//        生成图片
        BufferedImage image = createImageCode.getBuffImg();
//        响应浏览
        response.setContentType("image/png");
        ImageIO.write(image, "png", response.getOutputStream());


    }
}
