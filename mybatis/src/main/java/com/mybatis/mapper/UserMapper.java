package com.mybatis.mapper;

import com.mybatis.MyBaseMapper;
import com.mybatis.model.User;
import tk.mybatis.mapper.common.Mapper;

import java.math.BigInteger;

public interface UserMapper extends MyBaseMapper<User> {

    User selectUser(BigInteger id);
}
