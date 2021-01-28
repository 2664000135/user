package com.test;/**
 * @author清梦
 * @site www.xiaomage.com
 * @company xxx公司
 * @create 2020-11-12 20:20
 */

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

/**
 *@ClassName Application
 *@Description TODO
 *@Author QiaoFoPing
 *@Date 2020/11/12 20:20
 *@Version 1.0
 */
@SpringBootApplication
@MapperScan(basePackages = "com.test.mapper")
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class,args);
    }
}
