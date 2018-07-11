package com.lijingyao.coffee.gateway.api.base;

import com.alibaba.fastjson.JSON;

/**
 * Created by lijingyao on 2018/7/9 15:38.
 */
public class ResponseResult {

    private Object result;

    private String message;

    private Object errors = null;


    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getErrors() {
        return errors;
    }

    public void setErrors(Object errors) {
        this.errors = errors;
    }

    public String toString() {
        return JSON.toJSONString(this);

    }
}
