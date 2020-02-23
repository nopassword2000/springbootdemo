package com.nginx.rest;


import com.nginx.comm.modle.Result.Result;
import com.nginx.comm.modle.Result.ResultMode;
import com.nginx.comm.modle.UserInfo;
import com.nginx.comm.modle.UserLoginInfo;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@RestController
@RequestMapping("/user")
public class Authentication {


    static String password = "123456abC";
    static String xToken = "x-token";

    private Map<String, UserInfo> tokenMap = new ConcurrentHashMap();
    @GetMapping("/current-user")
    Result currentUser(){
        Cookie cookieV = null;
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest httpServletRequest = servletRequestAttributes.getRequest();
        if (httpServletRequest.getCookies() == null){
            return ResultMode.faild();
        }

        for( Cookie cookie : httpServletRequest.getCookies()){

            if( cookie.getName().equals(xToken) ){
                cookieV = cookie;
                break;
            }
        }

        if (cookieV == null){
            return ResultMode.faild();
        }

        UserInfo userInfo = tokenMap.get(cookieV.getValue());
        if (userInfo == null){

            tokenMap.remove(cookieV.getValue());
            return ResultMode.faild();
        }

     return ResultMode.sucess(userInfo);
    }


    @PostMapping("/login")
    Result userLogin(@RequestBody UserLoginInfo userLoginInfo){

        if (!userLoginInfo.getPassword().equals(password) || userLoginInfo.getUserAccount().isEmpty()){
            return ResultMode.faild();
        }

        UserInfo userInfo = new UserInfo();
        userInfo.setAccount(userLoginInfo.getUserAccount());
        userInfo.setEmail("xx@hl.com");
        userInfo.setTel("13700989988");


        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletResponse response = servletRequestAttributes.getResponse();

        String xTokenVal =  UUID.randomUUID().toString();
        Cookie cookie = new Cookie(xToken,xTokenVal);
        cookie.setPath("/");
        cookie.setComment("this is x token");
        response.addCookie(cookie);
        tokenMap.put(xTokenVal, userInfo);
        return ResultMode.sucess(userInfo);
    }
}
