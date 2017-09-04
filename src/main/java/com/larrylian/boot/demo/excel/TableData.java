package com.larrylian.boot.demo.excel;

/**
 * Created by lianjw22529 on 2017/8/10
 */

/**
 * 导出excel用的数据模型
 */
public class TableData {
    private String[] rowNames;
    private String[] columnNames;
    private String[][] contents;

    public TableData() {
    }

    public TableData(String[] rowNames, String[] columnNames, String[][] contents) {
        this.rowNames = rowNames;
        this.columnNames = columnNames;
        this.contents = contents;
    }

    public String[] getRowNames() {
        return rowNames;
    }

    public void setRowNames(String[] rowNames) {
        this.rowNames = rowNames;
    }

    public String[] getColumnNames() {
        return columnNames;
    }

    public void setColumnNames(String[] columnNames) {
        this.columnNames = columnNames;
    }

    public String[][] getContents() {
        return contents;
    }

    public void setContents(String[][] contents) {
        this.contents = contents;
    }
}
