package com.lijingyao.coffee.gateway.api.base;

/**
 * Created by lijingyao on 2018/7/9 16:37.
 */
public class GatewayErrors {

    public static Errors UN_CATCH_ERROR = new Errors(5000,"未知异常");

    public static Errors ILLEGAL_PARAM_ERROR = new Errors(5001,"参数异常");
}
