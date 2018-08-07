package com.lijingyao.coffee.gateway.api.restapi;

import com.lijingyao.coffee.gateway.api.adapter.OrderAdapter;
import com.lijingyao.coffee.gateway.api.models.OrderCreateModel;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by lijingyao on 2018/7/9 23:06.
 */
@RestController
@RequestMapping("/orders")
public class OrderController {


    @Autowired
    private OrderAdapter orderAdapter;

    @ApiOperation(value = "用户提交订单接口", response = OrderCreateModel.class)
    @RequestMapping(method = {RequestMethod.POST})
    public ResponseEntity createOrder(@RequestBody OrderCreateModel model) {
        return new ResponseEntity<>(orderAdapter.createOrder(model), HttpStatus.OK);
    }


}
