package com.nginx.comm.modle.Result;

public class ResultMode {

    public static<T>  Result<T> sucess(T data){

        return  new Result<T>("ok","sucess", data);
    }

    public static Result faild(){
        return new Result("no","faild",null);
    }
}
