package com.lijingyao.microservice.coffee.gateway.api.translator.clients;

import com.lijingyao.microservice.coffee.gateway.api.base.ResponseResult;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by lijingyao on 2018/7/9 19:28.
 */
@FeignClient("service-user/users")
public interface UserClient {

    @RequestMapping(value = "/{id}", method = {RequestMethod.GET})
    ResponseEntity<ResponseResult> getUser(@PathVariable("id") Long id);



}
