# 导出Excel列表DEMO

by Larry

### 使用步骤

1. 在ExcelFormatConfig 和 ExcelFormatConfigProperties 中配置导出excel的字段名和对应属性

2. 调用ExcelUtil中的getExcelByModel方法将List<Model>转化为workbook

3. 调用DownloadUtils中的exportExcel方法将workbook写入http响应体中实现下载功能
