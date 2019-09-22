package com.mybatis.service;

import com.github.pagehelper.PageHelper;
import com.mybatis.filter.PaginationContext;
import com.mybatis.mapper.UserMapper;
import com.mybatis.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;

@Service
public class UserService {

    @Autowired
    UserMapper userMapper;

    public  User userinfo(BigInteger id){

        return userMapper.selectUser(id);
    }


    public BigInteger regsiteruser(User user){
         userMapper.insertSelective(user);
         return  user.getId();
    }

    public List<User> userList(Integer pagesize, Integer idnex){

        PageHelper.startPage(idnex,pagesize);
        return userMapper.selectAll();
    }


    public List<User> userList(){
        PageHelper.startPage(PaginationContext.getPageNum() ,PaginationContext.getPageSize());
        return userMapper.selectAll();
    }
}
