package com.test.controller;/**
 * @author清梦
 * @site www.xiaomage.com
 * @company xxx公司
 * @create 2020-11-17 13:39
 */

import com.test.domain.User;
import com.test.servce.UserService;
import com.test.utils.ExcelUtil;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *@ClassName ExportExcel
 *@Description TODO
 *@Author QiaoFoPing
 *@Date 2020/11/17 13:39
 *@Version 1.0
 */
@Controller
public class ExportExcel {
    @Autowired
    private UserService userService;
    public void exportExcelKnow(){
        //创建excel对象
        HSSFWorkbook workbook =new HSSFWorkbook();
        //创建工作表单
        HSSFSheet sheet=workbook.createSheet("订单列表");
        //创建HSSFRow对象（行）
        //创建HSSFRow对象 （行）
        HSSFRow row = sheet.createRow(0);
        //创建HSSFCell对象  （单元格）
        HSSFCell cell=row.createCell(0);
        //设置单元格的值
        cell.setCellValue("单元格中的中文");

        //输出excel文件
        FileOutputStream outputStream=null;


            try {
                outputStream = new FileOutputStream("f:/导出excel/1.xls");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        try {
            workbook.write(outputStream);
            outputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //合并单元格前三列
        //CellRangeAddress参数:起始行，截至行，起始列，截至列
        sheet.addMergedRegion(new CellRangeAddress(0,0,0,3));

        //设置缺省列高
        sheet.setDefaultRowHeightInPoints(20);
        //设置缺省列宽
        sheet.setDefaultColumnWidth(30);
        //自定义300*60
        sheet.setColumnWidth(cell.getColumnIndex(),300 * 60);

        // 实例化样式对象
        HSSFCellStyle cellStyle = workbook.createCellStyle();
        // 两端对齐
        cellStyle.setAlignment(HSSFCellStyle.ALIGN_JUSTIFY);
        // 垂直居中
        cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
        // 填充图案---填充方式
        cellStyle.setFillPattern(HSSFCellStyle.DIAMONDS);
        // 设置前景色  （这个要写在背景色的前面）
        cellStyle.setFillForegroundColor(HSSFColor.RED.index);
        // 设置背景颜色
        cellStyle.setFillBackgroundColor(HSSFColor.LIGHT_YELLOW.index);
        // 设置边框
        cellStyle.setBorderBottom(HSSFCellStyle.BORDER_SLANTED_DASH_DOT);
        // 边框颜色
        cellStyle.setBottomBorderColor(HSSFColor.DARK_RED.index);
        // 日期展示格式
        cellStyle.setDataFormat(HSSFDataFormat.getBuiltinFormat("m/d/yy h:mm"));

        //将样式应用于单元格
        cell.setCellStyle(cellStyle);
        //将样式应用到行
        row.setRowStyle(cellStyle);


        // 实例化字体对象
        HSSFFont fontStyle = workbook.createFont();
        // 字体
        fontStyle.setFontName("宋体");
        // 高度
        fontStyle.setFontHeightInPoints((short)12);
        // 字体
        fontStyle.setColor(HSSFColor.BLUE.index);
        // 加粗
        fontStyle.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        // 斜体
        fontStyle.setItalic(true);
        // 下划线
        fontStyle.setUnderline(HSSFFont.U_SINGLE);
        // 将字体应用于单元格样式中
        cellStyle.setFont(fontStyle);

    }
}

