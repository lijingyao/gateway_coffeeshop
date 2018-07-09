package com.lijingyao.microservice.coffee.gateway.api.base;

/**
 * Created by lijingyao on 2018/7/9 16:35.
 */
public class FailedRestRequestException extends RuntimeException {

    private int code;
    private String message;
    private Object data;
    private Errors errors;


    public FailedRestRequestException() {
    }

    public FailedRestRequestException(int code, String message) {
        this.code = code;
        this.message = message;
        this.errors = new Errors(code, message);
    }

    public int getCode() {
        return code;
    }

    public FailedRestRequestException setCode(int code) {
        this.code = code;
        return this;
    }

    public Errors getErrors() {
        return errors;
    }

    public void setErrors(Errors errors) {
        this.errors = errors;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public FailedRestRequestException setMessage(String message) {
        this.message = message;
        return this;
    }

    public Object getData() {
        return data;
    }

    public FailedRestRequestException setData(Object data) {
        this.data = data;
        return this;
    }


}
