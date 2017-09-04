package com.larrylian.boot.demo.excel;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;

import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * excel导出工具类
 * Created by Lianjw22529 on 2017/8/11
 * 整合了项目中 ExcelExportUtil和viewExcel两个类
 * 将excel操作都封装在这个package下
 */
public class ExcelUtil {
    /***
     * 记log4j日志的log对象
     */
    protected static Log logger = LogFactory.getLog(ExcelUtil.class);

    private ExcelUtil() {
       /* set something here */
    }

    /**
     * 通过基本的数组数据，生成excel
     * 前面generalExcel()的升级版 使其适应2维数据生成
     *
     * @param tableData
     * @return
     */
    public static HSSFWorkbook createExcel(TableData tableData) throws Exception {
        String[] rowNames = tableData.getRowNames();
        String[] columnNames = tableData.getColumnNames();
        String[][] contents = tableData.getContents();

        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("sheet1");
        //设置列宽度
        sheet.setDefaultColumnWidth(16);
        //设置单元格格式
        HSSFCellStyle cellStyle = workbook.createCellStyle();
        cellStyle.setFillForegroundColor(HSSFColor.HSSFColorPredefined.WHITE.getIndex());
        cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        cellStyle.setBorderBottom(BorderStyle.THIN);
        cellStyle.setBorderLeft(BorderStyle.THIN);
        cellStyle.setBorderRight(BorderStyle.THIN);
        cellStyle.setBorderTop(BorderStyle.THIN);
        cellStyle.setAlignment(HorizontalAlignment.CENTER);
        //设置列标题单元格格式
        HSSFCellStyle headerCellStyle = workbook.createCellStyle();
        headerCellStyle.setFillForegroundColor(HSSFColor.HSSFColorPredefined.SKY_BLUE.getIndex());
        headerCellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        headerCellStyle.setBorderBottom(BorderStyle.THIN);
        headerCellStyle.setBorderLeft(BorderStyle.THIN);
        headerCellStyle.setBorderRight(BorderStyle.THIN);
        headerCellStyle.setBorderTop(BorderStyle.THIN);
        headerCellStyle.setAlignment(HorizontalAlignment.CENTER);

        HSSFRow row = sheet.createRow(0);
        HSSFCell cell = row.createCell(0);
        cell.setCellStyle(cellStyle);
        cell.setCellValue("");

        int preCellNum = 0; //为了满足两种数据格式 而设定
        boolean hasRowNames = (rowNames != null && rowNames.length > 0); //判断是否有rownames
        //判断是2维数据还是3维数据
        if (hasRowNames) {
            preCellNum = 1; //如果有rowname 整体数据右移1列
        }
        //设置列标题
        for (int i = 0; i < columnNames.length; i++) {
            cell = row.createCell(i + preCellNum);
            cell.setCellStyle(headerCellStyle);
            HSSFRichTextString text = new HSSFRichTextString(columnNames[i]);
            cell.setCellValue(text);
        }

        //添加行
        for (int i = 0; i < contents.length; i++) {
            //创建新行
            row = sheet.createRow(i + 1);
            cell = row.createCell(0);
            cell.setCellStyle(cellStyle);
            //判断是否要填充rowNames
            if (hasRowNames) {
                HSSFRichTextString text = new HSSFRichTextString(rowNames[i]);
                cell.setCellValue(text);
            }
            //填充每行数据
            for (int j = 0; j < columnNames.length; j++) {
                cell = row.createCell(j + preCellNum);
                cell.setCellStyle(cellStyle);
                HSSFRichTextString text = new HSSFRichTextString(contents[i][j]);
                cell.setCellValue(text);
            }
        }
        return workbook;
    }

    /**
     * 根据List<model>和配置文件自动生成excel表格
     *
     * @param objects
     * @param excelConfig
     * @return
     * @author lianjw22529
     */
    public static HSSFWorkbook getExcelByModel(List<?> objects, ExcelFormatConfig excelConfig) throws Exception {
        //对model（DO)数据做处理
        TableData tableData = getTableDataByModel(objects, excelConfig);
        //生成workboot
        return createExcel(tableData);
    }

    /**
     * 将list<?>转化为相应的tableData类 方便excel导出
     *
     * @param objects
     * @param excelConfig
     * @return
     * @author lianjw22529
     */
    public static TableData getTableDataByModel(List<?> objects, ExcelFormatConfig excelConfig) {
        //查询列表为空时
        if (objects == null || objects.isEmpty()) {
            return new TableData(new String[0], excelConfig.getColumeName(), new String[0][0]);
        }
        Class<?> objectClass = objects.get(0).getClass();
        String[] columeName = excelConfig.getColumeName();
        String[] propertyName = excelConfig.getPropertyName();

        //预定义一些临时变量
        String methodNameTmp;
        String valueTmp;
        Method methodTmp;
        Object methodReturnObject;
        int rowNum = objects.size();
        int columeNum = columeName.length;
        String[][] contents = new String[rowNum][columeNum];
        //将form中的属性填充到contents中
        for (int i = 0; i < objects.size(); i++) {
            for (int j = 0; j < columeNum; j++) {
                try {
                    //获取方法
                    if (propertyName[j] == null || propertyName[j] == "") {
                        throw new Exception("方法名为空-Property Index is " + i);
                    }

                    methodNameTmp = getMethodNameByProperty(propertyName[j]);
                    methodTmp = objectClass.getMethod(methodNameTmp);
                    methodReturnObject = methodTmp.invoke(objects.get(i));
                    //时间格式化
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

                    //把不同的返回类型都转成string
                    if (methodReturnObject instanceof Integer) { //interger
                        valueTmp = Integer.toString((Integer) methodReturnObject);
                    } else if (methodReturnObject instanceof String) {
                        if (StringUtils.isBlank((String) methodReturnObject)) {
                            valueTmp = "-";
                        } else {
                            valueTmp = (String) methodReturnObject;
                        }
                    } else if (methodReturnObject instanceof Date) {
                        valueTmp = simpleDateFormat.format((Date) methodReturnObject);
                    } else if (methodReturnObject instanceof Double) {
                        valueTmp = String.format("%.2f", (double) methodReturnObject);
                    } else {
                        valueTmp = "-";
                    }
                    contents[i][j] = valueTmp;
                } catch (Exception e) {
                    logger.error("获取属性值时出错", e);
                    contents[i][j] = "#"; //和前面的 "-"区分开，方便查错
                }

            }
        }
        //将数据封装到tableData中
        return new TableData(new String[0], columeName, contents);
    }

    /**
     * 根据属性名获取到对应的get方法名
     * 给反射机制服务
     *
     * @param propertyName
     * @return
     * @author lianjw22529
     */
    private static String getMethodNameByProperty(String propertyName) {
        if (propertyName == null || propertyName.length() < 2) {
            return "";
        }
        return "get" + propertyName.substring(0, 1).toUpperCase() + propertyName.substring(1);
    }
}
