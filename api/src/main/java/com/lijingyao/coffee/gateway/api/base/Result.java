package com.lijingyao.coffee.gateway.api.base;

import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.util.function.Supplier;

/**
 * Created by lijingyao on 2018/7/9 16:47.
 */
public class Result<T> implements Serializable {

    private Errors errors = null;

    private T result;

    private String message;

    public Result() {
    }

    public Result(T result) {
        this.result = result;
    }

    public T getResult() {
        return result;
    }

    public Result<T> setResult(T result) {
        this.result = result;
        return this;
    }

    public Errors getErrors() {
        return errors;
    }

    public Result<T> setErrors(Errors errors) {
        this.errors = errors;
        return this;
    }

    /**
     * 设置错误,如果第一个错误不存在,则设置第二个错误
     */
    public Result<T> setErrors(Errors errors, Errors defaultErrors) {
        if (errors == null) {
            this.errors = defaultErrors;
        } else {
            this.errors = errors;
        }
        return this;
    }

    /**
     * 设置错误,如果第一个错误不存在,则设置第二个错误
     */
    public Result<T> setErrors(Errors errors, Supplier<Errors> errorsSupplier) {
        if (errors == null && errorsSupplier != null) {
            this.errors = errorsSupplier.get();
        } else {
            this.errors = errors;
        }
        return this;
    }

    public boolean isSuccess() {
        return errors == null;
    }


    public String getMessage() {
        if (StringUtils.isEmpty(message) && errors != null) {
            return errors.getComment();
        }
        return message;
    }

    public Result setMessage(String message) {
        this.message = message;
        return this;
    }

    @Override
    public String toString() {
        if (result == null) {
            if (errors == null) {
                return "no result.";
            } else {
                return errors.toString();
            }
        } else {
            if (errors == null) {
                return result.toString();
            } else {
                StringBuilder sb = new StringBuilder();
                sb.append(result.getClass());
                sb.append("\n");
                sb.append(errors.toString());
                return sb.toString();
            }
        }
    }


}