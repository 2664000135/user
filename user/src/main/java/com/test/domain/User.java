package com.test.domain;/**
 * @author清梦
 * @site www.xiaomage.com
 * @company xxx公司
 * @create 2020-11-12 18:50
 */

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 *@ClassName User
 *@Description TODO
 *@Author QiaoFoPing
 *@Date 2020/11/12 18:50
 *@Version 1.0
 */
@Data
@Table(name = "tb_user")
public class User {
    @Column
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String gender;
    private Date createTime;


}
