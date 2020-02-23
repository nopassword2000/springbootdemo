package com.nginx.comm.modle.Result;

import lombok.Data;

@Data
public class Result<T> {

    String code;

    String msg;

    T data;


    public  Result(String code, String msg, T data){
        this.code = code;
        this.msg = msg;
        this.data = data;
    }


}
