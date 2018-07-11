package com.lijingyao.coffee.gateway.api.restapi;

import com.lijingyao.coffee.gateway.api.adapter.OrderAdapter;
import com.lijingyao.coffee.gateway.api.models.OrderCreateModel;
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

    @RequestMapping(method = {RequestMethod.POST})
    public ResponseEntity createOrder(@RequestBody OrderCreateModel model) {
        return new ResponseEntity<>(orderAdapter.createOrder(model), HttpStatus.OK);
    }


}
