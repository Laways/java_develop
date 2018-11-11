package com.huang.demo1.common.utils;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Result implements Serializable {

    private final static int SUCCESS = 0;
    private final static int EXCEPTION = 1;
    private final static int FAILED = 2;

    private final static String SUCCESS_INFO = "success";
    private final static String EXCEPTION_INFO = "exception";
    private final static String FAILED_INFO = "failed";

    private Map<String, Object> resultMap;

    private Result(int code, String info){
        resultMap = new HashMap<>();
        resultMap.put("code", code);
        resultMap.put("info", info);
    }

    private Result(int code , String info , Object data){
        this(code,info);
        resultMap.put("data",data);
    }

    public static Result getSuccess(){
        return new Result(SUCCESS, SUCCESS_INFO);
    }

    public static Result getSuccess(String info) {
        return new Result(SUCCESS, info);
    }

    public static Result getException() {
        return new Result(EXCEPTION, EXCEPTION_INFO);
    }

    public static Result getException(String info) {
        return new Result(EXCEPTION, info);
    }

    public static Result getFailed() {
        return new Result(FAILED, FAILED_INFO);
    }

    public static Result getFailed(String info) {
        return new Result(FAILED, info);
    }

    public void setResult(Object data) {
        this.resultMap.put("data",data);
    }

    public void setData(String key, Object value) { this.resultMap.put(key,value);}

    public Map<String , Object> getResult(){
        return this.resultMap;
    }
}
