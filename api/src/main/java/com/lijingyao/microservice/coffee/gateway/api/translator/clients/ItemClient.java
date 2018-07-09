package com.lijingyao.microservice.coffee.gateway.api.translator.clients;

import com.lijingyao.microservice.coffee.gateway.api.base.ResponseResult;
import com.lijingyao.microservice.coffee.template.items.OrderItemDTO;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by lijingyao on 2018/7/9 19:28.
 */
@FeignClient("service-item/items")
public interface ItemClient {


    @RequestMapping(value = "/{id}", method = {RequestMethod.GET})
    ResponseEntity<ResponseResult> getItem(@PathVariable("id") Integer id);

    @RequestMapping(value = "/prices", method = {RequestMethod.POST})
    ResponseEntity<ResponseResult> buildItemOrderPrice(@RequestBody OrderItemDTO itemDTO);

}
