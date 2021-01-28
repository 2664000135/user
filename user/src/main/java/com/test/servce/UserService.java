package com.test.servce;/**
 * @author清梦
 * @site www.xiaomage.com
 * @company xxx公司
 * @create 2020-11-12 18:53
 */

import com.test.domain.User;
import com.test.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.jws.soap.SOAPBinding;
import java.util.List;

/**
 *@ClassName UserService
 *@Description TODO
 *@Author QiaoFoPing
 *@Date 2020/11/12 18:53
 *@Version 1.0
 */
@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    public List<User> userList(){
        return userMapper.selectAll();
        //return  userMapper.list();
    }

    public User findById(int id){
        return userMapper.selectByPrimaryKey(id);
    }
    public User add(User user){
        userMapper.insert(user);
        return user;
    }
    public void update (User user){
        userMapper.updateByPrimaryKey(user);
    }

    public void delete(int id){
        userMapper.deleteByPrimaryKey(id);
    }


}
