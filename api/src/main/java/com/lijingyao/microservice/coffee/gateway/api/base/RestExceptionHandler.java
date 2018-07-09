package com.lijingyao.microservice.coffee.gateway.api.base;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by lijingyao on 2018/7/9 16:51.
 */
public class RestExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(RestExceptionHandler.class);

    public static Result onErrorReturn(Throwable e, Result result) {
        if (result == null) {
            result = new Result<>();
        }
        if (e instanceof FailedRestRequestException) {
            Errors errors = ((FailedRestRequestException) e).getErrors();
            logger.error("Service Error ,Code Msg:[{}] - [{}].", errors.getCode(), errors.getComment(), e);
            return result.setErrors(((FailedRestRequestException) e).getErrors());
        } else {
            logger.error("Service Error ,Un known Error .", e);
            return result.setErrors(GatewayErrors.UN_CATCH_ERROR);
        }
    }
}
