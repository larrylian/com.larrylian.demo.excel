package com.larrylian.boot.demo.utils;

import com.sun.deploy.net.URLEncoder;
import org.apache.poi.ss.usermodel.Workbook;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.Closeable;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Larry 2017-09-04
 */
public class DownloadUtils {
    private DownloadUtils(){
        //私有化
    }
    public static void exportExcel(Workbook workbook,String fileName, HttpServletResponse response, HttpServletRequest request) throws Exception{
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        fileName = fileName + dateFormat.format(new Date()) + ".xls";
        String displayName = URLEncoder.encode(fileName, "utf-8");
        String agent = request.getHeader("USER-AGENT");
        if (agent != null) {
            agent = agent.toLowerCase();
            if (agent.indexOf("firefox") != -1) {
                response.setHeader("content-disposition", String.format("attachment;filename*=utf-8'zh_cn'%s", displayName));
            } else {
                response.setHeader("content-disposition", "attachment;filename=" + displayName);
            }
        }
        response.setContentType("application/ms-excel; charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        ServletOutputStream out = response.getOutputStream();
        workbook.write(out);
        if (workbook instanceof Closeable) {
            workbook.close();
        }
    }
}
