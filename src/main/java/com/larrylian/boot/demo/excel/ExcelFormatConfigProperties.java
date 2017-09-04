package com.larrylian.boot.demo.excel;

/**
 * Created by lianjw22529 on 2017/8/10
 */

/**
 * 配置excel导出的数据格式
 */
public class ExcelFormatConfigProperties {
    private ExcelFormatConfigProperties() {
        //私有化
    }

    //导出需求列表
    public static final String USER_NAME = "用户表-";
    public static final String[] USER_COLUME = {"ID", "账号", "昵称", "手机", "email", "创建时间", "更新时间"};//表头名
    public static final String[] USER_PROPERTY = {"id", "account", "nickname", "phone", "email", "created_at", "updated_at"}; //每列对应的属性名
}
