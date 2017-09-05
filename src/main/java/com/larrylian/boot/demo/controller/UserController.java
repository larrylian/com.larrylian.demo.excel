package com.larrylian.boot.demo.controller;

import com.larrylian.boot.demo.excel.ExcelFormatConfig;
import com.larrylian.boot.demo.excel.ExcelUtil;
import com.larrylian.boot.demo.model.User;
import com.larrylian.boot.demo.service.UserService;
import com.larrylian.boot.demo.utils.DownloadUtils;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by Larry on 2017/9/4
 */
@Controller
public class UserController {
    @Resource
    UserService userService;

    /**
     * 获取所有用户列表
     * @return
     */
    @RequestMapping("user/all")
    @ResponseBody
    public List<User> findAll() {
        return userService.findAll();
    }

    /**
     * 导出用户表
     * @param request
     * @param response
     * @throws Exception
     */
    @RequestMapping("user/export")
    @ResponseBody
    public void export(HttpServletRequest request, HttpServletResponse response) throws Exception{
        List<User> userList = userService.findAll();
        Workbook workbook = ExcelUtil.getExcelByModel(userList, ExcelFormatConfig.USER);
        DownloadUtils.exportExcel(workbook, ExcelFormatConfig.USER.getFileNamePrefix(), response, request);
    }
}
