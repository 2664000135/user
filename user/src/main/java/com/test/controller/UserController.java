package com.test.controller;/**
 * @author清梦
 * @site www.xiaomage.com
 * @company xxx公司
 * @create 2020-11-12 19:06
 */

import com.test.domain.User;
import com.test.servce.UserService;
import com.test.utils.ExcelUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 *@ClassName Usercontroller
 *@Description TODO
 *@Author QiaoFoPing
 *@Date 2020/11/12 19:06
 *@Version 1.0
 */
@Controller
public class UserController {
    @Autowired
    private UserService userService;
    private static final Logger LOGGER= LoggerFactory.getLogger(UserController.class);
    @GetMapping("/list")
    public String list(ModelMap map){
        List<User> list=userService.userList();
        map.put("list",list);
        return "list";
    }

    @RequestMapping("toAdd")
    public String toAdd(){
        return "add";
    }

    @PostMapping("/add")
    public String save(User user){
        userService.add(user);
        return "redirect:/list";
    }

    @RequestMapping("/toUpdate")
    public String toUpdate(Model map, int id){
        User user=userService.findById(id);
        map.addAttribute("user",user);
        return "update";
    }
    @RequestMapping("/update")
    public String update(User user){
        userService.update(user);
        return "redirect:/list";
    }

    @RequestMapping("/delete")
    public String deleteById(int id){
        userService.delete(id);
        return "redirect:/list";
    }
    @RequestMapping("/excel")
    public void export(HttpServletRequest request, HttpServletResponse response){
        List<User> userList=userService.userList();

        //临时文件
        File tempFile=null;
        try {
            //excel导出工具类
            ExcelUtil<User> excelUtil=new ExcelUtil();
            //导出的标题列
            String headers[]={"用户编码","姓名","性别","创建时间"};
            //时间格式化
            SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
            //要保存的文件名
            String fileName="user_"+dateFormat.format(new Date())+".xls";
            //要保存的根目录
            String rootDir=request.getSession().getServletContext().getRealPath("/");
            //要保存的目录路径
            String path = rootDir + File.separator + "tempfile";
            File saveDir=new File(path);
            if (!saveDir.exists()){
                saveDir.mkdirs();//如果文件不存在则创建文件夹
            }
            //文件路径
            path = path + File.separator + fileName;
            tempFile = new File(path); //初始化临时文件
            //输出流
            OutputStream out = new FileOutputStream(tempFile);
            //实例化excel表格
            HSSFWorkbook workbook = new HSSFWorkbook();
            //创建工作表单
            String sheetName  = "用户表";
            workbook.createSheet(sheetName);
            //导出到excel
            excelUtil.exportExcel(sheetName,headers,userList,out,"yyyy-MM-dd",workbook);
            try {
                //保存文件
                workbook.write(out);
            }catch (Exception e){
                LOGGER.info("导出excel报错",e.getMessage());
            }

            out.close();
            //以流的形式下载文件
            BufferedInputStream fis=new BufferedInputStream(new FileInputStream(path));
            byte buffer[] = new byte[fis.available()];
            fis.read(buffer);
            fis.close();
            //清空response
            response.reset();
            //设置response的Header
            response.addHeader("Content-Disposition", "attachment;filename="
                    + new String(fileName.getBytes()));
            response.addHeader("Content-Length", "" + tempFile.length());
            OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
            response.setContentType("application/vnd.ms-excel;charset=utf-8");
            toClient.write(buffer);
            toClient.flush();
            toClient.close();
        }catch (Exception e){
            e.getMessage();
        }finally {
            if (tempFile != null && tempFile.exists()){
                tempFile.delete();//删除临时文件
            }
        }


    }

}
