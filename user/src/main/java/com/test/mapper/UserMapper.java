package com.test.mapper;/**
 * @author清梦
 * @site www.xiaomage.com
 * @company xxx公司
 * @create 2020-11-12 18:54
 */

import com.test.domain.User;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 *@ClassName UserMapper
 *@Description TODO
 *@Author QiaoFoPing
 *@Date 2020/11/12 18:54
 *@Version 1.0
 */
@Repository
public interface UserMapper extends Mapper<User> {
    @Select("select * from tb_user")
    List<User> list();
}
