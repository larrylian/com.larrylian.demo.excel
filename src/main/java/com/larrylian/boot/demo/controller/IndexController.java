package com.larrylian.boot.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Larry on 2017/9/4
 */
@Controller
public class IndexController {
    @RequestMapping({"/", "", "index", "index.html"})
    public String index() {
        return "index";
    }

    /**
     * json测试接口
     * @return
     */
    @RequestMapping("hello")
    @ResponseBody
    public Map<String, String> helloJson(){
        Map<String, String> map = new HashMap<>();
        map.put("name", "larry");
        map.put("age", "22");
        return map;
    }
}
