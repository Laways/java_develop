package com.huang.demo1.common;

import com.huang.demo1.common.utils.Result;


public class ResultUtils {

    public static Result getSuccess(){
        return Result.getSuccess();
    }
    public static Result getSuccess(String info){
        return Result.getSuccess(info);
    }

    public static Result getException(){
        return Result.getException();
    }

    public static Result getException(String info){
        return Result.getException(info);
    }

    public static Result getFailed(){
        return Result.getFailed();
    }
    public static Result getFailed(String info){
        return Result.getFailed(info);
    }
}
