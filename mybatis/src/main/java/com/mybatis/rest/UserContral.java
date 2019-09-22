package com.mybatis.rest;

import com.mybatis.model.User;
import com.mybatis.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserContral {

    @Autowired
    UserService userService;


    @GetMapping("/userinfo")
    public User userinfo(@RequestParam(value = "id")BigInteger id){

        return  userService.userinfo(id);
    }


    @PutMapping("/userinfo")
    public String userinfo(@RequestBody User user){

        userService.regsiteruser(user);
        return String.valueOf(user.getId());
    }

    @GetMapping("/users")
    public List<User> userList(@RequestParam(value = "pageSize") Integer pageSize, @RequestParam(value = "pageNum") Integer pageNum){

        return userService.userList(pageSize,pageNum);
    }

    @GetMapping("/users-ex")
    public List<User> userList(){

        return userService.userList();
    }
}
