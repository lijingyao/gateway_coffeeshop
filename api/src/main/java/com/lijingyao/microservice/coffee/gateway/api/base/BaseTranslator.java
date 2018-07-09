package com.lijingyao.microservice.coffee.gateway.api.base;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.stream.Collectors;

import static com.alibaba.fastjson.util.TypeUtils.castToJavaBean;

/**
 * Created by lijingyao on 2018/7/9 16:32.
 */
public class BaseTranslator {

    private static final Logger logger = LoggerFactory.getLogger(BaseTranslator.class);


    protected Object valueOfResult(ResponseEntity<ResponseResult> responseEntity, Class clazz) {


        if (responseEntity.getBody() == null) {
            FailedRestRequestException exception = new FailedRestRequestException(GatewayErrors.UN_CATCH_ERROR.getCode(),
                    GatewayErrors.UN_CATCH_ERROR.getComment());
            logger.error("Error to serialize response body, body null.", exception);
            throw exception;
        }
        return convertResult(responseEntity, clazz);

    }


    private Object convertResult(ResponseEntity<ResponseResult> responseEntity, Class clazz) {
        ResponseResult contentObject = JSONObject.parseObject(responseEntity.getBody().toString(), ResponseResult.class);

        Object data = contentObject.getResult();
        ServiceResult serviceResult = new ServiceResult();

        HttpStatus status = HttpStatus.OK;

        if (HttpStatus.OK.equals(status)) {
            if (data != null) {
                if (data instanceof JSONObject) {
                    serviceResult.setResult(castToJavaBean(data, clazz));
                } else if (data instanceof JSONArray) {
                    JSONArray array = JSONArray.class.cast(data);
                    serviceResult.setResult(array.stream().map(o -> castToJavaBean(o, clazz)).collect(Collectors.toList()));
                } else if (data.getClass().equals(clazz)) {
                    serviceResult.setResult(data);
                }
            } else {
                serviceResult.setResult(data);
            }
        }

        Object errorObj = contentObject.getErrors();

        if (errorObj != null && serviceResult.getResult() == null) {
            Errors errors = castToJavaBean(errorObj,Errors.class);

            FailedRestRequestException exception = new FailedRestRequestException(errors.getCode(), errors.getComment());
            logger.error("Translator error. {} ", errors.getComment(), exception);
            throw exception;
        } else {
            return serviceResult.getResult();
        }
    }


}
