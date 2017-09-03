package com.larrylian.boot.demo.controller;

import com.larrylian.boot.demo.model.User;
import com.larrylian.boot.demo.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Larry on 2017/9/4
 */
@Controller
public class UserController {
    @Resource
    UserService userService;
    @RequestMapping("user/all")
    @ResponseBody
    public List<User> findAll() {
        return userService.findAll();
    }
}
