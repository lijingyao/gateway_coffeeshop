package com.lijingyao.coffee.gateway.api.translator.clients;

import com.lijingyao.coffee.gateway.api.base.ResponseResult;
import com.lijingyao.microservice.coffee.template.trade.OrderCreateDTO;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by lijingyao on 2018/7/9 19:03.
 */
@FeignClient("service-trade/orders")
public interface OrderClient {


    @RequestMapping(method = {RequestMethod.POST})
    ResponseEntity<ResponseResult> orderCreative(@RequestBody OrderCreateDTO createDTO);


    @RequestMapping(value = "/users/{userId}", method = {RequestMethod.GET})
    ResponseEntity<ResponseResult> getUserNewOrders(@PathVariable("userId") Long userId, @RequestParam("orderSize") Integer orderSize);


    @RequestMapping(value = "/details", method = {RequestMethod.GET})
    ResponseEntity<ResponseResult> getDetailOrders(@RequestParam("mainOrderIds") String[] mainOrderIds, @RequestParam("orderSize") Integer orderSize);
}
