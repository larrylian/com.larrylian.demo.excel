package com.larrylian.boot.demo.excel;

/**
 * Created by lianjw22529 on 2017/8/10
 */

/**
 * 导出二维数据成excel时专用的配置文件
 */
public enum ExcelFormatConfig {
    //用户列表
    USER(ExcelFormatConfigProperties.USER_NAME, ExcelFormatConfigProperties.USER_COLUME, ExcelFormatConfigProperties.USER_PROPERTY);

    //导出文件的前缀
    private String fileNamePrefix;
    //excel表各列的名称
    private String[] columeName;
    //每列在model类中对应的属性名
    private String[] propertyName;

    ExcelFormatConfig(String fileNamePrefix, String[] columeName, String[] propertyName) {
        this.fileNamePrefix = fileNamePrefix;
        this.columeName = columeName;
        this.propertyName = propertyName;
    }

    public String getFileNamePrefix() {
        return fileNamePrefix;
    }

    public String[] getColumeName() {
        return columeName;
    }

    public String[] getPropertyName() {
        return propertyName;
    }
}
