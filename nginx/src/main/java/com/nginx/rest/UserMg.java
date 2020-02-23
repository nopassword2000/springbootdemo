package com.nginx.rest;

import com.nginx.comm.modle.Result.Result;
import com.nginx.comm.modle.Result.ResultMode;
import com.nginx.comm.modle.UserInfo;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import com.alibaba.fastjson.JSON;

import javax.servlet.http.HttpServletRequest;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/admin")
public class UserMg {

    @GetMapping("/users")
    public Result userInfo(){

        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = servletRequestAttributes.getRequest();

        List<UserInfo> userInfos = new ArrayList<>();
        UserInfo userInfo = new UserInfo();
        String accountInfo = request.getHeader("user-info");
        if (accountInfo == null || accountInfo.isEmpty()){
            return ResultMode.faild();
        }

        UserInfo uf = JSON.parseObject(accountInfo,UserInfo.class);
        userInfo.setAccount(uf.getAccount());
        userInfo.setEmail("xx@hl.com");
        userInfo.setTel("13700989988");
        userInfos.add(userInfo);

        return ResultMode.sucess(userInfos);
    }
}
