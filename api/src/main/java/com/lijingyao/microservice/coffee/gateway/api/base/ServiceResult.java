package com.lijingyao.microservice.coffee.gateway.api.base;

import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;

/**
 * Created by lijingyao on 2018/7/9 16:36.
 */
public class ServiceResult <T> implements Serializable {


    private Object result;

    private String message;

    private Errors errors = null;


    public ServiceResult() {
    }



    public Errors getErrors() {
        return errors;
    }

    public ServiceResult<T> setErrors(Errors errors) {
        this.errors = errors;
        return this;
    }

    public ServiceResult(T result) {
        this.result = result;
    }


    public Object getResult() {
        return result;
    }

    public ServiceResult setResult(Object result) {
        this.result = result;
        return this;
    }


    public String getMessage() {
        if (StringUtils.isEmpty(message) && errors != null) {
            return errors.getComment();
        }
        return message;
    }

    public ServiceResult<T> setMessage(String message) {
        this.message = message;
        return this;
    }

    public boolean isSuccess() {
        return errors == null;
    }
}
