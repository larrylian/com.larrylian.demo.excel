package com.larrylian.boot.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Larry on 2017/9/4
 */
@Controller
public class IndexController {
    @RequestMapping({"/", "", "index", "index.html"})
    public String index() {
        return "index";
    }
}
